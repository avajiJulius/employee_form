package com.javaproject.employeerequest.dao;

import com.javaproject.employeerequest.domain.data.EducationData;
import com.javaproject.employeerequest.domain.data.EmployeeData;
import com.javaproject.employeerequest.domain.data.PersonData;
import com.javaproject.employeerequest.domain.data.LastWorkData;
import com.javaproject.employeerequest.domain.data.components.*;
import com.javaproject.employeerequest.exception.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeeFormDaoImpl implements EmployeeFormDao {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeFormDaoImpl.class);

    private static final String INSERT_FORM =
            "INSERT INTO employee_form(" +
                    "e_form_status, e_form_date, f_name, l_name, b_day, city_id, " +
                    "relocate_status, profession, schedule_status, experience, " +
                    "salary, university_id, course_id, about, mail)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String INSERT_WORK =
            "INSERT INTO last_work(" +
            "e_form_id, organization, work_start, " +
            "work_end, position, progress, quit_reason)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?);";

    private static final String SELECT_FORMS = "SELECT ef.* , cs.city_name , us.university_name FROM employee_form ef " +
            "INNER JOIN cities cs ON cs.city_id = ef.city_id " +
            "INNER JOIN universities us ON us.university_id = ef.university_id " +
            "WHERE e_form_status = ? ORDER BY e_form_date";

    private static final String SELECT_LAST_WORK = "SELECT lw.* " +
            "FROM last_work lw " +
            "WHERE lw.e_form_id IN ";

    private Connection getConnection() throws SQLException {
        return ConnectionBuilder.getConnection();
    }

    public Long saveEmployeeForm(EmployeeForm ef) throws DaoException {
        Long result = -1L;
        logger.debug("EF {}", ef);

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_FORM, new String[]{"e_form_id"})) {

            connection.setAutoCommit(false);
            try {
                statement.setInt(1, FormStatus.UNCHECKED.ordinal());
                statement.setInt(7, RelocateStatus.UNSELECTED.ordinal());
                statement.setInt(8, Profession.UNSELECTED.ordinal());
                statement.setInt(9, ScheduleStatus.UNSELECTED.ordinal());
                statement.setTimestamp(2, java.sql.Timestamp.valueOf(LocalDateTime.now()));
                statement.setDate(5, java.sql.Date.valueOf(ef.getPersonData().getBirthDay()));


                PersonData person = ef.getPersonData();
                statement.setString(3, person.getFirstName());
                statement.setString(4, person.getLastName());
                statement.setLong(6, person.getCurrentCity().getCityId());
                statement.setString(14, person.getAbout());
                statement.setString(15, person.getEmail());

                EmployeeData employee = ef.getEmployeeData();
                statement.setDouble(10, employee.getExperience());
                statement.setDouble(11, employee.getSalary());

                EducationData education = ef.getEducation();
                statement.setLong(12, education.getUniversity().getUniversityId());
                statement.setLong(13, education.getCourse().getCourseId());


                statement.executeUpdate();

                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next())
                    result = resultSet.getLong(1);
                resultSet.close();

                saveLastWork(connection, ef, result);

                connection.commit();

            } catch (SQLException ex) {
                connection.rollback();
                throw ex;
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
            throw new DaoException(ex);
        }
        return result;
    }

    private void saveLastWork(Connection connection, EmployeeForm ef, Long efId) throws SQLException{
        try (PreparedStatement statement = connection.prepareStatement(INSERT_WORK)) {
            for (LastWorkData lastWork : ef.getLastWork()) {
                statement.setLong(1, efId);
                statement.setString(2,lastWork.getOrganization());
                statement.setDate(3, java.sql.Date.valueOf(lastWork.getWorkStart()));
                statement.setDate(4, java.sql.Date.valueOf(lastWork.getWorkEnd()));
                statement.setString(5, lastWork.getPosition());
                statement.setString(6, lastWork.getProgress());
                statement.setString(7, lastWork.getQuitReason());
                statement.addBatch();
            }
            statement.executeBatch();
        }
    }



    public List<EmployeeForm> getEmployeeForm() throws DaoException {
        List<EmployeeForm> result = new LinkedList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_FORMS)) {

            statement.setInt(1, FormStatus.UNCHECKED.ordinal());
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                EmployeeForm ef = new EmployeeForm();
                fillEmployeeForm(rs, ef);
                PersonData pd = fillPersonData(rs);
                EmployeeData ed = fillEmployeeData(rs);
                EducationData edd = fillEducationData(rs);
                ef.setPersonData(pd);
                ef.setEmployeeData(ed);
                ef.setEducation(edd);

                result.add(ef);
            }
            findLastWork(connection, result);

            rs.close();
        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
            throw new DaoException(ex);
        }
        return result;
    }



    private EducationData fillEducationData(ResultSet rs) throws SQLException {
        EducationData ed = new EducationData();
        Long universityId = rs.getLong("university_id");
        String universityName = rs.getString("university_name");
        University u = new University(universityId, universityName);
        ed.setUniversity(u);
        Long courseId = rs.getLong("course_id");
        String courseName = rs.getString("course_name");
        Course c = new Course(courseId, courseName);
        ed.setCourse(c);
        return ed;
    }

    private EmployeeData fillEmployeeData(ResultSet rs) throws SQLException{
        EmployeeData ed = new EmployeeData();
        ed.setExperience(rs.getDouble("experience"));
        ed.setSalary(rs.getDouble("salary"));
        ed.setProfession(Profession.fromValue(rs.getInt("profession")));
        ed.setScheduleStatus(ScheduleStatus.fromValue(rs.getInt("schedule_status")));
        return ed;
    }

    private void fillEmployeeForm(ResultSet rs, EmployeeForm ef) throws SQLException {
        ef.setEmployeeFormId(rs.getLong("e_form_id"));
        ef.setFormDate(rs.getTimestamp("e_form_date").toLocalDateTime());
        ef.setStatus(FormStatus.fromValue(rs.getInt("e_form_status")));
    }

    private PersonData fillPersonData(ResultSet rs) throws SQLException {
        PersonData pd = new PersonData();
        pd.setFirstName(rs.getString("f_name"));
        pd.setLastName(rs.getString("l_name"));
        pd.setBirthDay(rs.getDate("b_day").toLocalDate());
        Long cityId = rs.getLong("city_id");
        String cityName = rs.getString("city_name");
        City city = new City(cityId, cityName);
        pd.setCurrentCity(city);
        pd.setRelocateStatus(RelocateStatus.fromValue(rs.getInt("relocate_status")));
        pd.setAbout(rs.getString("about"));
        pd.setEmail(rs.getString("mail"));
        return pd;
    }

    private void findLastWork(Connection connection, List<EmployeeForm> result) throws SQLException {
        String pe = "(" + result.stream().map(ef -> String.valueOf(ef.getEmployeeFormId()))
                .collect(Collectors.joining(",")) + ")";

        Map<Long, EmployeeForm> maps = result.stream().collect(Collectors
                .toMap(ef -> ef.getEmployeeFormId(), ef -> ef));

        try(PreparedStatement statement = connection.prepareStatement(SELECT_LAST_WORK + pe)) {
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                LastWorkData ped = fillLastWorkData(rs);
                EmployeeForm ef = maps.get(rs.getLong("e_form_id"));
                ef.addPreviousEmployers(ped);
            }

        }
    }

    private LastWorkData fillLastWorkData(ResultSet rs) throws SQLException {
        String organization = rs.getString("organization");
        String position = rs.getString("position");
        String progress = rs.getString("progress");

        LastWorkData ped = new LastWorkData(organization, position, progress);

        ped.setWorkStart(rs.getDate("work_start").toLocalDate());
        ped.setWorkEnd(rs.getDate("work_end").toLocalDate());
        ped.setQuitReason(rs.getString("quit_reason"));


        return ped;
    }

}

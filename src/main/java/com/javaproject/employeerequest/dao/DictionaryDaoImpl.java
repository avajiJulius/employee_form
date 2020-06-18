package com.javaproject.employeerequest.dao;


import com.javaproject.employeerequest.config.Config;
import com.javaproject.employeerequest.domain.data.components.City;
import com.javaproject.employeerequest.domain.data.components.Course;
import com.javaproject.employeerequest.domain.data.components.University;
import com.javaproject.employeerequest.exception.DaoException;


import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DictionaryDaoImpl implements DictionaryDao{

    private static final String GET_CITY = "SELECT city_id, city_name FROM cities " +
            "WHERE UPPER(city_name) LIKE UPPER(?)";
    private static final String GET_UNIVERSITY = "SELECT university_id, university_name FROM universities " +
            "WHERE UPPER(university_name) LIKE UPPER(?)";
    private static final String GET_COURSE = "SELECT course_id, course_name FROM courses " +
            "WHERE UPPER(course_name) LIKE UPPER(?)";


    //TODO refactoring - cteate connect method
    private Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(
                Config.getProperties(Config.DB_URL),
                Config.getProperties(Config.DB_LOGIN),
                Config.getProperties(Config.DB_PASSWORD));
        return connection;
    }

    public List<City> findCity(String cityName) throws DaoException{
        List<City> result = new LinkedList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_CITY)) {

            statement.setString(1, "%" + cityName + "%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                City city = new City(rs.getInt("city_id"),
                        rs.getString("city_name"));
                result.add(city);
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return result;
    }


    public List<University> findUniversity(String universityName) throws DaoException{
        List<University> result = new LinkedList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_UNIVERSITY)) {

            statement.setString(1, "%" + universityName + "%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                University university = new University(rs.getInt("university_id"),
                        rs.getString("university_name"));
                result.add(university);
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return result;
    }


    public List<Course> findCourse(String courseName) throws DaoException{
        List<Course> result = new LinkedList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_COURSE)) {

            statement.setString(1, "%" + courseName + "%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Course course = new Course(rs.getInt("course_id"),
                        rs.getString("course_name"));
                result.add(course);
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return result;
    }
}

package com.javaproject.employeerequest.dao;

import com.javaproject.employeerequest.domain.data.EducationData;
import com.javaproject.employeerequest.domain.data.EmployeeData;
import com.javaproject.employeerequest.domain.data.PersonData;
import com.javaproject.employeerequest.domain.data.LastWorkData;
import com.javaproject.employeerequest.domain.data.components.*;
import com.javaproject.employeerequest.exception.DaoException;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;

public class EmployeeFormDaoImplTest {

    @BeforeClass
    public static void startUp() throws Exception{
        DBInit.startUp();
    }

    @Test
    public void saveEmployeeForm() throws DaoException {
        EmployeeForm ef = buildEmployeeForm(12);
        Long id = new EmployeeFormDaoImpl().saveEmployeeForm(ef);
    }

    @Test
    public void getEmployeeForm() throws DaoException {
        //TODO 
//        List<EmployeeForm> list = new EmployeeFormDaoImpl().getEmployeeForm();
    }

    public static EmployeeForm buildEmployeeForm(long id) {
        EmployeeForm ef = new EmployeeForm();
        ef.setEmployeeFormId(id);

        City city = new City(1, "Saint-Petersburg");
        University university = new University(1, "Herzen University");
        Course course = new Course(1 ,"CS50");


        PersonData pd = new PersonData("Alexandr","Matushkin", LocalDate.of(2000,2,12),
                "I am programmer", "matushkinsasha2012@yandex.ru");
        pd.setRelocateStatus(RelocateStatus.fromValue(0));
        pd.setCurrentCity(city);


        EmployeeData ed = new EmployeeData();
        ed.setProfession(Profession.fromValue(1));
        ed.setExperience(1.2);
        ed.setSalary(50.700);
        ed.setScheduleStatus(ScheduleStatus.fromValue(1));

        EducationData edud = new EducationData();
        edud.setUniversity(university);
        edud.setCourse(course);

//        LastWorkData ped1 = new LastWorkData("Petrusha", "waiter", "senior waiter");
//        //Write next two lines because something go wrong and 'position' changed to progress value and 'progress' equals null
//        ped1.setPosition("waiter");
//        ped1.setProgress("senior waiter");
//        ped1.setWorkStart(LocalDate.of(2018, 4,27));
//        ped1.setWorkEnd(LocalDate.of(2018,9,3));
//        ped1.setQuitReason("Start work on new restaurant");


        LastWorkData ped2 = new LastWorkData("Yat", "administrator", null);
        //Write next two lines because something go wrong and 'position' changed to progress value and 'progress' equals null
        ped2.setPosition("administrator");
        ped2.setProgress(null);
        ped2.setWorkStart(LocalDate.of(2018, 9,15));
        ped2.setWorkEnd(LocalDate.of(2020,2,7));
        ped2.setQuitReason("Start programming");


        ef.setPersonData(pd);
        ef.setEmployeeData(ed);
        ef.setEducation(edud);
//        ef.addPreviousEmployers(ped1);
        ef.addPreviousEmployers(ped2);

        return ef;
    }
}
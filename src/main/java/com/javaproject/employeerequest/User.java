package com.javaproject.employeerequest;


import com.javaproject.employeerequest.dao.DictionaryDaoImpl;
import com.javaproject.employeerequest.domain.EmployeeForm;
import com.javaproject.employeerequest.domain.data.components.City;
import com.javaproject.employeerequest.domain.data.components.Course;
import com.javaproject.employeerequest.domain.data.components.University;

import java.util.List;

public class User {
    private static EmployeeForm form;

    public static void main(String[] args) throws Exception {

        List<City> citiesList = new DictionaryDaoImpl().findCity("a");
        for (City city : citiesList)
            System.out.println(city.getCityName());

        List<University> universitiesList = new DictionaryDaoImpl().findUniversity("u");
        for (University university : universitiesList)
            System.out.println(university.getUniversityName());

        List<Course> couresList = new DictionaryDaoImpl().findCourse("50");
        for (Course course : couresList)
            System.out.println(course.getCourseName());

//        configure();
//        getForm();

    }


//    static void configure() {
//        Scanner in = new Scanner(System.in);
//        System.out.println("Enter profession: ");
//        String profession = in.next().toLowerCase();
//        if (profession.equals("nanny"))
//            form = new NannyForm();
//        else
//            form = new DriverForm();
//    }
//
//    static void getForm() {
//        form.buildForm();
//    }
}

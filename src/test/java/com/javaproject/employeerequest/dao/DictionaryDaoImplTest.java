package com.javaproject.employeerequest.dao;

import com.javaproject.employeerequest.domain.data.components.City;
import com.javaproject.employeerequest.domain.data.components.Course;
import com.javaproject.employeerequest.domain.data.components.University;
import com.javaproject.employeerequest.exception.DaoException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.util.stream.Collectors;

public class DictionaryDaoImplTest {

    private static final Logger logger = LoggerFactory.getLogger(DictionaryDaoImplTest.class);

    @BeforeClass
    public static void startUp() throws Exception {
        DBInit.startUp();
    }

    @Test
    public void testCity() throws DaoException {
        logger.info("Test");
        List<City> cities = new DictionaryDaoImpl().findCity("burg");
        Assert.assertTrue(cities.size() == 2);
    }

    @Test
    public void testUniversity() throws DaoException {
        List<University> uni = new DictionaryDaoImpl().findUniversity("e");
        Assert.assertTrue(uni.size() == 2);
    }

    @Test
    public void testCourse() throws DaoException {
        List<Course> courses = new DictionaryDaoImpl().findCourse("50");
        Assert.assertTrue(courses.size() == 1);
    }

}
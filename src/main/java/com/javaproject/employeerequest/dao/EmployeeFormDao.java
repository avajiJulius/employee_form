package com.javaproject.employeerequest.dao;

import com.javaproject.employeerequest.domain.EmployeeForm;
import com.javaproject.employeerequest.exception.DaoException;

import java.util.List;

public interface EmployeeFormDao {
    public Long saveEmployeeForm(EmployeeForm employeeForm) throws DaoException;

    public List<EmployeeForm> getEmployeeForm() throws DaoException;
}

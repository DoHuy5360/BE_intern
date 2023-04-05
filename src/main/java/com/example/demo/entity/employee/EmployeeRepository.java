package com.example.demo.entity.employee;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.KIT.TRAY.EmployeeAccountHeadquarterTray;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, String> {

}

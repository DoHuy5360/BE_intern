package com.example.demo.kit.query;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.kit.tray.HeadquarterEmployeeGenderTray;

@Repository
public interface HeadquarterEmployeeGenderQuery extends CrudRepository<HeadquarterEmployeeGenderTray, String> {
    @Query(value = "SELECT headquarter_name, SUM(CASE WHEN employee_gender = '1' THEN 1 ELSE 0 END) AS employee_gender_male, SUM(CASE WHEN employee_gender = '0' THEN 1 ELSE 0 END) AS employee_gender_female FROM headquarter h LEFT JOIN employee e ON h.headquarter_id = e.headquarter_id GROUP BY headquarter_name", nativeQuery = true)
    List<HeadquarterEmployeeGenderTray> getGenderByHeadquarter();
}
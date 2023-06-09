package com.example.demo.kit.query;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.kit.tray.EmployeeWorkscheduleTray;

@Repository
public interface EmployeeWorkscheduleQuery extends CrudRepository<EmployeeWorkscheduleTray, String> {
    @Query(value = "select e.employee_id, employee_name, employee_phone, employee_avatar, employee_position, work_schedule_plan,work_schedule_departure,work_schedule_destination, w.work_schedule_time_in, w.work_schedule_time_out, work_schedule_place, work_schedule_color, work_schedule_id, headquarter_name, headquarter_address from employee e, work_schedule w, headquarter h where e.employee_id = w.employee_id and e.headquarter_id = h.headquarter_id", nativeQuery = true)
    List<EmployeeWorkscheduleTray> getWorkSchedule();
}

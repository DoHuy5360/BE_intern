package com.example.demo.entity.workschedule;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkScheduleRepository extends CrudRepository<WorkSchedule, String> {
    @Query(value = "select * from work_schedule w, employee e where e.employee_id = ?1 and e.employee_id = w.employee_id", nativeQuery = true)
    List<WorkSchedule> findWorkScheduleById(String workScheduleId);

    @Modifying
    @Query(value = "delete from WorkSchedule w where w.employeeId = ?1 and w.workScheduleId = ?2")
    void deleteByEmployeeIdAndWorkScheduleId(String employeeId, String workScheduleId);
}

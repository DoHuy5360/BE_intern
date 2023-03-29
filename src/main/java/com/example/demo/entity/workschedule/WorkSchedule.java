package com.example.demo.entity.workschedule;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

import com.example.demo.utilities.Time;

@Data
@Entity
@Table(name = "workSchedule")
public class WorkSchedule {
    @Id
    @Column(length = 50, nullable = false)
    private String workScheduleId = Time.getDeadCurrentDate();

    @Column(length = 50, nullable = false)
    private String employeeId;

    @Column(length = 300, nullable = false)
    private String workSchedulePlan;

    @Column(length = 100, nullable = false)
    private String workScheduleTime = Time.getDeadCurrentDate();

    @Column(length = 100, nullable = false)
    private String createAt = Time.getDeadCurrentDate();

    @Column(length = 100, nullable = false)
    private String updateAt = Time.getDeadCurrentDate();
}

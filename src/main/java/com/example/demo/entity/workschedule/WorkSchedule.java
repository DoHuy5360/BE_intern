package com.example.demo.entity.workschedule;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.demo.kit.util.Time;

import lombok.Data;

@Data
@Entity
@Table(name = "workSchedule")
public class WorkSchedule {
    @Id
    @Column(length = 50, nullable = false)
    private String workScheduleId = "WS-" + UUID.randomUUID().toString();

    @Column(length = 50, nullable = false)
    private String employeeId;

    @Column(length = 50, nullable = true)
    private String workScheduleColor;

    @Column(length = 300, nullable = false)
    private String workSchedulePlan;

    @Column(length = 500, nullable = true, columnDefinition = "Varchar(500) default ''")
    private String workSchedulePlace;

    @Column(length = 100, nullable = false)
    private String workScheduleTimeIn;

    @Column(length = 100, nullable = false)
    private String workScheduleTimeOut;

    @Column(length = 100, nullable = false)
    private String createAt = Time.getCurrentTimeThangFormat();

    @Column(length = 100, nullable = false)
    private String updateAt = Time.getCurrentTimeThangFormat();
}

package com.example.demo.entity.timekeeping;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.demo.kit.Util.Time;

import lombok.Data;

@Data
@Entity
@Table(name = "timekeeping")
public class Timekeeping {
    @Id
    @Column(length = 50, nullable = false, updatable = false)
    private String timekeepingId = "CC-" + UUID.randomUUID().toString();

    @Column(length = 50, nullable = false)
    private String headquarterId;

    @Column(length = 50, nullable = false)
    private String employeeId;

    @Column(length = 100, nullable = false)
    private String timekeepingType;

    @Column(length = 100, nullable = false)
    private String timekeepingIn = Time.getCurrentTimeThangFormat();

    @Column(length = 100, nullable = true)
    private String timekeepingOut;

    @Column(length = 300, nullable = true)
    private String timekeepingException;

    @Column(length = 100, nullable = false)
    private String createAt = Time.getCurrentTimeThangFormat();

    @Column(length = 100, nullable = false)
    private String updateAt = Time.getCurrentTimeThangFormat();

}

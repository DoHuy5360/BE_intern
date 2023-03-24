package com.example.demo.entity.headquarter;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.demo.utilities.Time;

import lombok.Data;

@Data
@Entity
@Table(name = "headquarter")
public class Headquarter {
    @Id
    @Column(length = 50, nullable = false)
    private String headquarterId = "TS-" + UUID.randomUUID().toString();

    @Column(length = 50, nullable = false)
    private String headquarterName;

    @Column(length = 300, nullable = false)
    private String headquarterAddress;

    @Column(length = 100, nullable = false)
    private String createAt = Time.getDeadCurrentDate();

    @Column(length = 100, nullable = false)
    private String updateAt = Time.getDeadCurrentDate();
}

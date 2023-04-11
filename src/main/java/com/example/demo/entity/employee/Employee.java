package com.example.demo.entity.employee;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.example.demo.entity.account.Account;
import com.example.demo.kit.util.Time;

import lombok.Data;

@Data
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @Column(length = 50, nullable = false, updatable = false)
    private String employeeId = "NV-" + UUID.randomUUID().toString();

    @Column(length = 50, nullable = false)
    private String headquarterId;

    @Column(length = 50, nullable = false)
    private String accountId;

    @Column(length = 50, nullable = true)
    private String employeeName;

    @Column(length = 11, nullable = true)
    private String employeePhone;

    @Column(length = 300, nullable = true)
    private String employeeAvatar;

    @Column(length = 300, nullable = true)
    private String employeeAddress;

    @Column(length = 1, nullable = true)
    private String employeeGender;

    @Column(length = 50, nullable = false)
    private String employeePosition;

    @Column(length = 50, nullable = true)
    private int employeeSalary;

    @Column(length = 100, nullable = false)
    private String createAt = Time.getCurrentTimeThangFormat();

    @Column(length = 100, nullable = false)
    private String updateAt = Time.getCurrentTimeThangFormat();

    // @OneToOne(cascade = CascadeType.ALL, optional = false)
    // @JoinColumn(name = "account")
    // private Account account;
}

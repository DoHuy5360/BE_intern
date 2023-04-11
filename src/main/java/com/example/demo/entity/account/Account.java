package com.example.demo.entity.account;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.example.demo.KIT.Util.Time;
import com.example.demo.entity.employee.Employee;

import lombok.Data;

@Data
@Entity
@Table(name = "account")
public class Account {
    @Id
    @Column(length = 50, nullable = false, updatable = false)
    private String accountId = "TK-" + UUID.randomUUID().toString();

    @Column(length = 50, nullable = true, unique = false)
    private String accountEmail;

    @Column(length = 50, nullable = true)
    private String accountPassword;

    @Transient
    private String retypeAccountPassword;

    @Column(length = 50, nullable = true)
    private String accountRole;

    @Column(length = 100, nullable = false)
    private String createAt = Time.getDeadCurrentDate();

    @Column(length = 100, nullable = false)
    private String updateAt = Time.getDeadCurrentDate();

    // @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    // private Employee employee;
}

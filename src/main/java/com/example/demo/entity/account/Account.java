package com.example.demo.entity.account;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.demo.utilities.Time;

import lombok.Data;

@Data
@Entity
@Table(name = "account")
public class Account {
    @Id
    @Column(length = 50, nullable = false, updatable = false)
    private String accountId = "TK-" + UUID.randomUUID().toString();

    @Column(length = 50, nullable = true, updatable = false)
    private String accountEmail;

    @Column(length = 50, nullable = true, updatable = false)
    private String accountPassword;

    @Column(length = 50, nullable = true, updatable = false)
    private String accountRole;

    @Column(length = 100, nullable = false)
    private String createAt = Time.getDeadCurrentDate();

    @Column(length = 100, nullable = false)
    private String updateAt = Time.getDeadCurrentDate();
}

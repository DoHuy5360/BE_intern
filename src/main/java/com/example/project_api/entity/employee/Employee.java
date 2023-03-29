package com.example.project_api.entity.employee;

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

import com.example.project_api.entity.account.Account;
import com.example.project_api.ulities.Time;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @Column(name = "employee_id", length = 50, nullable = false, updatable = true)
    private String employeeId = "NV-" + UUID.randomUUID().toString();

    @Column(name = "headquater_id",length = 50, nullable = false)
    private String headquarterId;

    @Column(name = "account_id",length = 50, nullable = false)
    private String accountId;

    @Column(name = "employee_name",length = 50, nullable = false)
    private String employeeName;

    @Column(name = "employee_phone",length = 11, nullable = true)
    private String employeePhone;

    @Column(name = "employee_address",length = 300, nullable = true)
    private String employeeAddress;

    @Column(name = "employee_gender",length = 1, nullable = false)
    private String employeeGender;

    @Column(name = "employee_position",length = 50, nullable = false)
    private String employeePosition;

    @Column(name = "employee_salary",length = 50, nullable = false)
    private int employeeSalary;

    @Column(length = 50 ,nullable = false)
    private String createAt = Time.getDeadCurrentDate();

    @Column(length = 50 ,nullable = false)
    private String updateAt = Time.getDeadCurrentDate();

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "account_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Account account;
}

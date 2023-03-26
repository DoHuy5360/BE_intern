package com.example.demo.entity.timekeeping;

import com.example.demo.utilities.Time;

import java.util.UUID;
import javax.persistence.*;
import com.example.demo.entity.account.Account;
import lombok.Data;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "timekeeping")
public class Timekeeping {
    @Id
    @ManyToOne
    @JoinColumn(name = "timekeepingId")
    private Account account;
    @Column(length = 50, nullable = false, updatable = false)
    private String timekeepingId = "CC-" + UUID.randomUUID().toString();

    @Column(length = 50, nullable = false)
    private String headquarterId;

    @Column(length = 50, nullable = false)
    private String employeeId;

    @Column(length = 100, nullable = false)
    private String timekeepingType;

    @Column(length = 100, nullable = false)
    private String timekeepingIn = Time.getDeadCurrentDate();

    @Column(length = 100, nullable = true)
    private String timekeepingOut;

    @Column(length = 100, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private String createAt = Time.getDeadCurrentDate();

    @Column(length = 100, nullable = false)
    private String updateAt = Time.getDeadCurrentDate();

}

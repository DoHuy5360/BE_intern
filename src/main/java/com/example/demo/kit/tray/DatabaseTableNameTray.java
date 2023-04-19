package com.example.demo.kit.tray;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class DatabaseTableNameTray {
    @Id
    public String tablename;
}

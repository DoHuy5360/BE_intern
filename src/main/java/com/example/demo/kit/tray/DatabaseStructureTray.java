package com.example.demo.kit.tray;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class DatabaseStructureTray {
    @Id
    public String name;
    public String type;
    public Integer length;
    public String nullable;
}

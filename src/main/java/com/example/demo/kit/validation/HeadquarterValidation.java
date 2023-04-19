package com.example.demo.kit.validation;

import com.example.demo.entity.headquarter.HeadquarterRepository;
import com.example.demo.kit.res.Message;
import com.example.demo.kit.tray.HeadquarterAccountTray;

public class HeadquarterValidation extends PrimitiveValidation {
    public HeadquarterAccountTray headquarterAccountTray;
    public HeadquarterRepository headquarterRepository;
    public String id;

    public HeadquarterValidation(HeadquarterAccountTray headquarterAccountTray,
            HeadquarterRepository headquarterRepository) {
        this.headquarterAccountTray = headquarterAccountTray;
        this.headquarterRepository = headquarterRepository;
    }

    public HeadquarterValidation trackIdExist() {
        if (headquarterRepository.findById(this.id).isEmpty())
            this.errors.add(Message.setNotExistMessage("Headquarter ID"));
        return this;
    }

    public HeadquarterValidation setId(String headquarterId) {
        this.id = headquarterId;
        return this;
    }
}

package com.example.demo.kit.validation;

import com.example.demo.entity.headquarter.HeadquarterRepository;
import com.example.demo.kit.Interface.Validation;
import com.example.demo.kit.res.Message;
import com.example.demo.kit.tray.HeadquarterAccountTray;

public class HeadquarterAccountValidation extends Validation {
    public HeadquarterAccountTray headquarterAccountTray;
    public HeadquarterRepository headquarterRepository;

    public HeadquarterAccountValidation(HeadquarterAccountTray headquarterAccountTray,
            HeadquarterRepository headquarterRepository) {
        this.headquarterAccountTray = headquarterAccountTray;
        this.headquarterRepository = headquarterRepository;
    }

    public HeadquarterAccountValidation trackEmail() {
        if (!EmailValidation.track(this.headquarterAccountTray.getAccountEmail())) {
            this.errors.add(Message.EMAIL_UNVALID);
        }
        return this;
    }

    public HeadquarterAccountValidation trackPassword() {
        String passwordTrim = this.headquarterAccountTray.getAccountPassword().trim();
        if (passwordTrim.isBlank()) {
            this.errors.add(Message.setEmptyMessage("Password"));
        }
        // ^: Bắt đầu chuỗi
        // .{1,20}$: Chuỗi có từ 1 đến 20 ký tự
        // $: Kết thúc chuỗi
        else if (!passwordTrim.matches("^.{1,20}$")) {
            this.errors.add(Message.setLengthLimit("Password length", 20));
        }
        return this;
    }

    /**
     * @return this
     */
    public HeadquarterAccountValidation trackHeadquarterId() {
        String headquarterId = this.headquarterAccountTray.getHeadquarterId();
        if (headquarterRepository.findById(headquarterId).isEmpty())
            this.errors.add(Message.setNotExistMessage("Headquarter ID"));
        return this;
    }

    public HeadquarterAccountValidation trackRole() {
        if (!RoleValidation.track(this.headquarterAccountTray.getAccountRole())) {
            this.errors.add(Message.setInvalid("Role"));
        }
        return this;
    }
}

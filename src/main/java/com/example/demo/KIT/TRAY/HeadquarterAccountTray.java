package com.example.demo.kit.tray;

import com.example.demo.entity.headquarter.Headquarter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

@Data
public class HeadquarterAccountTray {
    private String accountEmail;
    private String accountPassword;
    private String accountRole;
    private String headquarterId;
    private String employeePosition;

    public String getHeadquarter() {
        // Tạo một đối tượng ObjectMapper để chuyển đổi đối tượng Java thành JSON
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Tạo một đối tượng JSON từ các thuộc tính của lớp
            String json = objectMapper.writeValueAsString(this);
            return json;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}

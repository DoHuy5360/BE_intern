package com.example.demo.KIT.RES;

import java.util.Optional;

import io.micrometer.core.instrument.config.validate.Validated.Invalid;

public class Message {
    public static final String CREATE_FAIL_AMOUNT = "Số Bản Ghi Thất Bại";

    public static final String CREATE_FAIL = "Lưu Thất Bại";
    public static final String READ_FAIL = "Đọc Thất Bại";
    public static final String UPDATE_FAIL = "Cập Nhật Thất Bại";
    public static final String DELETE_FAIL = "Xóa Thất Bại";
    public static final String CREATE_SUCCESS = "Lưu Thành Công";
    public static final String READ_SUCCESS = "Đọc Thành Công";
    public static final String UPDATE_SUCCESS = "Cập Nhật Thành Công";
    public static final String DELETE_SUCCESS = "Xóa Thành Công";

    public static final String NOT_FOUND = "Không Tìm Thấy";
    public static final String NOT_MATCH = "Không KhỚp";

    public static final String UPLOAD_FAIL = "Tải File Thất Bại";
    public static final String UPLOAD_SUCCESS = "Tải File Thành Công";

    public static final String EMAIL_ERROR = "Email";
    public static final String EMAIL_UNVALID = "Email Unvalid";
    public static final String ROLE_ERROR = "Role";
    public static final String PASSWORD_ERROR = "Password";
    public static final String HEADQUARTER_ID_ERROR = "Headquarter ID";
    public static final String POSITION_ERROR = "Position";

    public static final String EMPTY = "Empty";
    public static final String EXIST = "Exist";
    public static final String FAIL = "Fail";
    public static final String INVALID = "Invalid";
    public static final String CHARACTER = "Character";

    public static String setEmptyMessage(String name) {
        return name + " " + EMPTY;
    }

    public static String setNotExistMessage(String name) {
        return name + " Not " + EXIST;
    }

    public static String setUploadFail(String name) {
        return name + " Upload " + FAIL;
    }

    public static String setInvalid(String name) {
        return name + " " + INVALID;
    }

    public static String setLengthLimit(String name, int length) {
        return name + " Limit is " + length + " " + CHARACTER;
    }
}

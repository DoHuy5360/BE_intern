package com.example.demo.kit.validation;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.kit.res.Message;

public class FileValidation extends PrimitiveValidation {
    public MultipartFile file;
    public long size;
    public String name;
    public String fullName;
    public String extension;
    public int ONE_KILOBYTE = 1024;
    public int OPTIMAL_IMAGE_MAX_SIZE = 500 * ONE_KILOBYTE; // * 512000 byte

    public ArrayList<String> allowExtension = new ArrayList<String>(Arrays.asList("png", "jpg", "jpeg"));

    public FileValidation(MultipartFile file) {
        this.file = file;
        this.size = file.getSize();
        this.name = file.getName();
        this.fullName = file.getOriginalFilename();
        this.extension = this.fullName.substring(this.fullName.lastIndexOf(".") + 1);
    }

    public FileValidation trackExtension() {
        if (!allowExtension.contains(this.extension)) {
            this.errors.add(Message.setInvalid("File extension"));
        }
        return this;
    }

    public FileValidation trackSize() {
        if (this.size > OPTIMAL_IMAGE_MAX_SIZE) {
            this.errors.add(Message.setInvalid("File size"));
        }
        return this;
    }
}

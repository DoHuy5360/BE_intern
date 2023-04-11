package com.example.demo.kit.file;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.kit.RES.Message;
import com.example.demo.kit.RES.Response;

public class FileHandler {
    private String domainUrl = "https://be-intern.onrender.com";
    public MultipartFile file;
    public String name;
    public String fullName;
    public String extension;
    public Long size;
    public String path;
    public String publicPath = "public";

    public FileHandler(MultipartFile file) {
        this.file = file;
        this.size = file.getSize();
        this.name = file.getName();
        this.fullName = file.getOriginalFilename();
        this.extension = this.fullName.substring(this.fullName.lastIndexOf("."));
    }

    public FileHandler setPath(String path) {
        this.path = path;
        return this;
    }

    public FileHandler setName(String name) {
        this.name = name;
        return this;
    }

    public Response save() {
        String finalFileName = this.name + this.extension; // ? image.png
        String finalPath = this.publicPath + this.path + finalFileName; // ? public/image.png
        String fileUrl = domainUrl + this.path + finalFileName; // ? http://.../public/image.png
        try {
            try {
                Files.copy(this.file.getInputStream(), Paths.get(finalPath));
            } catch (Exception e) {
                Files.write(Paths.get(finalPath), this.file.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
            }
        } catch (Exception e) {
            return new Response(HttpStatus.BAD_REQUEST, Message.CREATE_FAIL);
        }
        return new Response(HttpStatus.OK, Message.CREATE_SUCCESS, fileUrl);

    }
}

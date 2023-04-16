package com.example.demo.kit.file;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.employee.Employee;
import com.example.demo.entity.employee.EmployeeRepository;
import com.example.demo.kit.res.Message;
import com.example.demo.kit.res.Response;

@Service
public class FileHandler {
    private String domainUrl = "https://be-intern.onrender.com";
    public MultipartFile file;
    public String name;
    public String fullName;
    public String extension;
    public String path;
    public String publicPath = "src/main/resources/static";

    @Autowired
    private EmployeeRepository employeeRepository;

    public FileHandler setUp(MultipartFile file) {
        this.file = file;
        this.name = file.getName();
        this.fullName = file.getOriginalFilename();
        this.extension = this.fullName.substring(this.fullName.lastIndexOf("."));
        return this;
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
                Optional<Employee> oneE = employeeRepository.findById(this.name);
                if (oneE.isPresent()) {
                    Employee _Employee = oneE.get();
                    _Employee.setEmployeeAvatar(fileUrl);
                    employeeRepository.save(_Employee);

                    // Tạo một đối tượng File từ đường dẫn thư mục gốc
                    String rootPath = System.getProperty("user.dir");
                    File rootDirectory = new File(rootPath);
                    String rootDir = rootDirectory.getAbsolutePath();

                    // Tạo tệp tin mới
                    String fullPath = rootDir + "/" + finalPath;
                    Path path = Paths.get(fullPath);
                    Files.createFile(path);

                    Files.write(Paths.get(fullPath), this.file.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
                } else {
                    return new Response(HttpStatus.BAD_REQUEST, Message.setInvalid("Employee ID"));
                }
            } catch (Exception e) {
                System.out.println(e);
                Files.copy(this.file.getInputStream(), Paths.get(finalPath), StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (Exception e) {
            System.out.println(e);
            return new Response(HttpStatus.BAD_REQUEST, Message.CREATE_FAIL);
        }
        return new Response(HttpStatus.OK, Message.CREATE_SUCCESS, fileUrl);

    }
}

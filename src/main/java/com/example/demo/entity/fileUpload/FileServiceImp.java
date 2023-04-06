package com.example.demo.entity.fileUpload;


import java.io.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.*;
import java.util.UUID;
@Service
public class FileServiceImp implements FileService{
    
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException{

        String name=file.getOriginalFilename();

        
        String randomID = UUID.randomUUID().toString();
        String fileName1 = randomID.concat(name.substring(name.lastIndexOf(".")));
        String filePath=path+ File.separator+fileName1;
        
        File f=new File(path);
        if(!f.exists()){
            f.mkdir();
        }


        Files.copy(file.getInputStream(),Paths.get(filePath));

    
        return name;
    }
}
    





    

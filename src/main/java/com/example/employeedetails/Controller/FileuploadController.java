package com.example.employeedetails.Controller;

import com.example.employeedetails.Response.GenericResponse;
import com.example.employeedetails.Services.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class FileuploadController {

    @Autowired
    FileUploadService fileUploadService;

    @GetMapping("/download")
    public ResponseEntity<byte[]> getExcelTemplate() throws IOException {
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Employee.xlsx").body(fileUploadService.getExcelTemplate());

    }

    @PostMapping("/upload")
    public Object uploadEmployee(@RequestParam("file")MultipartFile uploadEmployee) throws IOException{
        if(fileUploadService.hasCorrectFormat(uploadEmployee)){
            try {
                fileUploadService.save(uploadEmployee);
                return "Upload Successful";
            }catch (Exception e){
                return "Unable to Upload"+e.getMessage();
            }
        }
        return "Please upload the file in correct format";
    }



}

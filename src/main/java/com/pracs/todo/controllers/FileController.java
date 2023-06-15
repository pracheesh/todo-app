package com.pracs.todo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.*;
import java.util.Arrays;

@RestController
@RequestMapping("/file")
public class FileController {


    Logger logger = LoggerFactory.getLogger(FileController.class);

    @PostMapping("/single-upload")
    public String uploadSingleFile(@RequestParam("image")MultipartFile file) throws IOException {

        logger.info("Name: {}",file.getName());
        logger.info("Content Type: {}", file.getContentType());
        logger.info("Original Name: {}", file.getOriginalFilename());
        logger.info("File size: {}", file.getSize());

        InputStream inputStream = file.getInputStream();
        FileOutputStream fileOutputStream = new FileOutputStream("data.ppt");
        byte data[] = new byte[inputStream.available()];
        fileOutputStream.write(data);
        return  "FileTest";
    }

    @PostMapping("/multiple-upload")
    public String uploadMultipleFile(@RequestParam("files") MultipartFile[] files){

        Arrays.stream(files).forEach( file -> {
            logger.info("File Name: {}", file.getName());
            logger.info("File Type: {}", file.getContentType());
            System.out.println("+++++++++++++++++++++++++++++++++++");
        });
        return "Multiple File Test";
    }

    //serving image files in response

    @GetMapping("/serve-image")
    public void serveImageHandler(HttpServletResponse response){
        try{
            FileInputStream fileInputStream = new FileInputStream("images/Screenshot (63).png");
            response.setContentType(MediaType.IMAGE_JPEG_VALUE);
            StreamUtils.copy(fileInputStream, response.getOutputStream());
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}

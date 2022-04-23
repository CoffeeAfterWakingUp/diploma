package com.example.diplomaprojectgeneticcode.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import static org.springframework.http.MediaType.*;

@Controller
@RequestMapping("/file")
@Slf4j
public class FileController {

    @Value("${upload.path}")
    private String uploadPath;

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, Model model) {

        if (file != null && !file.getOriginalFilename().isEmpty()) {
            log.info("upload path: {}", uploadPath);
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();
            try {
                file.transferTo(new File(uploadPath + "/" + resultFileName));
            } catch (IOException e) {
                e.printStackTrace();
            }

            model.addAttribute("filename", resultFileName);
        }
        return "test";
    }

    @GetMapping(value = "/download/{filename:.+}",
            produces = {APPLICATION_OCTET_STREAM_VALUE, IMAGE_JPEG_VALUE, IMAGE_PNG_VALUE})
    @ResponseBody
    public byte[] downloadFile(@PathVariable String filename) throws IOException {
        File file = new File(uploadPath + "/" + filename);


        return null;
    }

}

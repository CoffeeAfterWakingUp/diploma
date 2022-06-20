package com.example.diplomaprojectgeneticcode.service.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileService {

    @Value("${upload.path}")
    private String uploadPath;

    public String getFileName(MultipartFile file) {
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

            return resultFileName;
        }

        return "";
    }


    public String getPlaceholderImage() {
        return "image_placeholder.png";
    }
}

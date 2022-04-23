package com.example.diplomaprojectgeneticcode.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("api/file")
public class FileRestController {

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/download/{filename:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) throws IOException {
        File file = new File(uploadPath + "/" + filename);
        if (file.exists()) {
            Path path = Paths.get(file.getAbsolutePath());
            ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(MediaType.APPLICATION_OCTET_STREAM_VALUE))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment:filename=\"" + filename + "\"")
                    .body(resource);
        }

        return null;
    }

}

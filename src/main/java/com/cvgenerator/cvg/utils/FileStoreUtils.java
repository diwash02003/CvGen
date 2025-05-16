package com.cvgenerator.cvg.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.UUID;

@Slf4j
@Component
public class FileStoreUtils {

    @Value("${upload.dir}")
    private String uploadDir;

    public String uploadFile(MultipartFile photoFile) {
        try {
            log.info("Starting file upload ... ");
            // Get the path of the JAR file's directory
            String jarDir = new File(System.getProperty("user.dir")).getAbsolutePath();
            String directoryPath = jarDir + File.separator + uploadDir;
            // Ensure the directory exists
            File directoryFile = new File(directoryPath);
            if (!directoryFile.exists()) {
                directoryFile.mkdirs();
                log.info("Directory created: {}", directoryPath);
            } else {
                log.info("Directory already exists: {}", directoryPath);
            }

            // Create a unique file name
            String fileName = UUID.randomUUID() + "_" + photoFile.getOriginalFilename();
            String photoFilePath = directoryPath + File.separator + fileName;
            File photo = new File(photoFilePath);
            // Save the file
            photoFile.transferTo(photo);
            log.info("File uploaded successfully: {}", photoFilePath);
            return photoFilePath;
        } catch (IOException e) {
            log.error("File upload failed: ", e);
            return null;
        }
    }

    public String getBase64FileFromPhotoPath(String photoFilePath) throws IOException {
        File readingFile = new File(photoFilePath);
        if(readingFile.exists()){
            log.info("File exists");
            byte [] bytes = Files.readAllBytes(readingFile.toPath());
            //i will get byte array of file here and convert it to base64

            String base64String = Base64.getEncoder().encodeToString(bytes);
            return "data:image/png;base64," + base64String;

        }else {
            log.info("File does not exist");
            return null;
        }
    }

}

package com.example.demo.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHelper {
    public static File moveAndStoreFile(MultipartFile file) throws IOException {
        // Get current path
        String rootPath = getUsersProjectRootDirectory();
        File dir = new File(rootPath + File.separator + "sample");
        if (!dir.exists()) { // If path exists, dont create it
            dir.mkdirs();
        }

        // Make file
        File f = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
        f.createNewFile();
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(f));
        stream.write(file.getBytes());
        stream.close();
        return f;
    }

    public static String getUsersProjectRootDirectory() {
        String envRootDir = System.getProperty("user.dir");
        Path rootDir = Paths.get(".").normalize().toAbsolutePath();
        if (rootDir.startsWith(envRootDir)) {
            return rootDir.toString();
        } else {
            throw new RuntimeException("Root dir not found in user directory.");
        }
    }
}


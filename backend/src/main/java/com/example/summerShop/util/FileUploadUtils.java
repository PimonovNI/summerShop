package com.example.summerShop.util;

import com.example.summerShop.util.exception.FileHandingException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUploadUtils {

    public static void save(String dirPath, String fileName, MultipartFile file) throws FileHandingException {
        Path uploadPath = Paths.get(dirPath);

        try {
            if (!Files.exists(uploadPath)) {
                Files.createDirectory(uploadPath);
            }

            try (InputStream inputStream = file.getInputStream()) {
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            throw new FileHandingException("Не вдалося зберегти файл", e);
        }
    }

    public static void delete(String dirPath, String fileName) throws FileHandingException {
        try {
            Path filePath = Paths.get(dirPath).resolve(fileName);
            Files.delete(filePath);
        } catch (IOException e) {
            throw new FileHandingException("Не вдалося видалити файл", e);
        }
    }

}

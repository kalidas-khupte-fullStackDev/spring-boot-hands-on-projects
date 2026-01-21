package com.ecommerce.project.service.impl;

import com.ecommerce.project.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    // Define the upload directory

    @Value("${project.image}")
    private String UPLOAD_DIR;

    //private static final String UPLOAD_DIR = "uploads/";

    @Override
    public String uploadImageFile(MultipartFile imageFile) throws IOException {
        String finalFileName = imageFile.getOriginalFilename();
       // finalFileName = UUID.randomUUID().toString().concat(Objects.requireNonNull(finalFileName).substring(finalFileName.lastIndexOf(".")));
        finalFileName = UUID.randomUUID().toString().concat("");
        // Create the upload directory if it doesn't exist
        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Save the file to the server's local directory
        byte[] bytes = imageFile.getBytes();
        Path filePath = Paths.get(UPLOAD_DIR + finalFileName);
        Files.write(filePath, bytes);
        return finalFileName;
    }

    //        try {
//            Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException(productId, "productId", "Product"));
//            String fileName = image.getOriginalFilename();
//            fileName = UUID.randomUUID().toString().concat(Objects.requireNonNull(fileName).substring(fileName.lastIndexOf(".")));
//            // Create the upload directory if it doesn't exist
//            Path uploadPath = Paths.get(UPLOAD_DIR);
//            if (!Files.exists(uploadPath)) {
//                Files.createDirectories(uploadPath);
//            }
//
//            // Save the file to the server's local directory
//            byte[] bytes = image.getBytes();
//            Path filePath = Paths.get(UPLOAD_DIR + fileName);
//            Files.write(filePath, bytes);
//
//            product.setImage(fileName);
//            return modelMapper.map(productRepository.save(product), ProductDTO.class);
//
//        } catch (IOException e) {
//            System.out.println("Failed to upload file: " + e.getMessage() + HttpStatus.INTERNAL_SERVER_ERROR);
//            return null;
//        }

}

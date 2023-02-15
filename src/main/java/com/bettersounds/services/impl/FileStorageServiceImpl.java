package com.bettersounds.services.impl;

import com.bettersounds.exception.BetterSoundsException;
import com.bettersounds.exception.FileStorageException;
import com.bettersounds.services.FileStorageService;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author TEGA
 */
@Service
@Slf4j
public class FileStorageServiceImpl implements FileStorageService{
    
    private Path fileStoragePath;
    private String fileStorageLocation;
    
    public FileStorageServiceImpl(@Value("${file.beatupload-dir:temp}") String fileStorageLocation) {
        this.fileStorageLocation = fileStorageLocation;
        this.fileStoragePath = Paths.get(fileStorageLocation)
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStoragePath);
        } catch (IOException ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }
    
    @Override
    public String uploadFile(MultipartFile file) {
        var filenameExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        var key = UUID.randomUUID().toString() + "." + filenameExtension;
        try {
            String directory = this.fileStorageLocation;
            Path newPath = Paths.get(directory).toAbsolutePath().normalize();
            Files.createDirectories(newPath);
            Files.copy(file.getInputStream(), newPath.resolve(StringUtils.cleanPath(key)),StandardCopyOption.REPLACE_EXISTING);
            return key;
        } catch (IOException e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
          }
    }

    @Override
    public Resource load(String filename) {
        try {
            Path file = fileStoragePath.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
              return resource;
            } else {
              throw new RuntimeException("Could not read the file!");
            }
          } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
          }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(fileStoragePath.toFile());
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.fileStoragePath, 1).filter(path -> !path.equals(this.fileStoragePath)).map(this.fileStoragePath::relativize);
          } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
          }
    }

    @Override
    public Resource downloadFile(String fileName) {
        String directory = this.fileStorageLocation;
        Path path = Paths.get(directory).toAbsolutePath().resolve(fileName);
        Resource resource;
        try {
            resource = new UrlResource(path.toUri());
            
        } catch (MalformedURLException ex) {
            throw new BetterSoundsException("Issue Reading this file", ex);
        }
        if(resource.exists() && resource.isReadable()){
            return resource;
        }else{
            throw new BetterSoundsException("This file does not exist or is not readable");
        }
    }
}

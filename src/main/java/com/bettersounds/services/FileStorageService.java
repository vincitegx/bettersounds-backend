package com.bettersounds.services;

import java.nio.file.Path;
import java.util.stream.Stream;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author TEGA
 */
public interface FileStorageService {
    
    
    String uploadFile(MultipartFile file);

    Resource load(String filename);

    void deleteAll();

    Stream<Path> loadAll();
    
    Resource downloadFile(String fileName);
}

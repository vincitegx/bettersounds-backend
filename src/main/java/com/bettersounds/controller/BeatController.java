package com.bettersounds.controller;

import com.bettersounds.dto.ApiResponse;
import com.bettersounds.dto.BeatDto;
import com.bettersounds.services.BeatService;
import com.bettersounds.services.FileStorageService;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author TEGA
 */
@RestController
@RequestMapping("/api/beat")
@RequiredArgsConstructor
@Slf4j
public class BeatController {

    private final BeatService beatService;
    private final FileStorageService fileStorageService;

    @PostMapping(path = "/upload", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ApiResponse> uploadBeat(@RequestParam("beatDto") @Valid String beatDto,
            @RequestParam("artWork") MultipartFile artWork,
            @RequestParam("taggedmp3") MultipartFile taggedmp3,
            @RequestParam("untaggedmp3") MultipartFile untaggedmp3,
            @RequestParam("untaggedwav") MultipartFile untaggedwav
    ) {
        BeatDto beat = beatService.getJson(beatDto);
        beat = beatService.uploadBeat(beat, artWork, taggedmp3, untaggedmp3, untaggedwav);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("beat", beat))
                        .message("Upload Successful")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
    }

    @PutMapping(path = "/edit", consumes = {
        MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE
    })
    public ResponseEntity<ApiResponse> editBeat(@RequestPart("beatDto") String beatDto,
            @RequestPart("artWork") MultipartFile artWork,
            @RequestPart("taggedmp3") MultipartFile taggedmp3,
            @RequestPart("untaggedmp3") MultipartFile untaggedmp3,
            @RequestPart("untaggedwav") MultipartFile untaggedwav
    ) {
        BeatDto beat = beatService.getJson(beatDto);
        beat = beatService.editBeat(beat, artWork, taggedmp3, untaggedmp3, untaggedwav);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("beat", beat))
                        .message("Beat Updated")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllBeats(
            @RequestParam Optional<String> search,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy) {
        Sort.Direction direction;
        if("name".equals(sortBy.get()) || "genre".equals(sortBy.get())){
            direction = Sort.Direction.ASC;
        }else{
            direction = Sort.Direction.DESC;
        }
        Page<BeatDto> beat = beatService.getAllBeats(
                search.orElse(""),
                PageRequest.of(page.orElse(0), 6, direction, sortBy.orElse("id"))
        );
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("beats", beat))
                        .message("beats retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping("/allfree")
    public ResponseEntity<ApiResponse> getAllFreeBeats(
            @RequestParam Optional<String> search,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy) {
        Sort.Direction direction;
        if("name".equals(sortBy.get()) || "genre".equals(sortBy.get())){
            direction = Sort.Direction.ASC;
        }else{
            direction = Sort.Direction.DESC;
        }
        Page<BeatDto> beat = beatService.getAllFreeBeats(
                search.orElse(""),
                PageRequest.of(page.orElse(0), 3, direction, sortBy.orElse("id"))
        );
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("beats", beat))
                        .message("beats retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getBeat(@PathVariable Long id) {
        BeatDto beatDto = beatService.getBeat(id);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("beat", beatDto))
                        .message("beat retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
    
    @GetMapping("/getBeats")
    public ResponseEntity<ApiResponse> getBeats(@RequestParam List<String> id) {
        List<BeatDto> beatDto = beatService.getBeats(id);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("beats", beatDto))
                        .message("beats retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteBeat(@PathVariable Long id) {
        boolean deleted = beatService.deleteBeat(id);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("deleted", deleted))
                        .message("Deleted Successfully")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

//    @GetMapping(path = "/download/{name}/{fileName:.+}", produces = MediaType.ALL_VALUE)
//    public ResponseEntity<ApiResponse> getBeatFile(@PathVariable String name, @PathVariable String fileName, HttpServletRequest request){
//        Files.readAllBytes("");
//        Resource resource = fileStorageService.downloadFile(name, fileName);
//        String mimeType;
//        try {
//            mimeType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
//        } catch (IOException ex) {
//            mimeType = MediaType.ALL_VALUE;
//        }
//        if (mimeType == null) {
//            mimeType = "application/octet-stream";
//        }
//        return ResponseEntity.ok(
//        ApiResponse.builder()
//                .timeStamp(LocalDateTime.now())
//                .data(Map.of("beatFile", resource))
//                .message("Deleted Successfully")
//                .status(OK)
//                .statusCode(OK.value())
//                .build()
//        );
//    }
    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadBeat(@PathVariable String fileName, HttpServletRequest request) {
        Resource resource = fileStorageService.downloadFile(fileName);
        String mimeType;
        try {
            mimeType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            mimeType = MediaType.ALL_VALUE;
        }
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(mimeType))
                //                .contentType(MediaType.ALL)
                //                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+resource.getFilename()+"\"")
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}

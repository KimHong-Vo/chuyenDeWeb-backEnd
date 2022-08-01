package com.web.bookStore.uploadImageModule;

import java.util.List;
import java.util.stream.Collectors;

import com.web.bookStore.shared.model.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

@Controller
@RequestMapping(path = "image")
public class UploadImageController {

    @Autowired
    private ImageStorageServiceImpl storageService;

    @PostMapping("upload")
    public ResponseEntity<ResponseObject> uploadImage(@RequestParam("image") MultipartFile image) {
        try {
            String generatedFileName = storageService.storeFile(image);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("Upload file successfully", generatedFileName)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject(e.getMessage())
            );
        }
    }

    @GetMapping("{name:.+}")
    public ResponseEntity<byte[]> readDetailFile(@PathVariable String name) {
        try {
            byte[] bytes = storageService.readFileContent(name);
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(bytes);
        } catch (Exception e) {
            return ResponseEntity
                    .noContent()
                    .build();
        }
    }

    @GetMapping("")
    public ResponseEntity<ResponseObject> getUploadedImage() {
        try {
            List<String> urls = storageService.loadAll()
                    .map(path -> {
                        String urlPath = MvcUriComponentsBuilder
                                .fromMethodName(UploadImageController.class, "readDetailFile", path.getFileName().toString())
                                .build()
                                .toUriString();
                        return urlPath;
                    })
//					.map(path -> path.getFileName().toString())
                    .collect(Collectors.toList());
            return ResponseEntity.ok(
                    new ResponseObject("Load all images successfully", urls)
            );
        } catch (Exception e) {
            return ResponseEntity.ok(
                    new ResponseObject(e.getMessage(), new String[] {})
            );
        }
    }
}


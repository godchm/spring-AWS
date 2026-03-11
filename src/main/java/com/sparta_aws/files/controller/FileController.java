//package com.sparta_aws.files.controller;
//
//import com.sparta_aws.files.dto.FileDownloadUrlResponse;
//import com.sparta_aws.files.dto.FileUploadResponse;
//import com.sparta_aws.files.service.S3Service;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.net.URL;
//
//@RestController
//@RequiredArgsConstructor
//public class FileController {
//
//    private final S3Service s3Service;
//
//    @PostMapping("/api/members/{merberId}/profile-image")
//    public ResponseEntity<FileUploadResponse> upload(
//            @PathVariable Long id,
//            @RequestParam("file") MultipartFile file) {
//        String key = s3Service.upload(id, file);
//        return ResponseEntity.ok(new FileUploadResponse(key));
//    }
//
//    @GetMapping("/api/members/{merberId}/profile-image")
//    public ResponseEntity<FileDownloadUrlResponse> getDownloadUrl(@PathVariable Long id) {
//        URL url = s3Service.getDownloadUrl(id);
//        return ResponseEntity.ok(new FileDownloadUrlResponse(url.toString()));
//    }
//}
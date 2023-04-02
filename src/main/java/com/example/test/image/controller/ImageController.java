package com.example.test.image.controller;

import com.example.test.config.generic.Result;
import com.example.test.give.Give;
import com.example.test.image.controller.dto.ImageListRequest;
import com.example.test.image.service.ImageService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/image")
public class ImageController {
    private final ImageService imageService;

    @GetMapping("/test")
    public ResponseEntity<Result> test(@RequestParam String filename) {
        return ResponseEntity.ok().body(new Result(imageService.get(filename)));
    }

    @GetMapping("/findImagesByGive")
    public ResponseEntity<Result> findImagesByGive(@RequestParam Long giveId) {
        return ResponseEntity.ok().body(new Result(imageService.getImagesByGive(giveId)));
    }

    @PostMapping("/saveImageList")
    public ResponseEntity<Void> saveImageList(@ModelAttribute ImageListRequest request){
        imageService.saveImageList(request);
        return ResponseEntity.ok().build();
    }

//    @PostMapping("/saveImage")
//    public ResponseEntity<Void> saveImage(@RequestBody MultipartFile request){
//        imageService.uploadFileToGcs(request,);
//        return ResponseEntity.ok().build();
//    }

    @Data
    @AllArgsConstructor
    public static class T {
        private MultipartFile multipartFile;
    }
}

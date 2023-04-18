package com.example.test.image.controller;

import com.example.test.config.generic.Result;
import com.example.test.give.Give;
import com.example.test.give.repository.GiveRepository;
import com.example.test.image.controller.dto.ImageRequest;
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
    private final GiveRepository giveRepository;

    @GetMapping("/test")
    public ResponseEntity<Result> test(@RequestParam String filename) {
        return ResponseEntity.ok().body(new Result(imageService.get(filename)));
    }

    @GetMapping("/findImagesByGive")
    public ResponseEntity<Result> findImagesByGive(@RequestParam Long giveId) {
        return ResponseEntity.ok().body(new Result(imageService.getImagesByGive(giveId)));
    }

    @PostMapping("/saveImageList")
    public ResponseEntity<Void> saveImageList(@ModelAttribute ImageRequest request) {
        imageService.saveImageList(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/saveImage")
    public ResponseEntity<Result> saveImage(@ModelAttribute ImageRequest imageRequest){
        Give give = giveRepository.findGiveById(imageRequest.getGiveId()).get();
        return ResponseEntity.ok().body(new Result(imageService.uploadFileToS3(imageRequest.getImage(), give)));
    }

    @Data
    @AllArgsConstructor
    public static class T {
        private MultipartFile multipartFile;
    }
}

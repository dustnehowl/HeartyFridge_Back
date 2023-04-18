package com.example.test.image.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
public class ImageRequest {
    private Long giveId;
    private MultipartFile image;
}

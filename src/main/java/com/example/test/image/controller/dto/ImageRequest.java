package com.example.test.image.controller.dto;

import com.example.test.give.Give;
import com.example.test.image.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ImageRequest {
    private Give give;
    private MultipartFile image;

}

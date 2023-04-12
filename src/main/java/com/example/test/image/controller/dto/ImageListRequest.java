package com.example.test.image.controller.dto;

import com.example.test.give.Give;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
public class ImageListRequest {
    private Long giveId;
    private MultipartFile image;
}

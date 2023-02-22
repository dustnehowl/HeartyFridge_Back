package com.example.test.give.controller.dto.v2;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Data
@AllArgsConstructor
public class GiveFormDataRequest {
    private String name;
    private String expiration;
    private String amount;
    private String category;
    private String message;
    private String fridgeId;
    private String giverId;
    private List<MultipartFile> images;
}

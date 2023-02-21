package com.example.test.image.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
public class ImageListResponse {
    private Long giveId;
    private List<ImageDto> images;
    private final String baseUri = "https://storage.cloud.google.com/slowy_storage123/";

    public ImageListResponse(Long giveId, List<ImageDto> images) {
        this.giveId = giveId;
        this.images = images;
    }
    public ImageListResponse(Long giveId, ImageDto image){
        this.giveId = giveId;
        this.images = new ArrayList<>();
        this.images.add(image);
    }
}

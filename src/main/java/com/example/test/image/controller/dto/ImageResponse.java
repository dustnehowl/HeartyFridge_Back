package com.example.test.image.controller.dto;

import com.example.test.image.Image;
import feign.template.Literal;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
public class ImageResponse {
    private List<ImageDto> images;
    private String baseUri;

    public ImageResponse(List<ImageDto> images, String baseUri) {
        this.images = images;
        this.baseUri = baseUri;
    }
    public ImageResponse(ImageDto image, String baseUri){
        this.images = new ArrayList<>();
        this.images.add(image);
        this.baseUri = baseUri;
    }
}

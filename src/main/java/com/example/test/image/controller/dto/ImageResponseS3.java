package com.example.test.image.controller.dto;

import com.example.test.image.Image;
import lombok.Data;

@Data
public class ImageResponseS3 {
    private long imageId;
    private String url;

    public ImageResponseS3(Long imageId, String url){
        this.imageId = imageId;
        this.url = "https://slowybucket.s3.ap-northeast-2.amazonaws.com/" + url;
    }
    public static ImageResponseS3 from(Image image){
        return new ImageResponseS3(
                image.getId(),
                image.getUuidFileName()
        );
    }
}

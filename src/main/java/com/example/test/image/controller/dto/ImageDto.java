package com.example.test.image.controller.dto;
import com.example.test.food.Food;
import com.example.test.give.Give;
import com.example.test.image.Image;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ImageDto {
    private Long imageId;
    private String originalFileName;
    private String uuidFileName;

    public ImageDto(Long imageId, String originalFileName, String uuidFileName) {
        this.imageId = imageId;
        this.originalFileName = originalFileName;
        this.uuidFileName = uuidFileName;
    }

    public static ImageDto from(Image image){
        return new ImageDto(
                image.getId(),
                image.getOriginalFileName(),
                image.getUuidFileName()
        );
    }

    public static List<ImageDto> of(List<Image> images){
        return images.stream().map(
                ImageDto::from).collect(Collectors.toList());
    }

    public static ImageDto of(Image image){
        return ImageDto.from(image);
    }
}

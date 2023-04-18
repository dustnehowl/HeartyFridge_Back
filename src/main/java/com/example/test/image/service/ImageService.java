package com.example.test.image.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.example.test.give.Give;
import com.example.test.give.repository.GiveRepository;
import com.example.test.image.Image;
import com.example.test.image.controller.dto.*;
import com.example.test.image.repository.ImageRepository;
import com.google.cloud.storage.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class ImageService {
    private final ApplicationContext applicationContext;
    private final ImageRepository imageRepository;
    private final GiveRepository giveRepository;
    private final Storage storage;
    private final AmazonS3Client amazonS3Client;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public ImageResponseS3 saveImage(ImageRequest request) {
        Give give = findGiveById(request.getGiveId());
        MultipartFile multipartFile = request.getImage();
        return uploadFileToS3(multipartFile, give);
    }
    public ImageResponseS3 uploadFileToS3(MultipartFile file, Give give){
        try{
            String originalName = file.getOriginalFilename();
            String storeFileName = createStoreFileName(originalName);

            ObjectMetadata metadata= new ObjectMetadata();
            metadata.setContentType(file.getContentType());
            metadata.setContentLength(file.getSize());
            amazonS3Client.putObject(bucket,storeFileName, file.getInputStream(),metadata);

            Image newImage = Image.builder()
                    .originalFileName(originalName)
                    .uuidFileName(storeFileName)
                    .give(give)
                    .build();
            imageRepository.save(newImage);
            return ImageResponseS3.from(newImage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Image uploadFileToGcs(MultipartFile file, Give give){
        try
        {
            String originalFilename = file.getOriginalFilename();
            String storeFilename = createStoreFileName(originalFilename);
            Image image = new Image(originalFilename, storeFilename, give);
            imageRepository.save(image);
            byte[] content = file.getBytes();
            String bucketName = "slowy_gcs_1";
            BlobId blobId = BlobId.of(bucketName, storeFilename);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                    .setContentType(file.getContentType())
                    .build();
            storage.create(blobInfo, content);
            return image;
        }
        catch (Exception e) {
            throw new RuntimeException();
        }
    }

    private final String storageBaseUrl = "gs://%s/";
    private final String bucketId = "friendly-legacy-382417";
    private final String buckerName = "slowy_gcs_1";

    public String get(String filename) {
        Resource resource = applicationContext.getResource(String.format(storageBaseUrl + filename, bucketId));
        try {
            return StreamUtils.copyToString(
                    resource.getInputStream(),
                    Charset.defaultCharset());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ImageResponseS3 getImagesByGive(Long giveId){
        Give give = giveRepository.findGiveById(giveId).get();
        List<Image> images = imageRepository.findImagesByGive(give);
        Image image = findImagesByGive(give);
        return ImageResponseS3.from(image);
    }

    public ImageListResponse saveImageList(ImageRequest request) {
        Give give = findGiveById(request.getGiveId());

        MultipartFile multipartFile = request.getImage();
        List<Image> images = new ArrayList<>();
        Image image = uploadFileToGcs(multipartFile, give);
        images.add(image);
        return new ImageListResponse(request.getGiveId(), ImageDto.of(images));
    }

    private String createStoreFileName(String originalFilename) {
        String ext = extractExt(originalFilename);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }
    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }
    private Give findGiveById(Long giveId){
        return giveRepository.findGiveById(giveId).orElseThrow(() -> new RuntimeException());
    }
    private Image findImagesByGive(Give give){
        return imageRepository.findImageByGive(give).orElseThrow(() -> new RuntimeException());
    }
}

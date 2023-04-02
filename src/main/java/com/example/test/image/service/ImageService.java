package com.example.test.image.service;

import com.example.test.give.Give;
import com.example.test.give.repository.GiveRepository;
import com.example.test.image.Image;
import com.example.test.image.controller.ImageController;
import com.example.test.image.controller.dto.*;
import com.example.test.image.repository.ImageRepository;
import com.google.cloud.storage.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class ImageService {
    //private final String baseUri = "https://storage.cloud.google.com/slowy_storage123";
    private final ApplicationContext applicationContext;
    private final ImageRepository imageRepository;
    private final GiveRepository giveRepository;
    private final Storage storage;

    public Image uploadFileToGcs(MultipartFile file, Give give){
        try
        {
            String originalFilename = file.getOriginalFilename();
            String storeFilename = createStoreFileName(originalFilename);

            Image image = new Image(originalFilename, storeFilename, give);
            imageRepository.save(image);
            // 1. MultipartFile 객체에서 파일을 읽어옵니다.
            byte[] content = file.getBytes();

            // 2. GCS에 연결하고 BlobId를 생성합니다.
            String bucketName = "slowy_gcs_1";
            //String objectName = UUID.randomUUID().toString(); // 파일 이름으로 사용할 UUID 생성
            BlobId blobId = BlobId.of(bucketName, storeFilename);

            // 3. BlobInfo 객체를 생성합니다.
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                    .setContentType(file.getContentType())
                    .build();

            // 4. Storage 객체를 사용하여 Blob을 업로드합니다.
            storage.create(blobInfo, content);

            // 5. 업로드된 파일의 URL을 반환합니다.
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

    public ImageListResponse getImagesByGive(Long giveId){
        Give give = giveRepository.findGiveById(giveId).get();
        List<Image> images = imageRepository.findImagesByGive(give);
        return new ImageListResponse(giveId, ImageDto.of(images));
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

    public ImageListResponse saveImageList(ImageListRequest request) {
        Give give = giveRepository.findGiveById(request.getGiveId()).get();

        List<MultipartFile> multipartFiles = request.getImages();
        List<Image> images = new ArrayList<>();
        for(MultipartFile multipartFile : multipartFiles){
            Image image = uploadFileToGcs(multipartFile, give);
            images.add(image);
        }
        return new ImageListResponse(request.getGiveId(), ImageDto.of(images));
    }
}

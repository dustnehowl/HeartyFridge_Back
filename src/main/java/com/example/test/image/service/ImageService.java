package com.example.test.image.service;

import com.example.test.give.Give;
import com.example.test.give.repository.GiveRepository;
import com.example.test.image.Image;
import com.example.test.image.controller.dto.ImageDto;
import com.example.test.image.controller.dto.ImageRequest;
import com.example.test.image.controller.dto.ImageResponse;
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
    private final String baseUri = "https://storage.cloud.google.com/slowy_storage123";
    private final ApplicationContext applicationContext;
    private final ImageRepository imageRepository;
    private final GiveRepository giveRepository;
    private final Storage storage;

    public String get(String filename) {
        Resource resource = applicationContext.getResource(String.format("gs://%s/" + filename, "hopeful-canto-373101"));
        try {
            return StreamUtils.copyToString(
                    resource.getInputStream(),
                    Charset.defaultCharset());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public ImageResponse saveImage(ImageRequest imageRequest) {
        Give give = imageRequest.getGive();
        MultipartFile multipartFile = imageRequest.getImage();
        String originalFilename = multipartFile.getOriginalFilename();
        String storeFilename = createStoreFileName(originalFilename);

        Image image = new Image(originalFilename, storeFilename, give);
        imageRepository.save(image);

        // imageRequest 에서 멀티파트 이미지들 빼와서 클라우드에 저장해야한다.
        String uuidFileName = image.getUuidFileName();
        try {
            imageRequest.getImage().transferTo(new File("/Users/gim-yeonsu/" +uuidFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        Bucket bucket = storage.create(BucketInfo.of("slowy_storage123"));


        try {
            storage.create(
                    BlobInfo.newBuilder("slowy_storage123", uuidFileName)
                            .setAcl(new ArrayList<>(Collections.singletonList(Acl.of(Acl.User.ofAllAuthenticatedUsers(), Acl.Role.READER))))
                            .build(),
                    Files.readAllBytes(Paths.get("/Users/gim-yeonsu/" +uuidFileName)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new ImageResponse(ImageDto.of(image), baseUri);
    }

    public ImageResponse getImagesByGive(Long giveId){
        List<Image> images = imageRepository.findImagesByGive(
                giveRepository.findGiveById(giveId).get());

        return new ImageResponse(ImageDto.of(images), baseUri);
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
}

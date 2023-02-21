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
            String originalFilename = multipartFile.getOriginalFilename();
            String storeFilename = createStoreFileName(originalFilename);

            Image image = new Image(originalFilename, storeFilename, give);
            imageRepository.save(image);
            images.add(image);

            String uuidFileName = image.getUuidFileName();
            try{
                //multipartFile.transferTo(new File("/Users/gim-yeonsu/"+ uuidFileName));
                storage.create(
                        BlobInfo.newBuilder("slowy_storage123", uuidFileName)
                                .setAcl(new ArrayList<>(Collections.singletonList(Acl.of(Acl.User.ofAllAuthenticatedUsers(), Acl.Role.READER))))
                                .setContentType("image/png")
                                .build(),
                        multipartFile.getInputStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        return new ImageListResponse(request.getGiveId(), ImageDto.of(images));
    }
}

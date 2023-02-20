package com.example.test.image.repository;

import com.example.test.give.Give;
import com.example.test.image.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findImagesByGive(Give give);
}

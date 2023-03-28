package com.example.test.fridge.controller;

import com.example.test.config.generic.Result;
import com.example.test.fridge.controller.dto.v2.SearchRequest;
import com.example.test.fridge.service.FridgeService;
import jakarta.servlet.ServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/fridge")
public class FridgeController {

    private final FridgeService fridgeService;
    @GetMapping("/saveFridge")
    public String saveFridge(){
        return fridgeService.saveFridge();
    }
    @GetMapping("/getAll")
    public Result getAll(ServletRequest servletRequest){
        String memberId = (String) servletRequest.getAttribute("memberId");
        return new Result(fridgeService.getAll(Long.parseLong(memberId)));
    }
    @GetMapping(value = "/getFridge2")
    public Result getFridge(ServletRequest servletRequest, @RequestParam Long fridgeId){
        String memberId = (String) servletRequest.getAttribute("memberId");
        System.out.println("============= getFridge" + fridgeId+ " =============");
        return new Result(fridgeService.getFridge2(fridgeId, Long.parseLong(memberId)));
    }

    @GetMapping("/searchByKeyword")
    public ResponseEntity<Result> searchByName(@ModelAttribute SearchRequest request, ServletRequest servletRequest){
        String keyword = request.getKeyword();
        System.out.println(keyword);
        String memberId = (String) servletRequest.getAttribute("memberId");
        //String keyword = searchRequest.getKeyword();
        return ResponseEntity.ok().body(new Result(fridgeService.findByKeyword(Long.parseLong(memberId), keyword)));
    }

    @PostMapping("/setKorean")
    public String setKorean(){
        return fridgeService.setKorean();
    }

    @PutMapping("/setEnglish")
    public String setEnglish(){
        return fridgeService.setEnglish();
    }
}

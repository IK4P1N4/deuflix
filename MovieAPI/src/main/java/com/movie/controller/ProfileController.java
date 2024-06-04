package com.movie.controller;

import com.movie.dto.ProfileDto;
import com.movie.request.ProfileCreateRequest;
import com.movie.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/profiles")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping
    public List<ProfileDto> getAllProfiles() {
        return profileService.getAllProfiles();
    }

    @GetMapping("/{uId}")
    public List<ProfileDto> getProfilesByUId(@PathVariable Long uId) {
        return profileService.getProfilesByUId(uId);
    }

    @PostMapping("/insert")
    public ResponseEntity<String> createProfile(@RequestBody ProfileCreateRequest profileCreateRequest) {
        System.out.println("Received ProfileCreateRequest: " + profileCreateRequest); // 디버깅용 로그 추가
        try {
            profileService.createProfile(profileCreateRequest);
            return new ResponseEntity<>("Profile created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace(); // 오류 세부 사항을 로그에 출력
            return new ResponseEntity<>("Failed to create profile", HttpStatus.BAD_REQUEST);
        }
    }

}

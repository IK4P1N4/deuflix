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
    public ResponseEntity<ProfileDto> insertProfile(@RequestBody ProfileCreateRequest request) {
        try {
            System.out.println("Request received: " + request);  // 요청 로깅
            ProfileDto createdProfile = profileService.insertProfile(request);
            return ResponseEntity.ok(createdProfile);
        } catch (Exception e) {
            e.printStackTrace();  // 에러 스택 트레이스 출력
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}

package com.movie.controller;

import com.movie.dto.ProfileDto;
import com.movie.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
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
}

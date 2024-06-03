package com.movie.service;

import com.movie.dto.ProfileDto;
import com.movie.model.Profile;
import com.movie.repository.ProfileRepository;
import com.movie.request.ProfileCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public List<ProfileDto> getAllProfiles() {
        List<Profile> profiles = profileRepository.findAll();
        return profiles.stream()
                .map(ProfileDto::fromEntity)
                .collect(Collectors.toList());
    }

    public List<ProfileDto> getProfilesByUId(Long uId) {
        List<Profile> profiles = profileRepository.findByUId(uId);
        return profiles.stream()
                .map(ProfileDto::fromEntity)
                .collect(Collectors.toList());
    }

    public ProfileDto insertProfile(ProfileCreateRequest request) {
        Profile newProfile = new Profile(
                request.getUId(),
                request.getPName(),
                request.getPImage(),
                request.getAge()  // Add this line to include age
        );
        Profile savedProfile = profileRepository.save(newProfile);
        return ProfileDto.fromEntity(savedProfile);
    }
}

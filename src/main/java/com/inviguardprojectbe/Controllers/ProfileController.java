package com.inviguardprojectbe.Controllers;

import com.inviguardprojectbe.Classes.Profile;
import com.inviguardprojectbe.Services.ProfileService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("profiles")
@RestController

public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService){
        this.profileService = profileService;
    }

    @GetMapping("list")
    public List<Profile> getProfileList() {
        return this.profileService.getProfileList();
    }

    @GetMapping("{id}")
    public Profile getProfile(@PathVariable Integer id){
     return this.profileService.getProfile(id);
    }

    @DeleteMapping("{id}")
    public void deleteProfile(@PathVariable Long id){
        this.profileService.deleteProfile(id);
    }

    @PostMapping("create")
    public Profile createProfile(@RequestBody Profile profile){
        return this.profileService.createProfile(profile);
    }

    @PutMapping("{id}")
    public Profile updateProfile(@PathVariable Long id, @RequestBody Profile profile){
        return this.profileService.updateProfile(id, profile);
    }
}

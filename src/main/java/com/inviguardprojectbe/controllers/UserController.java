package com.inviguardprojectbe.controllers;


import com.inviguardprojectbe.models.ProfileDto;
import com.inviguardprojectbe.models.UserDto;
import com.inviguardprojectbe.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/profile")
    public ProfileDto getProfile(){
         Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
         Long id = Long.parseLong(auth.getPrincipal().toString());

        return this.userService.getProfile(id);
    }

    @GetMapping("/profile/{id}")
    public ProfileDto getProfileById(@PathVariable Long id){
        Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
        Long authId = Long.parseLong(auth.getPrincipal().toString());
        UserDto user = userService.getById(authId);

        if(user.isOrganization()){
            return this.userService.getProfile(id);

        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User can't get other profiles");
    }

    @DeleteMapping("/profile")
    public void deleteProfile(){
        Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
        Long id = Long.parseLong(auth.getPrincipal().toString());

        this.userService.deleteProfile(id);
    }
}

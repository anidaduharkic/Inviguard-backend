package com.inviguardprojectbe.services;
import com.inviguardprojectbe.models.ProfileDto;
import com.inviguardprojectbe.models.UserDto;
import com.inviguardprojectbe.models.entities.User;
import com.inviguardprojectbe.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public ProfileDto getProfile(long id) {

        UserDto user = toDto(getEntity(id));
        ProfileDto profileDto = new ProfileDto(user);
        return profileDto;
    }

    public UserDto getByEmail(String email) {
        User user = this.userRepository.findOneByEmail(email);
        return toDto(user);
    }

    public UserDto getById(Long id) {
        User user = getEntity(id);
        return toDto(user);
    }

    public void deleteProfile(long id) {
        userRepository.deleteById(id);
    }

    private static UserDto toDto(User user){
        return new UserDto(user.getId(), user.getFullName(), user.getPhoneNumber(), user.getEmail(), user.getPasswordHash(), user.isOrganization());
    }

    private User getEntity(long id){
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()){
            return userOptional.get();
        }
        throw new RuntimeException("User with id:" + id + " does not exist!");
    }
}

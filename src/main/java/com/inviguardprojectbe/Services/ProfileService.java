package com.inviguardprojectbe.Services;

import com.inviguardprojectbe.Classes.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfileService {

    private long id = 0;

    private List<Profile> profiles = new ArrayList<>();

    public ProfileService(){
        this.profiles.add(new Profile(this.id++,"Alice", "Nguyen", "Inventory Manager"));
        this.profiles.add(new Profile(this.id++,"Michael", "Patel", "Accounting Manager"));
        this.profiles.add(new Profile(this.id++,"Emily", "Smith", "Warehouse Supervisor"));
    }

    public List<Profile> getProfileList(){
        return this.profiles;
    }

   public Profile getProfile(long id){
        for(Profile profile : this.profiles){
            if(profile.getId() == id){
                return profile;
            }
        }
        return null;
    }

   public void deleteProfile(long id){
        List<Profile> profilesList = this.profiles;
        for(int i = 0; i < profilesList.size(); i++){
            Profile profile = profilesList.get(i);
            if(profile.getId() == id){
                profilesList.remove(i);
                return;
            }
        }
    }

    public Profile createProfile(Profile profile) {
        profile.setId(this.id++);
        this.profiles.add(profile);
        return profile;
    }

    public Profile updateProfile(Long id, Profile profile) {
        Profile current = this.getProfile(id);
        if (current == null) {
            return null;
        }
        current.setName(profile.getName());
        current.setSurname(profile.getSurname());
        current.setAbout(profile.getAbout());
        return current;
    }
}

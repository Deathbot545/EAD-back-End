package com.example.Profile_Service.Controller;

import com.example.Profile_Service.Service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {
    @Autowired
    ProfileService profileService;
}

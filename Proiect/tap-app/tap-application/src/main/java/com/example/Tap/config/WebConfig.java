package com.example.Tap.config;

import com.example.Tap.dto.ExperienceDTO;
import com.example.Tap.dto.StudiesDTO;
import com.example.Tap.dto.UserDTO;
import com.example.Tap.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@Configuration
public class WebConfig {
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }


    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {
         init(userService);
        };
    }

    void  init(UserService userService) throws Exception {
//        UserDTO userDTO = new UserDTO();
//        userDTO.setUsername("dragos");
//        userDTO.setPassword("1234");
//
//        userService.saveUserToDB(userDTO);

//        ExperienceDTO experienceDTO = new ExperienceDTO();
//        experienceDTO.setDescription("Experience Descripton");
//        experienceDTO.setStartDate(new Date());
//        experienceDTO.setEndDate(new Date());
//        experienceDTO.setIsPersonalProject(false);
//
//        StudiesDTO studiesDTO = new StudiesDTO();
//        studiesDTO.setDescription("Am invatat");
//        studiesDTO.setStartDate(new Date());
//        studiesDTO.setEndDate(new Date());
//
//        userService.updateExperience(experienceDTO);
//        userService.updateStudies(studiesDTO);

    }
}

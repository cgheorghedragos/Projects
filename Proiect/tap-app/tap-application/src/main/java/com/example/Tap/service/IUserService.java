package com.example.Tap.service;

import com.example.Tap.dto.ExperienceDTO;
import com.example.Tap.dto.StudiesDTO;
import com.example.Tap.dto.UserDTO;
import com.example.Tap.dto.UserDetailsDTO;

import java.util.List;

public interface IUserService {
    UserDTO saveUserToDB(UserDTO userDTO) throws Exception;

    List<UserDetailsDTO> getUsers(UserDetailsDTO userDTO) throws Exception;
    UserDetailsDTO getUser(UserDetailsDTO userDTO) throws Exception;

    ExperienceDTO updateExperience(ExperienceDTO experienceDTO);
    Integer deleteExperience(Long id);

    StudiesDTO updateStudies(StudiesDTO experienceDTO);
    Integer deleteStudies(Long id);
}

package com.example.Tap.service;

import com.example.Tap.dao.ExperienceRepository;
import com.example.Tap.dao.StudiesRepository;
import com.example.Tap.dao.UserRepository;
import com.example.Tap.dto.ExperienceDTO;
import com.example.Tap.dto.StudiesDTO;
import com.example.Tap.dto.UserDTO;
import com.example.Tap.dto.UserDetailsDTO;
import com.example.Tap.entity.ExperienceEntity;
import com.example.Tap.entity.StudiesEntity;
import com.example.Tap.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService implements UserDetailsService, IUserService {
    private final UserRepository userRepository;
    private final ExperienceRepository experienceRepository;
    private final StudiesRepository studiesRepository;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("USER_NOT_FOUND");
        } else {
            log.info("User found in database: {}", username);
        }

        Collection<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("USER"));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public UserDTO saveUserToDB(UserDTO userDTO) throws Exception {

        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        UserEntity userEntity = userRepository.findByUsername(userDTO.getUsername());

        if (userEntity != null) {
            throw new Exception("User Already Exists");
        }

        userEntity = mapper.map(userDTO, UserEntity.class);
        if (userEntity.getPhotoUrl() == null) {
            userEntity.setPhotoUrl("https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png");
        }
        userEntity = userRepository.save(userEntity);

        return mapper.map(userEntity, UserDTO.class);
    }

    @Override
    public List<UserDetailsDTO> getUsers(UserDetailsDTO userDTO) throws Exception {
        if (Objects.isNull(userDTO.getUsername())) {
            throw new Exception("Username not found");
        }
        List<UserEntity> users = userRepository.findUserEntitiesByUsernameContains(userDTO.getUsername());
        return users.stream().map(userEntity -> mapper.map(userEntity, UserDetailsDTO.class)).collect(Collectors.toList());
    }

    @Override
    public UserDetailsDTO getUser(UserDetailsDTO userDTO) throws Exception {
        UserEntity user = userRepository.findByUsername(userDTO.getUsername());
        UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
        userDetailsDTO.setUsername(user.getUsername());
        userDetailsDTO.setUsername(user.getUsername());
        return UserDetailsDTO.builder()
                .username(user.getUsername())
                .id(user.getId())
                .studies(user.getStudies().stream().sorted(Comparator.comparing(StudiesEntity::getId)).map(studiesEntity -> mapper.map(studiesEntity, StudiesDTO.class)).collect(Collectors.toList()))
                .experiences(user.getExperiences().stream().sorted(Comparator.comparing(ExperienceEntity::getId)).map(experienceEntity -> mapper.map(experienceEntity, ExperienceDTO.class)).collect(Collectors.toList()))
                .build();
    }

    @Override
    public ExperienceDTO updateExperience(ExperienceDTO experienceDTO) {
        ExperienceEntity entity = mapper.map(experienceDTO, ExperienceEntity.class);

        if (Objects.isNull(experienceDTO.getId())) {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            UserEntity user = userRepository.findByUsername(username);
            entity.setUser(user);
        } else {
            Optional<ExperienceEntity> experience = experienceRepository.findById(experienceDTO.getId());
            experience.ifPresent(experienceEntity -> entity.setUser(experienceEntity.getUser()));
        }

        if (experienceDTO.getIsPersonalProject()) {
            entity.getPhotos().forEach(photosEntity -> photosEntity.setExperience(entity));
        }

        return mapper.map(experienceRepository.save(entity), ExperienceDTO.class);
    }

    @Override
    public Integer deleteExperience(Long id) {
        experienceRepository.deleteById(id);
        Optional<ExperienceEntity> entity = experienceRepository.findById(id);

        return entity.isPresent() ? 1 : 0;
    }

    @Override
    public StudiesDTO updateStudies(StudiesDTO studiesDTO) {
        StudiesEntity entity = mapper.map(studiesDTO, StudiesEntity.class);

        if (Objects.isNull(studiesDTO.getId())) {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            UserEntity user = userRepository.findByUsername(username);
            entity.setUser(user);
        } else {
            Optional<StudiesEntity> experience = studiesRepository.findById(studiesDTO.getId());
            experience.ifPresent(experienceEntity -> entity.setUser(experienceEntity.getUser()));
        }

        return mapper.map(studiesRepository.save(entity), StudiesDTO.class);
    }

    @Override
    public Integer deleteStudies(Long id) {
        studiesRepository.deleteById(id);
        Optional<ExperienceEntity> entity = experienceRepository.findById(id);

        return entity.isPresent() ? 1 : 0;
    }
}

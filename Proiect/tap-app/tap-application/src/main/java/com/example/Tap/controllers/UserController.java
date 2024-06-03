package com.example.Tap.controllers;

import com.example.Tap.dto.UserDTO;
import com.example.Tap.dto.UserDetailsDTO;
import com.example.Tap.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/rest/")
public class UserController {

    private final UserService userService;
    private final ModelMapper mapper;

    @PostMapping("/register-user")
    public UserDetailsDTO registerUser(@RequestBody UserDTO userDTO) throws Exception {
        return mapper.map(userService.saveUserToDB(userDTO), UserDetailsDTO.class);
    }

    @GetMapping("/find-by-username")
    public UserDetailsDTO findByUsernameUser(@RequestParam(required = false) String username) throws Exception {
        String userNameToFind = username;
        if (username == null || StringUtils.isEmpty(username)) {
            userNameToFind = SecurityContextHolder.getContext().getAuthentication().getName();

        }
        UserDetailsDTO userDetailsDTO = userService.getUser(UserDetailsDTO.builder().username(userNameToFind).build());
        userDetailsDTO.setIsPersonalAccount(SecurityContextHolder.getContext().getAuthentication().getName().equals(userNameToFind));

        return userDetailsDTO;
    }

    @GetMapping("/search-users")
    public List<UserDetailsDTO> findUsers(@RequestParam(required = false) String username) throws Exception {
        if (username == null || StringUtils.isEmpty(username)) {
            throw new Exception("Please use request param");
        }
        return userService.getUsers(UserDetailsDTO.builder().username(username).build());

    }


}

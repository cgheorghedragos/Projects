package com.example.Tap.controllers;

import com.example.Tap.dto.StudiesDTO;
import com.example.Tap.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/rest/")
public class StudiesController {

    private final UserService userService;

    @PutMapping("/studies/update")
    public StudiesDTO updateStudies(@RequestBody StudiesDTO studiesDTO) {
        return userService.updateStudies(studiesDTO);
    }

    @PostMapping("/studies/save")
    public StudiesDTO saveStudies(@RequestBody StudiesDTO studiesDTO) {
        return userService.updateStudies(studiesDTO);
    }

    @DeleteMapping("/studies/delete")
    public Integer deleteExperience(@RequestParam Long id) {
        return userService.deleteStudies(id);
    }
}

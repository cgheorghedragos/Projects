package com.example.Tap.controllers;

import com.example.Tap.dto.ExperienceDTO;
import com.example.Tap.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/rest/")
public class ExperienceController {

    private final UserService userService;

    @PutMapping("/experience/update")
    public ExperienceDTO updateExperience(@RequestBody ExperienceDTO experienceDTO) {
        return userService.updateExperience(experienceDTO);
    }

    @PostMapping("/experience/save")
    public ExperienceDTO saveExperience(@RequestBody ExperienceDTO experienceDTO) {
        return userService.updateExperience(experienceDTO);
    }

    @DeleteMapping("/experience/delete")
    public Integer deleteExperience(@RequestParam Long id) {
        return userService.deleteExperience(id);
    }
}

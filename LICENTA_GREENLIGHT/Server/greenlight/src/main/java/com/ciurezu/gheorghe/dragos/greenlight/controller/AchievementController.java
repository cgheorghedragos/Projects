package com.ciurezu.gheorghe.dragos.greenlight.controller;

import com.ciurezu.gheorghe.dragos.greenlight.data.model.request.SimpleAchievementDTO;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.request.SimpleInactiveAchievementDTO;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.shared.response.GreenLightResponse;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.shared.AchievementLevelDTO;
import com.ciurezu.gheorghe.dragos.greenlight.service.AchievementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/achievement")
public class AchievementController {
    private final AchievementService achievementService;

    @PostMapping("/save")
    public ResponseEntity<GreenLightResponse<Boolean>> saveAchievement(@RequestBody @Valid AchievementLevelDTO dto) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/achievement/save").toUriString());

        Boolean response = achievementService.saveAchievementLevel(dto);
        GreenLightResponse<Boolean> greenLightResponse = new GreenLightResponse<>(response, "successfully");

        return ResponseEntity.created(uri).body(greenLightResponse);
    }

    @GetMapping("/get-by-user")
    public ResponseEntity<GreenLightResponse<?>> getAchievementsByUser(@RequestParam String user) {

        List<SimpleAchievementDTO> response = achievementService.getAllAchievementForUser(user);
        GreenLightResponse<?> greenLightResponse = new GreenLightResponse<>(response, "successfully");

        return ResponseEntity.ok().body(greenLightResponse);
    }

    @GetMapping("/get-inactive-by-user")
    public ResponseEntity<GreenLightResponse<?>> getInactiveAchievementsByUser(@RequestParam String user) {

        List<SimpleInactiveAchievementDTO> response = achievementService.getAllInactiveAchievementsForUser(user);
        GreenLightResponse<?> greenLightResponse = new GreenLightResponse<>(response, "successfully");
        return ResponseEntity.ok().body(greenLightResponse);
    }


}

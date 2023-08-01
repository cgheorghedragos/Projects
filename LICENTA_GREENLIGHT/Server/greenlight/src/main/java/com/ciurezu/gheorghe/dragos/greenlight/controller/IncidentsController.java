package com.ciurezu.gheorghe.dragos.greenlight.controller;

import com.ciurezu.gheorghe.dragos.greenlight.data.model.shared.response.GreenLightResponse;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.shared.IncidentDTO;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.shared.SolveIncidentDTO;
import com.ciurezu.gheorghe.dragos.greenlight.exception.BadRequestException;
import com.ciurezu.gheorghe.dragos.greenlight.service.IncidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/incidents")
public class IncidentsController {
    private final IncidentService incidentService;

    @PostMapping(value = "/add-incident")
    public ResponseEntity<GreenLightResponse<Boolean>> saveIncident(@RequestPart("file") List<MultipartFile> photos, @RequestPart("json") IncidentDTO incidentDTO) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/incidents").toUriString());
        GreenLightResponse<Boolean> greenLightResponse = new GreenLightResponse<>(incidentService.saveIncident(incidentDTO, photos), null);
        return ResponseEntity.created(uri).body(greenLightResponse);
    }

    @GetMapping(value = "/get-incidents")
    public ResponseEntity<GreenLightResponse<?>> getIncidents() {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/incidents").toUriString());
        GreenLightResponse<?> greenLightResponse = new GreenLightResponse<>(incidentService.getIncidents(), null);
        return ResponseEntity.created(uri).body(greenLightResponse);
    }

    @PutMapping(value = "/solve-incident")
    public ResponseEntity<GreenLightResponse<?>> solveIncident(@RequestBody SolveIncidentDTO solveIncidentDTO) throws BadRequestException {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/incidents").toUriString());
        GreenLightResponse<?> greenLightResponse = new GreenLightResponse<>(incidentService.solveIncidents(solveIncidentDTO), null);
        return ResponseEntity.created(uri).body(greenLightResponse);
    }

    @PostMapping(value = "/add-recycle-bin")
    public ResponseEntity<GreenLightResponse<?>> addRecycleBin(@RequestBody IncidentDTO incidentDTO) throws BadRequestException {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/incidents").toUriString());
        GreenLightResponse<?> greenLightResponse = new GreenLightResponse<>(incidentService.saveRecycleBin(incidentDTO), null);
        return ResponseEntity.created(uri).body(greenLightResponse);
    }

    @GetMapping(value = "/get-recycle-bin")
    public ResponseEntity<GreenLightResponse<?>> getAllRecycleBins() throws BadRequestException {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/incidents").toUriString());
        GreenLightResponse<?> greenLightResponse = new GreenLightResponse<>(incidentService.getAllRecycleBin(), null);
        return ResponseEntity.created(uri).body(greenLightResponse);
    }

    @DeleteMapping(value = "/delete-recycle-bin")
    public ResponseEntity<GreenLightResponse<?>> delteReqycleBin(@RequestParam("delete_id") Long id) throws BadRequestException {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/incidents").toUriString());
        GreenLightResponse<?> greenLightResponse = new GreenLightResponse<>(incidentService.deleteRecycleBin(id), null);
        return ResponseEntity.created(uri).body(greenLightResponse);
    }
}

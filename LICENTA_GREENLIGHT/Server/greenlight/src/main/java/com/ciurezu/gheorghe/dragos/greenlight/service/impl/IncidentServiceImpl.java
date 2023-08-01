package com.ciurezu.gheorghe.dragos.greenlight.service.impl;

import com.ciurezu.gheorghe.dragos.greenlight.data.entity.User;
import com.ciurezu.gheorghe.dragos.greenlight.data.entity.incident.ActiveIncidentsEntity;
import com.ciurezu.gheorghe.dragos.greenlight.data.entity.incident.ActiveIncidentsPhotoEntity;
import com.ciurezu.gheorghe.dragos.greenlight.data.entity.incident.InactiveIncidentsEntity;
import com.ciurezu.gheorghe.dragos.greenlight.data.entity.incident.InactiveIncidentsPhotoEntity;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.request.RecycleBinDto;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.shared.IncidentDTO;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.shared.IncidentWithPhotoDTO;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.shared.SolveIncidentDTO;
import com.ciurezu.gheorghe.dragos.greenlight.exception.BadRequestException;
import com.ciurezu.gheorghe.dragos.greenlight.repository.ActiveIncidentRepository;
import com.ciurezu.gheorghe.dragos.greenlight.repository.InactiveIncidentRepository;
import com.ciurezu.gheorghe.dragos.greenlight.repository.UserRepository;
import com.ciurezu.gheorghe.dragos.greenlight.service.ImageService;
import com.ciurezu.gheorghe.dragos.greenlight.service.IncidentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class IncidentServiceImpl implements IncidentService {
    private final ActiveIncidentRepository activeIncidentRepository;
    private final InactiveIncidentRepository inactiveIncidentRepository;
    private final UserRepository userRepository;
    private final ImageService imageService;
    private final ModelMapper mapper;

    @Override
    public Boolean saveIncident(IncidentDTO incidentDTO, List<MultipartFile> photos) {
        //Get Local Date
        LocalDate currentDate = LocalDate.now();
        String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        //Get Local Time
        LocalTime currentTime = LocalTime.now();
        String formattedTime = currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        ActiveIncidentsEntity activeIncidents = mapper.map(incidentDTO, ActiveIncidentsEntity.class);
        activeIncidents.setCreatedAtDate(formattedDate);
        activeIncidents.setCreatedAtTime(formattedTime);

        Set<ActiveIncidentsPhotoEntity> activeIncidentsPhotoEntities = new HashSet<>();

        List<String> photosUrl = photos.stream().map(imageService::uploadPhoto).collect(Collectors.toList());

        ActiveIncidentsEntity finalActiveIncidents = activeIncidents;
        photosUrl.forEach(photoUrl -> {
            ActiveIncidentsPhotoEntity activeIncidentsEntity = new ActiveIncidentsPhotoEntity();
            activeIncidentsEntity.setPhotoUrl(photoUrl);
            activeIncidentsEntity.setActiveIncidents(finalActiveIncidents);
            activeIncidentsPhotoEntities.add(activeIncidentsEntity);
        });

        activeIncidents.setActiveIncidentsPhotoEntities(activeIncidentsPhotoEntities);

        activeIncidents = activeIncidentRepository.save(activeIncidents);
        return activeIncidents.getId() != null;

    }

    @Override
    public String updateIncident(IncidentDTO incidentDTO) {
        return null;
    }

    @Override
    public String deleteIncident(IncidentDTO incidentDTO) {
        return null;
    }

    @Override
    public List<IncidentWithPhotoDTO> getIncidents() {
        return activeIncidentRepository
                .findAllIncidents()
                .stream()
                .map((incidentEntity) -> mapper.map(incidentEntity, IncidentWithPhotoDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public IncidentDTO getIncident(IncidentDTO incidentDTO) {
        return null;
    }

    @Transactional(rollbackFor = BadRequestException.class)
    @Override
    public Boolean solveIncidents(SolveIncidentDTO solveIncidentDTO) throws BadRequestException {
        ActiveIncidentsEntity activeIncidentsEntity = activeIncidentRepository.findIncidentsById(solveIncidentDTO.incidentId);

        if (activeIncidentsEntity == null) {
            throw new BadRequestException("Incident not found!");

        }

        InactiveIncidentsEntity inactiveIncidents = mapper.map(activeIncidentsEntity, InactiveIncidentsEntity.class);


        // inactive incident photo
        Set<InactiveIncidentsPhotoEntity> inactiveIncidentsPhotoEntity = activeIncidentsEntity
                .getActiveIncidentsPhotoEntities()
                .stream()
                .map(incidentPhoto ->
                        mapper.map(incidentPhoto, InactiveIncidentsPhotoEntity.class))
                .collect(Collectors.toSet());

        inactiveIncidentsPhotoEntity.forEach(s -> s.setInactiveIncidents(inactiveIncidents));

        activeIncidentRepository.delete(activeIncidentsEntity);

        inactiveIncidents.setInactiveIncidentsPhotoEntities(inactiveIncidentsPhotoEntity);
        InactiveIncidentsEntity solvedIncident = inactiveIncidentRepository.save(inactiveIncidents);

        if (solvedIncident.getId() == null) {
            throw new BadRequestException("Something went wrong");
        }

        updateUsersPoints(solveIncidentDTO.users);

        return true;
    }

    @Override
    public Boolean saveRecycleBin(IncidentDTO incidentDTO) {
        //Get Local Date
        LocalDate currentDate = LocalDate.now();
        String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        //Get Local Time
        LocalTime currentTime = LocalTime.now();
        String formattedTime = currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        ActiveIncidentsEntity activeIncidents = mapper.map(incidentDTO, ActiveIncidentsEntity.class);
        activeIncidents.setCreatedAtDate(formattedDate);
        activeIncidents.setCreatedAtTime(formattedTime);

        activeIncidents = activeIncidentRepository.save(activeIncidents);
        return activeIncidents.getId() != null;
    }

    @Override
    public List<RecycleBinDto> getAllRecycleBin() {
        return activeIncidentRepository
                .findAllRecycleBin()
                .stream()
                .map((incidentEntity) -> mapper.map(incidentEntity, RecycleBinDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Boolean deleteRecycleBin(Long id) {
        activeIncidentRepository.deleteById(id);
        return true;
    }

    private void updateUsersPoints(Set<String> userList) {
        Set<User> users = userList.stream()
                .map(userRepository::findByUsername)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        for (User actualUser : users) {
            Long currPoints = actualUser.getScore();
            actualUser.setScore(currPoints + 1);
            userRepository.save(actualUser);
        }
    }
}

package com.ciurezu.gheorghe.dragos.greenlight.service;

import com.ciurezu.gheorghe.dragos.greenlight.data.model.request.RecycleBinDto;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.shared.IncidentDTO;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.shared.IncidentWithPhotoDTO;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.shared.SolveIncidentDTO;
import com.ciurezu.gheorghe.dragos.greenlight.exception.BadRequestException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IncidentService {
    Boolean saveIncident(IncidentDTO incidentDTO, List<MultipartFile> photos);
    String updateIncident(IncidentDTO incidentDTO);
    String deleteIncident(IncidentDTO incidentDTO);
    List<IncidentWithPhotoDTO> getIncidents();
    IncidentDTO getIncident(IncidentDTO incidentDTO);
    Boolean solveIncidents(SolveIncidentDTO solveIncidentDTO) throws BadRequestException;

    Boolean saveRecycleBin(IncidentDTO incidentDTO);

    List<RecycleBinDto> getAllRecycleBin();
    Boolean deleteRecycleBin(Long id);
}

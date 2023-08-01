package com.ciurezu.gheorghe.dragos.greenlight.data.model.shared;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class SolveIncidentDTO implements Serializable {
    @JsonProperty("incident_id")
    public Long incidentId;
    @JsonProperty("users")
    public Set<String> users;

}

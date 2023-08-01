package com.ciurezu.gheorghe.dragos.greenlight.data.network_model.solve_incident

import com.google.gson.annotations.SerializedName

data class SolveIncident(
    @SerializedName("incident_id")
    var incidentId : Long,
    @SerializedName("users")
    var users: HashSet<String>
)

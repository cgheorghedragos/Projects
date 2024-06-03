package com.example.Tap.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize
public class UserDetailsDTO {
    Long id;
    String username;
    String photoUrl;
    Set<SimpleUserDTO> friends;
    Set<SimpleUserDTO> pendingFriends;
    List<ExperienceDTO> experiences;
    List<StudiesDTO> studies;
    Boolean isPersonalAccount;
}

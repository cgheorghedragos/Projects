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
public class UserDTO {
    Long id;
    String username;
    String password;
    String photoUrl;
    Set<UserDTO> friends;
    Set<UserDTO> pendingFriends;
    List<ExperienceDTO> experiences;
    List<StudiesDTO> studies;
}

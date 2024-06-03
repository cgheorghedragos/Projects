package com.example.Tap.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity(name = "photos")
@Table(name = "photos")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PhotosEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "photo_url")
    private String photoUrl = "https://i.sstatic.net/l60Hf.png";

    @ManyToOne
    @JoinColumn(name = "experience_id", nullable = false)
    private ExperienceEntity experience;
}

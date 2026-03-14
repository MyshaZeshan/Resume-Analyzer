package com.mysha.ResumeAnalyzer.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String candidateName;
    private String filePath;
    private LocalDateTime uploadAt;
    @Column(columnDefinition = "TEXT")
    private String extractedText; //text as it can be long
}

package com.mysha.ResumeAnalyzer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class AnalysisResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String jobDescription;
    int matchScore;
    String matchingSkills;
    String missingSkills;
    String recommendation;
    LocalDateTime analyzedAt;
    @ManyToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;

}

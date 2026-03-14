package com.mysha.ResumeAnalyzer.dto;

import lombok.Data;

@Data
public class AnalysisResponse {
    String candidateName;
    int matchScore;
    String matchingSkills;
    String missingSkills;
    String recommendation;
}

package com.mysha.ResumeAnalyzer.repository;

import com.mysha.ResumeAnalyzer.model.AnalysisResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnalysisResultRepository extends JpaRepository<AnalysisResult,Long> {
}

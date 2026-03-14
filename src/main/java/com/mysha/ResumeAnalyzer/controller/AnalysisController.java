package com.mysha.ResumeAnalyzer.controller;

import com.mysha.ResumeAnalyzer.dto.AnalysisResponse;
import com.mysha.ResumeAnalyzer.service.AnalysisService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/analysis")
public class AnalysisController {
    private final AnalysisService analysisService;

    AnalysisController(AnalysisService analysisService)
    {
        this.analysisService=analysisService;
    }

    @PostMapping("/analyze")
    AnalysisResponse analyze(@RequestParam Long resumeId, @RequestParam String jobDescription) throws IOException
    {
        return analysisService.analyzeResume(resumeId,jobDescription);
    }
}

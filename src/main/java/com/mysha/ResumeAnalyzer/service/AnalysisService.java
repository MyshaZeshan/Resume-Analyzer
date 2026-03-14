package com.mysha.ResumeAnalyzer.service;

import com.mysha.ResumeAnalyzer.repository.AnalysisResultRepository;
import com.mysha.ResumeAnalyzer.repository.ResumeRepository;
import org.springframework.stereotype.Service;
import com.mysha.ResumeAnalyzer.dto.AnalysisResponse;
import com.mysha.ResumeAnalyzer.model.Resume;
import java.util.HashMap;
import java.util.Map;
import com.mysha.ResumeAnalyzer.model.AnalysisResult;
import java.time.LocalDateTime;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;

@Service
public class AnalysisService {
    private final AnalysisResultRepository  analysisResultRepository;
    private final ResumeRepository resumeRepository;
    private final RestTemplate restTemplate;

    AnalysisService(AnalysisResultRepository analysisResultRepository,ResumeRepository resumeRepository,RestTemplate restTemplate)
    {
        this.analysisResultRepository = analysisResultRepository;
        this.resumeRepository = resumeRepository;
        this.restTemplate=restTemplate;
    }

    public AnalysisResponse analyzeResume(Long resumeId, String jobDescription) throws IOException {
        // Step 1 - fetch resume from database by id
        // Step 2 - build prompt using resume text and job description
        // Step 3 - call Ollama API
        // Step 4 - parse Ollama response
        // Step 5 - save analysis result to database
        // Step 6 - return AnalysisResponse

        Resume resume = resumeRepository.findById(resumeId)
                .orElseThrow(() -> new RuntimeException("Resume not found with id: " + resumeId));

        String prompt = "You are a resume analyzer. Analyze the following resume against the job description and respond in this EXACT format:\n" +
                "SCORE: (number between 0 and 100)\n" +
                "MATCHING_SKILLS: (comma separated skills found in both resume and job description)\n" +
                "MISSING_SKILLS: (comma separated skills in job description but not in resume)\n" +
                "RECOMMENDATION: (one sentence advice)\n\n" +
                "Resume:\n" + resume.getExtractedText() + "\n\n" +
                "Job Description:\n" + jobDescription;

        // build request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "llama3.2");
        requestBody.put("prompt", prompt);
        requestBody.put("stream", false);

// call Ollama
        String ollamaUrl = "http://localhost:11434/api/generate";
        Map response = restTemplate.postForObject(ollamaUrl, requestBody, Map.class);
        String ollamaResponse = response.get("response").toString();

        String score = "0";
        String matchingSkills = "";
        String missingSkills = "";
        String recommendation = "";

        for (String line : ollamaResponse.split("\n")) {
            if (line.startsWith("SCORE:"))
                score = line.replace("SCORE:", "").trim();
            else if (line.startsWith("MATCHING_SKILLS:"))
                matchingSkills = line.replace("MATCHING_SKILLS:", "").trim();
            else if (line.startsWith("MISSING_SKILLS:"))
                missingSkills = line.replace("MISSING_SKILLS:", "").trim();
            else if (line.startsWith("RECOMMENDATION:"))
                recommendation = line.replace("RECOMMENDATION:", "").trim();
        }

        AnalysisResult result = new AnalysisResult();
        result.setResume(resume);
        result.setJobDescription(jobDescription);
        result.setMatchScore(Integer.parseInt(score));
        result.setMatchingSkills(matchingSkills);
        result.setMissingSkills(missingSkills);
        result.setRecommendation(recommendation);
        result.setAnalyzedAt(LocalDateTime.now());
        analysisResultRepository.save(result);

        AnalysisResponse analysisResponse = new AnalysisResponse();
        analysisResponse.setCandidateName(resume.getCandidateName());
        analysisResponse.setMatchScore(Integer.parseInt(score));
        analysisResponse.setMatchingSkills(matchingSkills);
        analysisResponse.setMissingSkills(missingSkills);
        analysisResponse.setRecommendation(recommendation);
        return analysisResponse;
    }
}

package com.mysha.ResumeAnalyzer.controller;
import java.io.IOException;
import com.mysha.ResumeAnalyzer.model.Resume;
import com.mysha.ResumeAnalyzer.dto.ResumeRequest;
import com.mysha.ResumeAnalyzer.service.ResumeService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/resume")
@RestController
public class ResumeController {
    private final ResumeService resumeService;

    ResumeController(ResumeService resumeService)
    {
        this.resumeService=resumeService;
    }

    @PostMapping("/upload") //modal attribute -> whole file not json
    public Resume uploadResume(@ModelAttribute ResumeRequest resumeRequest) throws IOException {
        return resumeService.saveResume(resumeRequest);
    }
}


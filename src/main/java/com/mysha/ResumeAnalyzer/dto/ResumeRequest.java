package com.mysha.ResumeAnalyzer.dto;
import org.springframework.web.multipart.MultipartFile;
import lombok.Data;

@Data
public class ResumeRequest {
    private String candidateName;
    private MultipartFile file;

}

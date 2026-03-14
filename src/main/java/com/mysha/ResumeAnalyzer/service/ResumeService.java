package com.mysha.ResumeAnalyzer.service;

import org.apache.pdfbox.Loader;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import com.mysha.ResumeAnalyzer.model.Resume;
import com.mysha.ResumeAnalyzer.dto.ResumeRequest;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import com.mysha.ResumeAnalyzer.repository.ResumeRepository;
import org.springframework.stereotype.Service;

@Service
public class ResumeService {
    private final ResumeRepository resumeRepository;

    ResumeService(ResumeRepository resumeRepository){
        this.resumeRepository = resumeRepository;
    }

    public Resume saveResume(ResumeRequest request) throws IOException {
        // Step 1 - save file to uploads folder
        String uploadDir = System.getProperty("user.home") + "/uploads/";
        File dir = new File(uploadDir);
        if (!dir.exists()) dir.mkdirs();

        String filePath = uploadDir + request.getFile().getOriginalFilename();
        File destFile = new File(filePath);
        request.getFile().transferTo(destFile.getAbsoluteFile());
        // Step 2 - extract text from PDF
        PDDocument document = Loader.loadPDF(new File(filePath));
        PDFTextStripper stripper = new PDFTextStripper();
        String extractedText = stripper.getText(document);
        document.close();

        // Step 3 - save to database
        Resume resume = new Resume();
        resume.setCandidateName(request.getCandidateName());
        resume.setFilePath(filePath);
        resume.setUploadAt(LocalDateTime.now());

        resume.setExtractedText(extractedText);
        return resumeRepository.save(resume);
    }

}

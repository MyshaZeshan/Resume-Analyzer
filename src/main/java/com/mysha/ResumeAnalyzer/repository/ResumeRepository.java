package com.mysha.ResumeAnalyzer.repository;
import com.mysha.ResumeAnalyzer.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<Resume,Long> {

}


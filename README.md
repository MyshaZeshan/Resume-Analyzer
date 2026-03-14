# Resume Analyzer

An AI-powered resume analyzer built with Spring Boot, Ollama, and MySQL.
Upload your resume and a job description to get an instant analysis of how well you match the role.

## Features
- Upload PDF resume
- Paste any job description
- Get match score, matching skills, missing skills, and recommendation powered by Ollama LLaMA 3.2
- Results saved to database

## Tech Stack
- Java 17
- Spring Boot 3
- MySQL
- Ollama (LLaMA 3.2)
- Docker & Docker Compose
- Apache PDFBox
- Lombok

## How to Run

### Locally
1. Make sure MySQL is running and create database:
```sql
CREATE DATABASE resume_analyzer;
```
2. Update `application.yaml` with your MySQL password
3. Make sure Ollama is running:
```bash
ollama run llama3.2
```
4. Run the Spring Boot app

### With Docker
```bash
docker-compose up --build
```

## API Endpoints

### Upload Resume
```
POST /api/resume/upload
Body: form-data
  - candidateName (text)
  - file (PDF)
```

### Analyze Resume
```
POST /api/analysis/analyze?resumeId={id}&jobDescription={text}
```

## Sample Response
```json
{
    "candidateName": "Roronoa Zoro",
    "matchScore": 60,
    "matchingSkills": "Spring Boot, MySQL, Java, REST API",
    "missingSkills": "None",
    "recommendation": "Consider highlighting MySQL and REST API experience"
}
```

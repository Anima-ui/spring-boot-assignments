# **File Upload & Analyzer Service**

## Description

Spring Boot service that accepts a text file upload (.txt) via multipart/form-data and analyzes its content without saving it to disk.

## Requirements

- Endpoint: POST `/upload`
- Accept a single text file (`text/plain`, max size 5MB) via `MultipartFile`
- Analyze file content to get: total lines, total words, empty lines, average words per line, the longest word
- Return results as JSON
- Validate file type and size; return HTTP 400 on invalid input
- Handle exceptions globally

## Notes 

- You can access the docs by searching for `localhost:8080/swagger-ui-docs` in your browser and then search for the docs using: `/api-docs/fileAnalyzer`
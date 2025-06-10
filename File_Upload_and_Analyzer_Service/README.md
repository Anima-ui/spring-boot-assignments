# **File Upload & Analyzer Service**

## Description

Spring Boot service that accepts a text file upload (.txt) via multipart/form-data and analyzes its content without saving it to disk.

## Requirements

- Endpoint: POST `/upload`
- Accept a single text file (`text/plain`, max size 5MB) via `MultipartFile`
- Analyze file content to get: total lines, total words, empty lines, average words per line, longest word
- Return results as JSON
- Validate file type and size; return HTTP 400 on invalid input
- Handle exceptions globally

## Notes 

- Use Swagger/OpenAPI for API documentation
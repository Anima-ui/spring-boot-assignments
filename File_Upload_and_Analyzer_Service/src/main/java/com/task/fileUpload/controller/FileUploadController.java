package com.task.fileUpload.controller;

import com.task.fileUpload.model.FileAnalysisResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/upload")
public class FileUploadController {

    @PostMapping
    public FileAnalysisResult uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }
        if(!Objects.equals(file.getContentType(), "text/plain")) {
            throw new IllegalArgumentException("File is not a text file");
        }
        if (file.getSize() > 5 * 1024 * 1024) {
            throw new IllegalArgumentException("File size is too big");
        }
        try(BufferedReader reader = new BufferedReader
                (new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))){

            List<String> lines = reader.lines().toList();
            long totalLines = lines.size();
            long totalWords = lines.stream().mapToLong(String::length).sum();
            long emptyLines = lines.stream().filter(String::isBlank).count();
            double averageWordsPerLine = totalWords / (double) totalLines;
            String longestWord = lines.stream()
                    .flatMap(line -> Arrays.stream(line.trim().split("\\s+")))
                    .filter(word -> !word.isEmpty())
                    .max(Comparator.comparingInt(String::length))
                    .orElse("");

            return new FileAnalysisResult(totalLines, totalWords, emptyLines, averageWordsPerLine, longestWord);

        }catch (IOException e) {
            throw new IllegalArgumentException("File is not a text file");
        }
    }
}

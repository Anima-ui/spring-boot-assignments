package com.task.fileUpload.controller;

import com.task.fileUpload.model.FileAnalysisResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@Tag(name = "File analyzer", description = "API for analysis a .txt file")
public class FileUploadController {

    @Operation(summary = "Upload a file", description = "Takes a file and returns the info")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The analysis was successful",
                    content = @Content(schema = @Schema(implementation = FileAnalysisResult.class))
            ),
            @ApiResponse(responseCode = "400", description = "Something went wrong",
                    content = @Content(schema = @Schema())
            )
    })
    @PostMapping
    public ResponseEntity<FileAnalysisResult> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(!Objects.equals(file.getContentType(), "text/plain")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (file.getSize() > 5 * 1024 * 1024) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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

            return new ResponseEntity<>(new FileAnalysisResult(
                    totalLines,
                    totalWords,
                    emptyLines,
                    averageWordsPerLine,
                    longestWord),
                    HttpStatus.OK);

        }catch (IOException e) {
            throw new IllegalArgumentException("File is not a text file");
        }
    }
}

package com.task.fileUpload.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileAnalysisResult {
    private long totalLines;
    private long totalWords;
    private long emptyLines;
    private double averageWordsPerLine;
    private String longestWord;
}

package com.bierpdv.projetointegrador.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class BugTrackService {

    private static final String FILE_PATH = "bug-tracker.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void reportBug(String testName, String errorMessage) {
        try {
            Map<String, String> bugReport = new HashMap<>();
            bugReport.put("testName", testName);
            bugReport.put("errorMessage", errorMessage);

            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.createNewFile();
            }

            objectMapper.writeValue(file, bugReport);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

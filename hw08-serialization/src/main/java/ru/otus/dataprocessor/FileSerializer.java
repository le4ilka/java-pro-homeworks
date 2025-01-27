package ru.otus.dataprocessor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class FileSerializer implements Serializer {
    private String fileName;

    public FileSerializer(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void serialize(Map<String, Double> data) {
        // формирует результирующий json и сохраняет его в файл
        ObjectMapper mapper = JsonMapper.builder().build();
        Map<String, Double> sortedMap = new TreeMap<>(data);
        try {

            mapper.writeValue(new File(fileName), sortedMap);

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}

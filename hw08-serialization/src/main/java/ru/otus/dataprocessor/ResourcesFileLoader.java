package ru.otus.dataprocessor;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.model.Measurement;

public class ResourcesFileLoader implements Loader {

    private String fileName;
    private ObjectMapper mapper = new ObjectMapper();
    public ResourcesFileLoader(String fileName) {
        this.fileName = fileName;
    }
    private static final Logger log = LoggerFactory.getLogger(ResourcesFileLoader.class);

    @Override
    public List<Measurement> load() {
        // читает файл, парсит и возвращает результат
        try (InputStream inputStream = ResourcesFileLoader.class.getClassLoader().getResourceAsStream(fileName)) {
            if (inputStream == null) {
                log.info("File not found in resources");
                throw new IOException("File not found in resources: " + fileName);
            }
            return mapper.readValue(inputStream, new TypeReference<>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            log.info("Can't return parsing result");
            return Collections.emptyList();
        }
    }
}

package com.zagar.email;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by naayadaa on 03.05.17.
 */
public class JsonEmailRepository {

    private String configFileName;
    private ObjectMapper objectMapper;

    public JsonEmailRepository(String configFileName, ObjectMapper objectMapper) {
        this.configFileName = configFileName;
        this.objectMapper = objectMapper;
    }

    public List<Email> list() {
        try(InputStream inputStream = new FileInputStream(configFileName)) {
            CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(List.class, Email.class);

            return objectMapper.readValue(inputStream, listType);
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to read " + configFileName);
        }
    }

    public Email create(Email config) {
        List<Email> configList = list();
        configList.add(config);

        updateStorageFile(configList);

        return config;
    }

    private void updateStorageFile(List<Email> configList) {
        File configFile = new File(configFileName);
        if (!configFile.exists())
            try {
                configFile.getParentFile().mkdirs();
                configFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("Unable to create " + configFileName);

            }

        try {
            objectMapper.writeValue(configFile, configList);
        } catch (IOException e) {
            throw new RuntimeException("Unable to write " + configFileName);
        }
    }
}

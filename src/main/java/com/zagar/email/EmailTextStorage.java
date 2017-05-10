package com.zagar.email;

import org.springframework.core.io.Resource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by naayadaa on 05.05.17.
 */
public class EmailTextStorage {

    private String terraSubject;

    private Resource terraText;

    private Map<RequestOrigin,String> textsMap = new HashMap<>();

    private Map<RequestOrigin,String> subjectMap = new HashMap<>();

    public EmailTextStorage(String terraSubject, Resource terraText) throws IOException {
        this.terraSubject = terraSubject;
        this.terraText = terraText;

        setSubjectMap();
        setTextsMap();
    }

    public String getText(RequestOrigin requestOrigin){
        return textsMap.get(requestOrigin);
    }

    public String getSubject(RequestOrigin requestOrigin){
        return subjectMap.get(requestOrigin);
    }

    private void setTextsMap() throws IOException {
        InputStream inputStream = terraText.getInputStream();
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        String content = result.toString(StandardCharsets.UTF_8.name());
        //String content = new String(Files.readAllBytes(terraText.getFile().toPath()));
        textsMap.put(RequestOrigin.TERRAGRAM, content);
    }

    private void setSubjectMap() {
        subjectMap.put(RequestOrigin.TERRAGRAM, terraSubject);
    }
}

package com.viewhigh.excel.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class FileUploadTemplateHandler {

    private FileTemplate fileTemplate;

    public FileUploadTemplateHandler() {

    }
    public FileUploadTemplateHandler(String jsonTemplate) {
        initFileTemplate(jsonTemplate);
    }

    private void initFileTemplate(String jsonTemplate) {
        ObjectMapper mapper = new ObjectMapper();

        try {

            Resource resource = new ClassPathResource(jsonTemplate);
            // read from file, convert it to class
            //readValue到一个实体,FileTemplate中
            fileTemplate = mapper.readValue(resource.getInputStream(), FileTemplate.class);
            // display to console
            System.out.println(fileTemplate);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileTemplate getFileTemplate() {
        return fileTemplate;
    }

}

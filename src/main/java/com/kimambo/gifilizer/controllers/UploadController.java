package com.kimambo.gifilizer.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.UUID;

@RestController
public class UploadController {

    private final static Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    //TODO: put this into env variables
    private String FileLocation = "/home/max/dev/gifilizer/tmp";

    @PostMapping(value = "/upload", produces = MediaType.IMAGE_GIF_VALUE)
    public String Upload(@RequestParam("file")MultipartFile file) throws IOException {

        //TODO: add other parameters if necessary
        String tempFileName = UUID.randomUUID().toString() +".mp4";
        String tempFilePath = FileLocation + "/"+ tempFileName;
        File uploadedFile = new File(tempFilePath);

        // save it to temp
        try {
            file.transferTo(uploadedFile);

        } catch (IOException e) {
            log.error(String.format("My exception %s", e.getMessage()));
            // create the path and try again
            log.info("Temp directory not found, creating one");
            File tmpDir = new File(FileLocation);
            tmpDir.mkdir();
            log.info(String.format("Created temp directory at %s", FileLocation));
            file.transferTo(uploadedFile);
            return tempFilePath;
        }


        log.info(String.format("Uploaded file saved to %s", tempFilePath));

        // do the convertion

        return tempFilePath;
    }
}

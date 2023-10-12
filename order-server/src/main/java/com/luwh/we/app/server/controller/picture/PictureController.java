package com.luwh.we.app.server.controller.picture;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

/**
 * @author lu.wh
 * @date 2023/10/12 14/02/33
 * @description
 */
@CrossOrigin
@RestController
@RequestMapping("/picture")
public class PictureController {

    @GetMapping(value = "/visit", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] visitPicture(@RequestParam("filePath") String filePath){
        try {
            File file = new File(filePath);
            return Files.readAllBytes(file.toPath());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

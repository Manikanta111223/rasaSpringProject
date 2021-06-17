package com.gapbot.demo.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("/product")
public class RasaController {

    @GetMapping(value = "/byBrandColor", produces = {MediaType.IMAGE_JPEG_VALUE})
    public @ResponseBody
    byte[] getProdustByBrandcolor(HttpServletRequest request,
                                  @RequestParam(value = "color", required = false) String color,

                                  @RequestParam(value = "brand", required = false) String brand) throws IOException {

        InputStream in = getClass()
                .getResourceAsStream("/src/static/images/blue.jpg");
        return IOUtils.toByteArray(in);
    }

    @GetMapping(value = "/image", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<Resource> image() throws IOException {
        final ByteArrayResource inputStream = new ByteArrayResource(Files.readAllBytes(Paths.get(
                "/src/static/images/blue.jpg"
        )));
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentLength(inputStream.contentLength())
                .body(inputStream);

    }
}

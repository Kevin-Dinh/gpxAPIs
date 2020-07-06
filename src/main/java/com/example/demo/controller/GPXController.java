package com.example.demo.controller;

import com.example.demo.core.GenericApiResultDto;
import com.example.demo.core.ParsingException;
import com.example.demo.dto.GPXDto;
import com.example.demo.dto.response.GPXResponseDto;
import com.example.demo.service.gpxService.ParseService;
import com.example.demo.utils.FileHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.concurrent.Callable;

@RestController
@EnableAsync
@RequestMapping("/api/")
public class GPXController {

    @Autowired
    private ParseService parseService;
    private final Logger logger = LoggerFactory.getLogger(getClass());


    @PostMapping(path = "uploadGPX")
    @ResponseBody
    public Callable<ResponseEntity<GPXResponseDto>> uploadGPX (
            HttpServletRequest request,
            @RequestParam("file") MultipartFile file) {

        return () -> {
            logger.info("Start parsing file");
            GPXResponseDto gpxResponseDto = new GPXResponseDto();

            GPXDto gpxDto = parseService.parseFile(file);

            gpxResponseDto.setGpxDto(gpxDto);
            gpxResponseDto.setStatusCode(HttpStatus.OK);
            return ResponseEntity.status(gpxResponseDto.getStatusCode()).body(gpxResponseDto);
        };
    }

    @GetMapping(path = "gpx-info")
    @ResponseBody
    public Callable<ResponseEntity<GPXResponseDto>> gpxInfo (
            HttpServletRequest request,
            @RequestParam int userId) {

        return () -> {
            logger.info("Getting details for user with id = " + userId);
            GPXResponseDto gpxResponseDto = parseService.getDetails(userId);
            return ResponseEntity.status(gpxResponseDto.getStatusCode()).body(gpxResponseDto);
        };
    }

    @GetMapping(path = "latest-tracks")
    @ResponseBody
    public Callable<ResponseEntity<GPXResponseDto>> getLatestTracks (
            HttpServletRequest request) {

        return () -> {
            GPXResponseDto gpxResponseDto = new GPXResponseDto();
            return ResponseEntity.status(gpxResponseDto.getStatusCode()).body(gpxResponseDto);
        };
    }
}

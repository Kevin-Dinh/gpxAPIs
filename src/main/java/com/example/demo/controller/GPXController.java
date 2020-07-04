package com.example.demo.controller;

import com.example.demo.core.GenericApiResultDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.Callable;

@RestController
@EnableAsync
@RequestMapping("/api/")
public class GPXController {

    @GetMapping(path = "uploadGPX")
    @ResponseBody
    public Callable<ResponseEntity<GenericApiResultDto>> uploadGPX (
            HttpServletRequest request) {

        return () -> {

            GenericApiResultDto genericApiResultDto = new GenericApiResultDto();
            genericApiResultDto.setStatusCode(HttpStatus.OK);
            genericApiResultDto.setMessage("For testing purposes!!!!");
            return ResponseEntity.status(genericApiResultDto.getStatusCode()).body(genericApiResultDto);
        };
    }
}

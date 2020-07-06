package com.example.demo.dto.response;

import com.example.demo.core.GenericApiResultDto;
import com.example.demo.dto.GPXDto;

public class GPXResponseDto extends GenericApiResultDto {
    private GPXDto gpxDto;

    public GPXDto getGpxDto() {
        return gpxDto;
    }

    public void setGpxDto(GPXDto gpxDto) {
        this.gpxDto = gpxDto;
    }
}

package com.example.demo.core;

import com.fasterxml.jackson.annotation.JsonGetter;
import org.springframework.http.HttpStatus;

public class GenericApiResultDto {

    private HttpStatus statusCode;

    public GenericApiResultDto(HttpStatus statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public GenericApiResultDto() {
    }

    public static GenericApiResultDtoBuilder genericResultBuilder() {
        return new GenericApiResultDtoBuilder();
    }

    @JsonGetter
    public int getStatusCodeNumber(){
        if(statusCode == null){
            return HttpStatus.OK.value();
        }
        return statusCode.value();
    }

    // @ApiModelProperty(value = "For global message regardless the fields which were sent in the request")
    private String message;

    public static com.example.demo.core.GenericApiResultDto ok() {
        return com.example.demo.core.GenericApiResultDto.genericResultBuilder()
                .statusCode(HttpStatus.OK)
                .build();
    }

    public HttpStatus getStatusCode() {
        return this.statusCode;
    }

    public String getMessage() {
        return this.message;
    }


    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toString() {
        return "GenericApiResultDto(statusCode=" + this.getStatusCode() + ", message=" + this.getMessage() + ")";
    }

    public static class GenericApiResultDtoBuilder {
        private HttpStatus statusCode;
        private String message;

        GenericApiResultDtoBuilder() {
        }

        public GenericApiResultDto.GenericApiResultDtoBuilder statusCode(HttpStatus statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        public GenericApiResultDto.GenericApiResultDtoBuilder message(String message) {
            this.message = message;
            return this;
        }
        public GenericApiResultDto build() {
            return new GenericApiResultDto(statusCode, message);
        }
    }
}

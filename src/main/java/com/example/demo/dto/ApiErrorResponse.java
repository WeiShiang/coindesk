package com.example.demo.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public class ApiErrorResponse {

    // in case we want to provide API based custom error code
    private String error_code;

    // customer error message to the client API
    private String message;


    public static final class ApiErrorResponseBuilder {
        private String error_code;
        private String message;

        public ApiErrorResponseBuilder() {
        }

        public static ApiErrorResponseBuilder anApiErrorResponse() {
            return new ApiErrorResponseBuilder();
        }

        public ApiErrorResponseBuilder withError_code(String error_code) {
            this.error_code = error_code;
            return this;
        }

        public ApiErrorResponseBuilder withMessage(String message) {
            this.message = message;
            return this;
        }

        public ApiErrorResponse build() {
            ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
            apiErrorResponse.error_code = this.error_code;
            apiErrorResponse.message = this.message;
            return apiErrorResponse;
        }

    }

    @Override
    public String toString() {
        return "{" +
                "error_code='" + error_code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}

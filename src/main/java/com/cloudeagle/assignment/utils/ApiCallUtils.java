package com.cloudeagle.assignment.utils;

import com.cloudeagle.assignment.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class ApiCallUtils {

    SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();

    public <T> ApiResponse makePostRequestWithBody(String url, Object body, HttpHeaders headers, Class<T> responseType) {

        log.info("*********makePostRequestWithBody************");
        log.info("URL = " + url);
        log.info("Headers = " + String.valueOf(headers));
        if(body.toString().length() < 2000){
            log.info("Request body = " + body);
        }
        requestFactory.setConnectTimeout(50000);
        requestFactory.setReadTimeout(50000);

        try {
            RestTemplate restTemplate = new RestTemplate(requestFactory);
            ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(body, headers), responseType);
            return new ApiResponse(true,"success",response.getBody());
        } catch (Exception e) {
            log.error("Error while calling API",e);
            return new ApiResponse(false,"error",e.getMessage());
        }
    }

    public ApiResponse makePostRequestWithoutBody(String url, HttpHeaders headers) {
        log.info("*********makePostRequestWithoutBody************");
        log.info("URL = " + url);
        log.info("Headers = " + String.valueOf(headers));
        requestFactory.setConnectTimeout(50000);
        requestFactory.setReadTimeout(50000);

        try {
            RestTemplate restTemplate = new RestTemplate(requestFactory);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>("null",headers), String.class);
            return new ApiResponse(true,"success",response.getBody());
        } catch (Exception e) {
            log.error("Error while calling API",e);
            return new ApiResponse(false, "error", e.getMessage());
        }
    }

}

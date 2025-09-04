package com.cloudeagle.assignment.service;

import com.cloudeagle.assignment.response.ApiResponse;
import com.cloudeagle.assignment.utils.ApiCallUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
@Slf4j
public class OAuthService {

    @Autowired
    private ApiCallUtils apiCallUtils;
    @Value("${dropbox.token-url}")
    private String tokenUrl;
    @Value("${dropbox.client-id}")
    private String clientId;
    @Value("${dropbox.client-secret}")
    private String clientSecret;
    @Value("${dropbox.redirect-uri}")
    private String redirectUri;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public String getAccessToken(String code){
        try{
            log.info("Inside getAccessToken Method.........");
            log.info("Calling token API for fetching token......");

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type","application/x-www-form-urlencoded");

            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            formData.add("code", code);
            formData.add("grant_type", "authorization_code");
            formData.add("client_id", clientId);
            formData.add("client_secret", clientSecret);
            formData.add("redirect_uri", redirectUri);

            ApiResponse response = apiCallUtils.makePostRequestWithBody(tokenUrl,formData,headers, JsonNode.class);
            log.info("response from token API is:{}", response);

            if(!response.isStatus()){
                return null;
            }

            JsonNode data = objectMapper.readTree(String.valueOf(response.getData()));
            if(data==null || data.isEmpty()){
                return null;
            }

            JsonNode token = data.get("access_token");
            if(token==null){
                return null;
            }

            log.info("access token is: " + token);
            return token.asText();
        } catch (Exception e) {
            log.error("Exception occurred while getting access token", e);
            return null;
        }
    }
}

package com.cloudeagle.assignment.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OAuthService {
    public String getAccessToken(String code){
        try{

        } catch (Exception e) {
            log.error("Exception occurred while getting access token", e);
            return null;
        }
    }
}

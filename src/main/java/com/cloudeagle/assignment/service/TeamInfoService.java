package com.cloudeagle.assignment.service;

import com.cloudeagle.assignment.response.ApiResponse;
import com.cloudeagle.assignment.utils.ApiCallUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TeamInfoService {

    @Autowired
    private OAuthService oAuthService;
    @Autowired
    private ApiCallUtils apiCallUtils;
    @Value("${dropbox.team-info-url}")
    private String teamInfoUrl;

    public void getTeamInfo(String code) {
        try{
            log.info("Fetching access token.....");
            String token = oAuthService.getAccessToken(code);
            if(token==null || token.isEmpty()){
                throw new RuntimeException("Access token Not Present, Unauthorized");
            }

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Authorization","Bearer " + token);
            httpHeaders.add("Content-Type","application/json");

            ApiResponse response = apiCallUtils.makePostRequestWithoutBody(teamInfoUrl,httpHeaders);
            if(!response.isStatus()){
                throw new RuntimeException("Error calling API: " + response.getData());
            }

            String data = String.valueOf(response.getData());

            log.info("Response from team info is: " + data);
        } catch (Exception e) {
            log.error("Error getting team Information",e);
        }
    }
}

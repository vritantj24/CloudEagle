package com.cloudeagle.assignment.service;

import com.cloudeagle.assignment.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TeamInfoService {

    public ResponseEntity<ApiResponse> getTeamInfo(String code) {
        try{
            System.out.println("Team Info called");
            return ResponseEntity.status(200).body(new ApiResponse(true,"Data Success",null));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ApiResponse(false,"Some error occurred while fetching team info",null));
        }
    }
}

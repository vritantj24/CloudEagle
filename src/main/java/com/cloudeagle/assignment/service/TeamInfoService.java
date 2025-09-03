package com.cloudeagle.assignment.service;

import com.cloudeagle.assignment.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TeamInfoService {

    public ResponseEntity<ApiResponse> getTeamInfo() {
        try{
            System.out.println("Team Info called");
            return ResponseEntity.status(200).body(new ApiResponse(true,"Data Success",null));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ApiResponse(false,"Some error occurred while fetching team info",null));
        }
    }
}

package com.cloudeagle.assignment.controller;

import com.cloudeagle.assignment.response.ApiResponse;
import com.cloudeagle.assignment.service.TeamInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/team/")
@RestController
public class TeamInfoController {

    @Autowired
    TeamInfoService teamInfoService;

    @GetMapping("info")
    public ResponseEntity<ApiResponse> getTeamInfo() {
        return teamInfoService.getTeamInfo();
    }
}

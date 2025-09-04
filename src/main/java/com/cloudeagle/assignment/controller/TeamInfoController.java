package com.cloudeagle.assignment.controller;

import com.cloudeagle.assignment.service.TeamInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/api/team/")
@RestController
public class TeamInfoController {

    @Autowired
    TeamInfoService teamInfoService;

    @GetMapping("info")
    public void getTeamInfo(@RequestParam(name = "code") String code) {
        log.info("Callback for Team Info Received");
        teamInfoService.getTeamInfo(code);
    }
}

package com.cloudeagle.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.*;
import java.net.URI;

@SpringBootApplication
public class CloudEagleAssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudEagleAssignmentApplication.class, args);
		System.out.println("*****************Application Started********************");
		try {
			Desktop desktop = Desktop.getDesktop();
			desktop.browse(new URI("https://www.dropbox.com/oauth2/authorize?response_type=code&client_id=2oxb7jo695kcpe8&redirect_uri=http://localhost:8080/api/team/info&scope=team_info.read"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
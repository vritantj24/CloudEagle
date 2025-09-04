package com.cloudeagle.assignment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.*;
import java.net.URI;

@Slf4j
@SpringBootApplication
public class CloudEagleAssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudEagleAssignmentApplication.class, args);
		System.out.println("*****************Application Started********************");
		String url = "https://www.dropbox.com/oauth2/authorize?response_type=code&client_id=2oxb7jo695kcpe8&redirect_uri=http://localhost:8080/api/team/info&scope=team_info.read";

		try {
			if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
				Desktop.getDesktop().browse(new URI(url));
			} else {
				// Fallback based on OS
				String os = System.getProperty("os.name").toLowerCase();
				Runtime rt = Runtime.getRuntime();

				if (os.contains("win")) {
					rt.exec("rundll32 url.dll,FileProtocolHandler " + url);
				} else if (os.contains("mac")) {
					rt.exec("open " + url);
				} else if (os.contains("nix") || os.contains("nux")) {
					rt.exec("xdg-open " + url);
				} else {
					System.out.println("Unsupported operating system: " + os);
				}
			}
		} catch (Exception e) {
			log.error("Error redirecting auth",e);
		}
	}

}
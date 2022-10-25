package com.example.handlingformsubmission;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GreetingController {

	@GetMapping("/greeting")
	public String greetingForm(Model model) {
		return "greeting";
	}

	@PostMapping("/botnet")
	public String botnetConnect(Model model) {
		Botnet botnet = new Botnet();
		botnet.setIp("1.123.37.68");
		botnet.setPort("80");
		model.addAttribute("botnet", botnet);
		URL url;
		try {
			System.out.println("Sending request to botnet");
			url = new URL("http://1.123.37.68:80");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setConnectTimeout(3000);
			int responseCode = con.getResponseCode();
			System.out.println("GET Response Code :: " + responseCode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "greeting";
	}

}

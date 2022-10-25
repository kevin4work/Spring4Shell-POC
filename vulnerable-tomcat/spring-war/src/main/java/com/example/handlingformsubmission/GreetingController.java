package com.example.handlingformsubmission;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GreetingController {

	@GetMapping("/greeting")
	public String greetingForm(Model model) {
		model.addAttribute("greeting", new Greeting());
		return "greeting";
	}

	@RequestMapping(value="/submit", method=RequestMethod.POST, params="action=list")
	public String list(@ModelAttribute Greeting greeting, Model model) {
		greeting.setInjection(true);
		model.addAttribute("greeting", greeting);
		return "greeting";
	}

	@RequestMapping(value="/submit", method=RequestMethod.POST, params="action=botnet")
	public String botnetConnect(Model model) {
		Botnet botnet = new Botnet();
		botnet.setIp("1.123.37.68");
		botnet.setPort("80");
		model.addAttribute("botnet", botnet);
		model.addAttribute("greeting", new Greeting());
		URL url;
		try {
			System.out.println("Sending request to botnet");
			url = new URL("http://1.123.37.68:80");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setConnectTimeout(2000);
			int responseCode = con.getResponseCode();
			System.out.println("GET Response Code :: " + responseCode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "greeting";
	}

}

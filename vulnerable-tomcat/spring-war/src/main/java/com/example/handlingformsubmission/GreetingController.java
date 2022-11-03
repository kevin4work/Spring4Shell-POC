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

	@GetMapping("/login")
	public String loginForm(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}

	@GetMapping("/user/login")
	public String fakeLoginForm(Model model) {
		model.addAttribute("user", new User());
		return "user/login";
	}

	@PostMapping("/maintenance")
	public String maintenancein(@ModelAttribute User user, Model model) {
		model.addAttribute("user", user);
		System.out.println("username:" + user.getName());
		System.out.println("password:" + user.getPassword());
		return "user/maintenance";
	}

	@GetMapping("/order")
	public String searchOrder(Model model) {
		model.addAttribute("user", new User());
		return "order";
	}

	@PostMapping("/submit")
	public String login(@ModelAttribute User user, Model model) {
		model.addAttribute("user", user);
		// System.out.println("username:" + user.getName());
		// System.out.println("password:" + user.getPassword());
		return "order";
	}

	@RequestMapping(value="/search", method=RequestMethod.POST, params="action=list")
	public String list(@ModelAttribute User user, Model model) {
		user.setInjection(true);
		model.addAttribute("user", user);
		return "order";
	}

	@RequestMapping(value="/search", method=RequestMethod.POST, params="action=botnet")
	public String botnetConnect(Model model) {
		Botnet botnet = new Botnet();
		botnet.setIp("2.56.59.42");
		botnet.setPort("80");
		model.addAttribute("botnet", botnet);
		model.addAttribute("user", new User());
		URL url;
		try {
			System.out.println("Sending request to botnet");
			url = new URL("http://2.56.59.42:80");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setConnectTimeout(2000);
			int responseCode = con.getResponseCode();
			System.out.println("GET Response Code :: " + responseCode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "order";
	}

}

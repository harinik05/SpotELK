package in.harininotes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/*
MessageController: Welcome message in the website!
*/
@Controller
public class MessageController {

	@GetMapping("/welcome")
	public String getWelcomeMsg(Model model) {

		model.addAttribute("msg", "Welcome To Harini Notes App App");

		return "index";
	}
}

package spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegisterController {

	@RequestMapping(value = "/main", method = { RequestMethod.GET, RequestMethod.POST })
	public String main() {
		return "/main";
	}
	
	@RequestMapping(value ="/join", method = RequestMethod.GET)
	public String join() {
		return "/join";
	}
	
	@RequestMapping(value ="/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login() {
		return "/login";
	}
	
	

}

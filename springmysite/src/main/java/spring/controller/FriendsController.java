package spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.friend.Friends;
import spring.friend.FriendsDao;

@Controller
public class FriendsController {
	private FriendsDao friendsDao;

	public FriendsDao getFriendsDao() {
		return friendsDao;
	}

	public void setFriendsDao(FriendsDao friendsDao) {
		this.friendsDao = friendsDao;
	}

	@RequestMapping(value = "/main", method = { RequestMethod.GET, RequestMethod.POST })
	public String main() {
		return "/main";
	}

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {
		return "/join";
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(Friends friends) {
		friendsDao.insert(friends);
		return "redirect:/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "/login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Friends friends) {
		return "redirect:/main";
	}

}

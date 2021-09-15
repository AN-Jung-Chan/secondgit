package spring.friend;

import java.util.Date;

public class FriendsRegisterService {

	private FriendsDao friendsDao;

	public FriendsRegisterService(FriendsDao friendsDao) {
		this.friendsDao = friendsDao;
		System.out.println("FriendsRegisterService(FriendsDao)");
	}
	
	public void regist (RegisterRequest req) {
		Friends friends = friendsDao.selectById(req.getId());
		if(friends != null) {
			throw new AlreadyExistingFriendsException(req.getId() + "이미 등록된 아이디입니다.");
		}
		Friends newFriends = new Friends(
									req.getId(),
									req.getPassword(),
									req.getName(),
									req.getGender(),
									req.getEmail(),
									new Date()
									);
		friendsDao.insert(newFriends);
	}

}

package spring.friend;

import java.util.Date;

public class FriendsRegisterService {

	private FriendsDao friendsDao;

	public FriendsRegisterService(FriendsDao friendsDao) {
		this.friendsDao = friendsDao;
	}
	
	public void regist (RegisterRequest req) {
		Friends friends = friendsDao.selectById(req.getId());
		if(friends != null) {
			throw new AlreadyExistingFriendsException("dup id" + req.getId());
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

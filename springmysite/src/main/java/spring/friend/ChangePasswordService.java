package spring.friend;

public class ChangePasswordService {
	private FriendsDao friendsDao;

	public ChangePasswordService(FriendsDao friendsDao) {
		this.friendsDao = friendsDao;
	}

	public void changePassword(String id, String oldPwd, String newPwd) {
		Friends friends = friendsDao.selectById(id);
		if (friends == null) {
			throw new FriendsNotFoundException();
		}
		friends.changePassword(oldPwd, newPwd);
		friendsDao.update(friends);
	}

}

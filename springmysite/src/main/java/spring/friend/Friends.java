package spring.friend;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("friendsVo")
public class Friends {
	private int num;
	private String id;
	private String password;
	private String name;
	private String gender;
	private String email;
	private Date regdate;

	public Friends() {
		System.out.println("Friends() 생성");
	}

	public Friends(String id, String password, String name, String gender, String email, Date regdate) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.email = email;
		this.regdate = regdate;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	// 암호변경 기능을 구현하는 메서드
	public void changePassword(String oldPassword, String newPassword) {
		if (!password.equals(oldPassword)) {
			throw new IdPasswordNotMatchingException();
		}
		this.password = newPassword;
	}

}

package com.nhs.exercise.restfulservicesnhs.unittest.utility;

import java.util.ArrayList;
import java.util.List;

import com.nhs.exercise.restfulservicesnhs.model.UserBean;

public class TestDataBuilder {

	public UserBean createDummyUser() {
		UserBean user = new UserBean();
		user.setId(1L);
		user.setName("Nitin");
		user.setEmail("nitin@nhstest.com");
		
		return user;
	}
	
	public List<UserBean> createDummyUserList(){
		List<UserBean> userList = new ArrayList<UserBean>();
		userList.add(createDummyUser());
		return userList;
	}
}

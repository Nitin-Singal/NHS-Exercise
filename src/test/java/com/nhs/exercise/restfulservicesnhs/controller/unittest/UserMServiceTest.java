package com.nhs.exercise.restfulservicesnhs.controller.unittest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nhs.exercise.restfulservicesnhs.controller.UserMService;
import com.nhs.exercise.restfulservicesnhs.dao.UserRepository;
import com.nhs.exercise.restfulservicesnhs.model.UserBean;
import com.nhs.exercise.restfulservicesnhs.unittest.utility.TestDataBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserMServiceTest {

	@Autowired
	private UserMService userMService;
	
	@Mock
	private UserRepository userRepository;
	
	private TestDataBuilder testDataBuilder = new TestDataBuilder();
	
	private void init() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	private void testgetAllUsers() {
		init();
		List<UserBean> dummyUserList = testDataBuilder.createDummyUserList();
		when(userRepository.findAll()).thenReturn(dummyUserList);
		List<UserBean> userList = userMService.getAllUsers();
		
		assertTrue(userList.size() == 1);
		assertTrue(userList.get(0).getName().equals("Nitin"));
	}
}

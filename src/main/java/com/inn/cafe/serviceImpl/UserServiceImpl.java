package com.inn.cafe.serviceImpl;

import java.util.Map;
import java.util.Objects;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.inn.cafe.POJO.User;
import com.inn.cafe.constants.CafeConstants;
import com.inn.cafe.dao.UserDao;
import com.inn.cafe.service.UserService;
import com.inn.cafe.utils.CafeUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;
	
	@Override
	public ResponseEntity<String> signUp(Map<String, String> requestMap) {
	  System.out.println("Inside SignUp"+ requestMap);
	 try {
	  if(validateSignUp(requestMap)) {
		  User user =userDao.findbyEmailId(requestMap.get("email"));
		  if(Objects.isNull(user)) {
			  userDao.save(getUserFromMap(requestMap));
			  return CafeUtils.getResponseEntity("Succesfully Registered", HttpStatus.OK);
		  }else {
			  return CafeUtils.getResponseEntity("Email Id ALready Exists",HttpStatus.BAD_REQUEST);
		  }
	  }else {
		  return CafeUtils.getResponseEntity(CafeConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
	  }
	  }catch(Exception ex) {
		  ex.printStackTrace();
	  }
	 return CafeUtils.getResponseEntity(CafeConstants.SOME_THING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
//	private ResponseEntity<String> CafeUtils(String string, HttpStatus badRequest) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	private boolean validateSignUp(Map<String, String> requestMap) {
		if(requestMap.containsKey("name") && requestMap.containsKey("contactNumber")
			&&requestMap.containsKey("email") && requestMap.containsKey("password")) {
			return true;
		}
		return false;
	}
	
	private User getUserFromMap(Map<String, String> requestMap) {
		User user =new User();
		user.setName(requestMap.get("name"));
		user.setContactNumber(requestMap.get("contactNumber"));
		user.setEmail(requestMap.get("email"));
		user.setPassword(requestMap.get("password"));
		user.setStatus("false");
		user.setRole("user");
		return user;
	}

}

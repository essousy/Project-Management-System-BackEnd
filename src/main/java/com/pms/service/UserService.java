package com.pms.service;

import com.pms.model.User;

public interface UserService {
    User findUserProfileByJwt(String jwt) throws Exception;
    User findUserByEmail(String email) throws Exception;
    User findUserById(Long userid) throws Exception;
    User updateUsersProjectSize(User user, int number);


}

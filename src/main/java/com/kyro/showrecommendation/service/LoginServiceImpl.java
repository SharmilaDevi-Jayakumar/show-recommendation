package com.kyro.showrecommendation.service;

import com.kyro.showrecommendation.entities.UserTableEntity;
import com.kyro.showrecommendation.models.UserInfo;
import com.kyro.showrecommendation.repositories.UserTableRepo;
import com.kyro.showrecommendation.repositories.UserTableRepoJpa;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LoginServiceImpl {

    private final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    private UserTableRepoJpa userTableRepoJpa;
    private UserInfo userInfo = new UserInfo();

    public UserInfo login(String userName, String password) {

        try {

            UserTableEntity userTableEntity = userTableRepoJpa.getUserTableEntityByUserNameAndPassword(userName, password);

            if (Objects.nonNull(userTableEntity)) {
                userInfo.setIsRegistered(true);
                userInfo.setUser_id(userTableEntity.getUserId());
            }
            return userInfo;
        }
        catch (Exception e) {
            throw e;
        }
    }

    public UserInfo register(String userName, String password) {

        try {
        //ToDo: Check if the userName is already existing and throw the message to front, Note: Sign in can be split into User email validation and then password verification
            UserTableEntity userTableEntity = new UserTableEntity(userName,password);

            userTableEntity = userTableRepoJpa.save(userTableEntity);
            userInfo.setIsRegistered(true);
            userInfo.setUser_id(userTableEntity.getUserId());
            return userInfo;
        }
        catch (Exception e) {
            logger.error("Error occurred while registering the user"+e.getMessage());
            throw e;
        }
    }

}

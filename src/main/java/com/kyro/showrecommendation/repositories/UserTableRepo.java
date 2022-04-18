package com.kyro.showrecommendation.repositories;

import com.kyro.showrecommendation.entities.UserTableEntity;

public interface UserTableRepo {

    UserTableEntity getUserTableEntityByUserNameAndPassword(String userName, String password);
}

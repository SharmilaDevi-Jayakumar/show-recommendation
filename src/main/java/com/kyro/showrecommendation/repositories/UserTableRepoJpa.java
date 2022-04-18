package com.kyro.showrecommendation.repositories;

import com.kyro.showrecommendation.entities.UserTableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UserTableRepoJpa extends UserTableRepo, JpaRepository<UserTableEntity, Long> {

    UserTableEntity getUserTableEntityByUserNameAndPassword(String userName, String password);
}

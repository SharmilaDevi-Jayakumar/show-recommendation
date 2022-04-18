package com.kyro.showrecommendation.repositories;

import com.kyro.showrecommendation.entities.UserHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserHistoryRepoJpa extends UserHistoryRepo, JpaRepository<UserHistoryEntity, Long> {

    UserHistoryEntity getUserHistoryEntityByUserId (int userId);

}

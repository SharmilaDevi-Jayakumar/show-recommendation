package com.kyro.showrecommendation.repositories;

import com.kyro.showrecommendation.entities.UserHistoryEntity;

public interface UserHistoryRepo {

    UserHistoryEntity getUserHistoryEntityByUserId(Long userId);
}

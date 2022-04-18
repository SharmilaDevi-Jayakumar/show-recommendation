package com.kyro.showrecommendation.service;

import com.kyro.showrecommendation.entities.UserHistoryEntity;
import com.kyro.showrecommendation.models.ShowRecommendationDTO;

public interface ShowRecommendationService {

    ShowRecommendationDTO showDefaultRecommendationByUser(Long userId);

    void storeOrUpdateShowRecommendation(String showId, UserHistoryEntity userHistoryEntity, Long userId);

    String resetUserHistory(Long userID);
}

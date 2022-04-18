package com.kyro.showrecommendation.service;

import com.kyro.showrecommendation.RestClient.TvMazeRestClient;
import com.kyro.showrecommendation.entities.UserHistoryEntity;
import com.kyro.showrecommendation.models.Show;
import com.kyro.showrecommendation.models.ShowRecommendationDTO;
import com.kyro.showrecommendation.repositories.UserHistoryRepoJpa;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ShowRecommendationServiceImpl implements ShowRecommendationService {

    private final Logger logger = LoggerFactory.getLogger(ShowRecommendationServiceImpl.class);

    private final UserHistoryRepoJpa userHistoryRepoJpa;
    private final TvMazeRestClient tvMazeRestClient;
    private Show recommendedShow;
    private ShowRecommendationDTO showRecommendationDTO;

    public ShowRecommendationServiceImpl(UserHistoryRepoJpa userHistoryRepoJpa, TvMazeRestClient tvMazeRestClient) {
        this.userHistoryRepoJpa = userHistoryRepoJpa;
        this.tvMazeRestClient = tvMazeRestClient;
    }

    @Override
    public ShowRecommendationDTO showDefaultRecommendationByUser(Long userId) {

        try {
            UserHistoryEntity showHistoryEntityByUser = userHistoryRepoJpa.getUserHistoryEntityByUserId(userId);
            List<Integer> showHistoryList = new ArrayList<>();
            if(Objects.nonNull(showHistoryEntityByUser)){
               showHistoryList = this.convertListStringToListInteger(showHistoryEntityByUser.getShowHistory());
            }
            String recommendedShowId = getRecommendedShowId(showHistoryList);
            recommendedShow = tvMazeRestClient.getShowRecommendationResponse(recommendedShowId);
            showRecommendationDTO = this.convertRecommendedShowToDto(recommendedShow);
            this.storeOrUpdateShowRecommendation(recommendedShowId,showHistoryEntityByUser,userId);

        } catch (Exception e) {
            logger.error("Error occurred while getting recommendation for the user"+userId);
            e.printStackTrace();
        }
        return showRecommendationDTO;
    }

    @Override
    public void storeOrUpdateShowRecommendation(String showId, UserHistoryEntity userHistoryEntity, Long userId) {

        try {
            if(!Objects.nonNull(userHistoryEntity)) {
                userHistoryEntity = new UserHistoryEntity();
                List<String> showHistoryList = new ArrayList<>();
                userHistoryEntity.setUserId(userId);
                userHistoryEntity.setShowHistory(showHistoryList);
            }
            userHistoryEntity.getShowHistory().add(showId);
            userHistoryRepoJpa.save(userHistoryEntity);
        } catch (Exception e) {
            logger.error("Error occurred while storing the recommendation history in db for the user"+userId);
            throw e;
        }
    }


    public String getRecommendedShowId(List<Integer> showHistoryList) {

        Boolean showIdNotFound = true;
        Integer searchId = 1;

        while (showIdNotFound) {

            if (!showHistoryList.contains(searchId)) {
                showIdNotFound =false;
                return searchId.toString();
            }
            searchId++;
        }
        return searchId.toString();

    }

    public List<Integer> convertListStringToListInteger(List<String> stringListShowHistory) {

        List<Integer> integerListShowHistory = new ArrayList<Integer>();
        integerListShowHistory = stringListShowHistory.stream().map(x ->
                Integer.valueOf(x)).collect(Collectors.toList());
        return integerListShowHistory;
    }

    public ShowRecommendationDTO convertRecommendedShowToDto(Show recommendedShow) {
         ShowRecommendationDTO showRecommendationDTO = new ShowRecommendationDTO();
         showRecommendationDTO.setId(String.valueOf(recommendedShow.getId()));
         showRecommendationDTO.setImage(recommendedShow.getImage().getMedium());
         showRecommendationDTO.setUrl(recommendedShow.getUrl());
         showRecommendationDTO.setLanguage(recommendedShow.getLanguage());
         showRecommendationDTO.setName(recommendedShow.getName());
         showRecommendationDTO.setRating(recommendedShow.getRating().getAverage().toString());
         return showRecommendationDTO;

    }

    @Override
    public String resetUserHistory(Long userId) {
        try{
        UserHistoryEntity showHistoryEntityByUser = userHistoryRepoJpa.getUserHistoryEntityByUserId(userId);
        this.userHistoryRepoJpa.delete(showHistoryEntityByUser);
        return "Success";
        }
        catch (Exception e) {
            logger.error("Error occurred while deleting the record for user"+userId);
            throw e;
        }
        }
}

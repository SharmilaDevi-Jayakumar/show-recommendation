package com.kyro.showrecommendation.RestClient;

import com.kyro.showrecommendation.models.Show;
import com.kyro.showrecommendation.service.ShowRecommendationServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class TvMazeRestClient {

    RestTemplate restTemplate = new RestTemplate();
    Show recommendedShow;
    final static public String tvMazeUrl = "https://api.tvmaze.com/shows/";
    private final Logger logger = LoggerFactory.getLogger(TvMazeRestClient.class);


    public Show getShowRecommendationResponse (String searchId) {
        try {
             recommendedShow = restTemplate.getForObject(tvMazeUrl + searchId, Show.class);
        } catch (RestClientException e) {
            logger.error("Exception occurred while fetching the show details for showID"+searchId);
            e.printStackTrace();
        }
        return recommendedShow;
    }
}

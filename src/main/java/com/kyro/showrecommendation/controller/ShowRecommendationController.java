package com.kyro.showrecommendation.controller;
import com.kyro.showrecommendation.models.ShowRecommendationDTO;
import com.kyro.showrecommendation.models.UserInfo;
import com.kyro.showrecommendation.models.UserLogin;
import com.kyro.showrecommendation.service.ShowRecommendationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.kyro.showrecommendation.service.LoginServiceImpl;

@RestController
@CrossOrigin(origins="https://sharmiladevi-jayakumar.github.io/ui-show-recommendation/")
public class ShowRecommendationController {

    private final LoginServiceImpl loginServiceImpl;
    private final ShowRecommendationService showRecommendationService;
    private final Logger logger = LoggerFactory.getLogger(ShowRecommendationController.class);

    @Autowired
    public ShowRecommendationController(LoginServiceImpl loginServiceImpl, ShowRecommendationService showRecommendationService){
        this.loginServiceImpl = loginServiceImpl;
        this.showRecommendationService = showRecommendationService;
    }

    @PostMapping (value = "/user/login")
    public @ResponseBody
    UserInfo login(@RequestBody UserLogin userLogin) {
        UserInfo userInfo;
        try {
            userInfo = loginServiceImpl.login(userLogin.getUserName(), userLogin.getPassword());

        } catch (Exception e) {
            logger.error("Error occurred while authenticating the user"+e.getMessage());
            throw e;
        }
        return userInfo;
    }

    @PostMapping (value = "/user/register")
    public @ResponseBody
    UserInfo register(@RequestBody UserLogin userLogin) {
        UserInfo userInfo;
        try {
            userInfo = loginServiceImpl.register(userLogin.getUserName(),userLogin.getPassword());

        } catch (Exception e) {
            logger.error("Error occurred while authenticating the user"+e.getMessage());
            throw e;
        }
        return userInfo;
    }


    @GetMapping(value = "/showRecommendationByUserId")
    public @ResponseBody
    ShowRecommendationDTO showDefaultRecommendation(@RequestParam("userId") String user_id) {
        ShowRecommendationDTO showRecommendationDTO;
        try {
                Long userId = Long.valueOf(user_id);
                 showRecommendationDTO = showRecommendationService.showDefaultRecommendationByUser(userId);
                 return showRecommendationDTO;
        }
        catch (Exception e) {
            logger.error("Error occurred while authenticating the user"+e.getMessage());
           throw e;
        }
    }


    @DeleteMapping(value = "/resetUserHistory")
    public @ResponseBody
    void resetUserHistory(@RequestParam("userId") String user_id) {
        try {
            Long userId = Long.valueOf(user_id);
            String deletedMessage = showRecommendationService.resetUserHistory(userId);
        }
        catch (Exception e) {
            logger.error("Error occurred while reseting the user history"+e.getMessage());
            throw e;
        }
    }
}

package com.kyro.showrecommendation.models.show;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class Links implements Serializable {

    private Link self;
    @JsonProperty("previousepisode")
    private Link previousEpisode;
    @JsonProperty("nextepisode")
    private Link nextEpisode;
    private Link show;
    private Link character;

}

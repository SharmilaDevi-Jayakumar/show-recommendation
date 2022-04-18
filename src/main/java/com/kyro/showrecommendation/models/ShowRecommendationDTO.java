package com.kyro.showrecommendation.models;
import com.kyro.showrecommendation.models.show.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ShowRecommendationDTO {
    public String id;
    public String image;
    public String language;
    public String name;
    public String rating;
    public String url;
}

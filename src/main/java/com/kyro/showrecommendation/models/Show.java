package com.kyro.showrecommendation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import  com.kyro.showrecommendation.models.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kyro.showrecommendation.models.show.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Show implements Serializable {
    @JsonProperty("_links")
    public Links links;
    public List<CastMember> cast;
    public Externals externals;
    public List<String> genres;
    public int id;
    public Image image;
    public String language;
    public String name;
    public Network network;
    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date premiered;
    public Rating rating;
    public Integer runtime;
    public Integer weight;
    public Integer averageRuntime;
    public Schedule schedule;
    public String status;
    public String ended;
    public String officialSite;
    public String summary;
    public String type;
    public Integer updated;
    public String url;
    public WebChannel webChannel;
    public String dvdCountry;

}

package com.kyro.showrecommendation.models;

import lombok.Data;

import java.io.Serializable;

@Data
public class Externals implements Serializable {
    private int tvrage;
    private int thetvdb;
    private String imdb;
}

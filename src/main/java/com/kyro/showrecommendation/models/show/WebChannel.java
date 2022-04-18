package com.kyro.showrecommendation.models.show;

import lombok.Data;

import java.io.Serializable;

@Data
public class WebChannel implements Serializable {

    private int id;
    private String name;
    private Country country;
}

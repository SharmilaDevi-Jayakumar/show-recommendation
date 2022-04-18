package com.kyro.showrecommendation.models.show;

import lombok.Data;

import java.io.Serializable;

@Data
public class Country implements Serializable {
    private String code;
    private String name;
    private String timezone;

}

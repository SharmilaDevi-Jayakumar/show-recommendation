package com.kyro.showrecommendation.models.show;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalTime;

@Data
public class Schedule implements Serializable {
    private String[] days;
    private LocalTime time;

}

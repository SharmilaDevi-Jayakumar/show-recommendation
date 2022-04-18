package com.kyro.showrecommendation.models;

import lombok.Data;

import java.io.Serializable;

@Data
public class CastMember implements Serializable {
	protected Character character;
	protected Person person;
}

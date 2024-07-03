package com.smhrd.entity;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class ReceiveDetect {
	
	private List<Integer> typeNUM;
    private String identifiID;
    private List<String> detectionFILE;
	
}

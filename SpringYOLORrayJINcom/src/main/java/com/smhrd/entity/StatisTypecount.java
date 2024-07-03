package com.smhrd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class StatisTypecount {
		
	private int thistype;
	private int this_typecnt;
	private int lasttype;	
	private int last_typecnt;
	private String type_name;	

}

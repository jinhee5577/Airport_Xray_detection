package com.smhrd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Workload { // 작업량 테이블DTO
   
   private int WORK_IDX;
   private String IDENTIFI_ID;
   private String WORK_AT;
   private String WORK_PRESENCE;

}

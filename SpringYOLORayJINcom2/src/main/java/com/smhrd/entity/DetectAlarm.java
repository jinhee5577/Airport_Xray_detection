package com.smhrd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class DetectAlarm {  // 알림사항 받을 DTO
   
   private String TYPE_NAME_ENG;
   private String IDENTIFI_ID;   
   

}

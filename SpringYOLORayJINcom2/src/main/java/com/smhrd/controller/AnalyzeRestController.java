package com.smhrd.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.smhrd.entity.Board;
import com.smhrd.entity.Company;
import com.smhrd.entity.DetectAlarm;
import com.smhrd.entity.Detection;
import com.smhrd.entity.MemID;
import com.smhrd.entity.Member;
import com.smhrd.entity.ReceiveDetect;
import com.smhrd.entity.SelectModel;
import com.smhrd.entity.StatisTypecount;
import com.smhrd.entity.Workload;
import com.smhrd.mapper.BoardMapper;

// 비동기 방식만 가능
// 데이터, 객체...
// 페이지 이동이 불가능
@RequestMapping("/detection")
@RestController
@CrossOrigin // CorsFiler class생성해두어서 이제 originPatterns 이거 필요 없다. @CrossOrigin(originPatterns = "http://localhost:3000") -> @CrossOrigin("http://localhost:3000")
public class AnalyzeRestController {
      // 실험으로 구현한 가상의 DB 회원정보 Member ArrayList<>
      // List<Member> members = new ArrayList<>();
      

      @Autowired
      private BoardMapper mapper;
      
      private Member memberDto;
      private Workload workloadDto;      
      

      // Detection테이블과 type테이블 조인으로 알림사항 전체보기
      @GetMapping("/alarm") //  /detection/alarm
      public List<DetectAlarm> detectionalarmList(HttpServletRequest request, @RequestParam("comnum") String IDENTIFI_ID){
         HttpSession session = request.getSession();
         System.out.println("알림사항 전체보기" + IDENTIFI_ID);
         // 회원의 사번 필요함
//        Company loginMem = (Company) session.getAttribute("mem");
//        System.out.println("세션" + loginMem.getIDENTIFI_ID());
         
         List<DetectAlarm> detectionalarmList = mapper.detectionalarmList(IDENTIFI_ID);
         System.out.println("알림내용 " + detectionalarmList);
         return detectionalarmList;
      }
       
      
      // 실시간 분석 - 돌릴때마다 정상탐지물품도 Workload테이블에 insert해줌.
      @PostMapping("/normal")   //  /detection/normal로 요청.
      public int normalWorkloadInsert (@RequestBody MemID memid) {
         // 로그인된 회원의 사번을 memberDto의 MEMBER_ID에 넣어줌.
        // Company loginMem = (Company) session.getAttribute("mem");
    	  Workload workloadDto = new Workload();
         
          // Workload테이블에 MEMBER_ID가 필요함.
    	  workloadDto.setIDENTIFI_ID(memid.getComid());
    	  workloadDto.setWORK_PRESENCE("N");
         
          // DB INSET
          mapper.workloadInsert(workloadDto);
          System.out.println("정상품목도 인설트 완료");
          return 205;
      }
            
      
      
      // 실시간 분석 - 위해물품 탐지시 stop 일어난뒤 DB(DETECTION, WORKLOAD)테이들에 동시에 insert 기능
      @PostMapping("/stop")    //  /detection/stop로 요청.
      public int detectionInsert (@RequestBody ReceiveDetect detec, HttpSession session) {
//        Company loginMem = (Company) session.getAttribute("mem");  세션이 안옴.
  //      System.out.println("사번" + loginMem.getIDENTIFI_ID());
    	  System.out.println("탐지물품 " + detec);
    	  Detection insertDect = new Detection();
    	  Workload workloadDto = new Workload();
    	  
         // Detection, WorkloaDTO에 해당정보 추가.    	  
    	  insertDect.setIDENTIFI_ID(detec.getIdentifiID());
          workloadDto.setIDENTIFI_ID(detec.getIdentifiID());
          
          for(int i=0; i< detec.getTypeNUM().size(); i++) {
        	  insertDect.setTYPE_NUM(detec.getTypeNUM().get(i));
              workloadDto.setWORK_PRESENCE("Y");          
              insertDect.setDETECTION_FILE(detec.getDetectionFILE().get(i));              
              // DB INSET
              mapper.detectionInsert(insertDect);
              mapper.workloadInsert(workloadDto);
              System.out.println("인설트 완료");        	  
          }          
          
          System.out.println("워크로드 " + workloadDto);
          return 200;
      }
      
      
      
      // 해당 유저의 이상탐지기록 :탐지된품목 번호로 탐지(DETECTION)테이블과 품목이름 결합하여 출력하는 기능.
      @GetMapping("/combinationType") //  /detection/combinationType
      public List<Detection> detectionCombinationType(@RequestParam("compnum") String IDENTIFI_ID, HttpSession session){
         System.out.println("[유저 이상탐지 TYPE결합된 ALL list]");
         // 로그인된 회원의 사번을 memberDto의 MEMBER_ID에 넣어줌.
        // Company loginMem = (Company) session.getAttribute("mem");
         
         List<Detection> CombinationTypeAll = mapper.detectionCombinationType(IDENTIFI_ID);
         System.out.println("탐지 출력 "+ CombinationTypeAll);
         return CombinationTypeAll;
      }
             
      
      // 이번주 가장 많은 TYPE_NUM구하는 기능
      @GetMapping("/maxtypeThisweek") //  /detection/maxtypeThisweek
      public StatisTypecount maxtypeThisweek(){
    	 // 이번주 가장 많은 TYPE_NUM구함. 
    	 StatisTypecount typeCnt = mapper.getMaxTypeThis();
    	 // 저번주 가장 많은 TYPE_NUM구함.
    	 StatisTypecount LasttypeCnt = mapper.getLastMaxType();

    	 typeCnt.setLasttype(LasttypeCnt.getLasttype());
    	 typeCnt.setLast_typecnt(LasttypeCnt.getLast_typecnt());
    	 
    	 // 타입번호로 타입이름 가져오기.
    	 StatisTypecount thisTypeName = mapper.getTypeName(typeCnt.getThistype());
    	 typeCnt.setType_name(thisTypeName.getType_name());
    	 System.out.println("이번주 저번주 " + typeCnt);
    	 
         return typeCnt;
      }
      
      
      // 이번달 가장 많은 TYPE_NUM구하는 기능
      @GetMapping("/maxtypeThismonth") //  /detection/maxtypeThismonth
      public StatisTypecount maxtypeThismonth(){
    	  // 이번달 가장 많은 TYPE_NUM구함. 
    	  StatisTypecount typeCnt = mapper.getMaxTypeThisMonth();
    	  // 저번달 가장 많은 TYPE_NUM구함.
    	  StatisTypecount LasttypeCnt = mapper.getLastmonthMaxType();
    	  
    	  typeCnt.setLasttype(LasttypeCnt.getLasttype());
    	  typeCnt.setLast_typecnt(LasttypeCnt.getLast_typecnt());
    	  
    	  // 타입번호로 타입이름 가져오기.
    	  StatisTypecount thisTypeName = mapper.getTypeName(typeCnt.getThistype());
    	  typeCnt.setType_name(thisTypeName.getType_name());
    	  System.out.println("이번달 저번달 " + typeCnt);
    	  
    	  return typeCnt;
      }
      
      
      
      
      
      
   
}

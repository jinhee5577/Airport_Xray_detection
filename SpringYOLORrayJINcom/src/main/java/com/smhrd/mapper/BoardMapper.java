package com.smhrd.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.smhrd.entity.Board;
import com.smhrd.entity.CreateNotice;
import com.smhrd.entity.DailyStatis;
import com.smhrd.entity.DetectAlarm;
import com.smhrd.entity.Detection;
import com.smhrd.entity.Member;
import com.smhrd.entity.MonthlySum;
import com.smhrd.entity.Notice;
import com.smhrd.entity.StatisTypecount;
import com.smhrd.entity.TodayYN;
import com.smhrd.entity.Workload;

@Mapper
public interface BoardMapper {  
   // 여기에 공지사항 mapper로 쓰겠습니다.
   // Spring과 MyBatis를 연결하는 역할
   
   // 공지사항 전부가져오기
   public List<Notice> noticeList(); 
   
   // 위해물품 월별합산 전체 작업량보기
   public List<MonthlySum> workloadList();
   
   // 위해물품 주간합산 전체 작업량보기
   public List<MonthlySum> workloadListWeek(); 
   
   
   // Detection테이블데이터로 알림사항 전체보기
   public List<DetectAlarm> detectionalarmList(String memID); 
   
   // 
   public List<Member> getMemberAll();

   // 실시간 분석 - 위해물품 탐지시 stop 일어난뒤 DB Detection테이블에 insert 기능
   public void detectionInsert(Detection detec);
   
   // 실시간 분석 - 위해물품 탐지시 stop 일어난뒤 DB Workload테이블에 insert 기능
   public void workloadInsert(Workload workloadDto);
   
   
   // 먼저 금일(오늘) 날짜 가져오는 쿼리 호출 
   public TodayYN getToday();
   
   // 어제 날짜 가져오는 쿼리 호출 
   public DailyStatis getYesterday();
   
   // 금일(오늘) Y의 총갯수 가져오기
   public TodayYN gettodayY(String today);
   // 금일(오늘) N의 총갯수 가져오기
   public TodayYN gettodayN(String today);
   
   
   // 이번주 가장 많은 TYPE_NUM구하는 오라클 SQL쿼리
   public StatisTypecount getMaxTypeThis();
   
   // 저번주 가장 많은 TYPE_NUM구하는 오라클 SQL쿼리
   public StatisTypecount getLastMaxType();
   
   
   // 이번달 가장 많은 TYPE_NUM구하는 오라클 SQL쿼리
   public StatisTypecount getMaxTypeThisMonth();
   
   // 저번달 가장 많은 TYPE_NUM구하는 오라클 SQL쿼리
   public StatisTypecount getLastmonthMaxType();  
   
   // 타입번호로 타입이름 가져오기.
   public StatisTypecount getTypeName(int typenum);  
   
   
   // 해당 유저의 이상탐지기록 :탐지된품목 번호로 탐지(DETECTION)테이블과 품목이름 결합하여 출력하는 기능.
   public List<Detection> detectionCombinationType(String memID);
   
   // 선택 공지사항 삭제하기
   public void boardDelete(int idx);
   
   // 공지사항 글작성하기
   public void createNotice(CreateNotice newNotice);
   
   // 공지사항 글수정하기
   public void modifyNotice(CreateNotice modify);
   
   
   
   
   public Board boardContent(int idx);  

   public void boardCount(int idx);

}



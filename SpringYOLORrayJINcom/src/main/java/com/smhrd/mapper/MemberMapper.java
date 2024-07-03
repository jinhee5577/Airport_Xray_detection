package com.smhrd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.smhrd.entity.Auth;
import com.smhrd.entity.Company;
import com.smhrd.entity.Detection;
import com.smhrd.entity.KakaoUserInfoResponseDto;
import com.smhrd.entity.MODEL;
import com.smhrd.entity.Member;
import com.smhrd.entity.ReceiveModel;

@Mapper
public interface MemberMapper {

   // Spring과 MyBatis를 연결하는 역할
   
   
    public Company login(String IDENTIFI_ID);
    
    // 모든 유저정보 불러옴.
    public List<Member> getuserAll();
    
    // DB model내용 모두 가져오기
    public List<MODEL> getModelList();
    
    // DB MODEL테이블에 새로운 내용 insert.
    public void insertModel(ReceiveModel newModel);

    // 로그인 실패 and COMPANY테이블에 정보 insert 기능.
    public int insertCompanyNum(String companyNum);
    
    // 카카오로그인으로 가져온data DB insert기능.
    public void insertKakaodata(KakaoUserInfoResponseDto kakaoUser);
    
   
    // Member 테이블에서 유저 권한 검사하기위해 권한 가져오기.
    public Member getUserAuth(String IDENTIFI_ID);
    
    // member테이블에 저장된 정보없으면 추가함.
    public void insertMemberNum(String IDENTIFI_ID);
    
    // 해당 유저권한 업데이트기능.
    public void userAuthUpdate(Auth authData);
    
    // 회원관리 페이지에서 db status 상태 true로 바꿈.
    public void userstatusTrue(String Identifi_ID);
    
    // 회원관리 페이지에서 db status 상태 FALSE로 바꿈.
    public void userstatusFalse(String Identifi_ID);

    
   
   
   
}

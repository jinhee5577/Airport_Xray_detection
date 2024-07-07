# Airport_Xray_detection
실전 융합 - 딥러닝을 이용한 공항 X-ray위해물품 자동 탐지시스템 프로젝트 repository 입니다.

## 💡실전 융합 - 딥러닝을 이용한 공항 X-ray위해물품 자동 탐지시스템 프로젝트

----

## 🎉서비스 소개.
- 서비스 명 : 딥러닝을 이용한 공항 **X-ray위해물품 자동 탐지**시스템 웹서비스를 기획 개발.
- 서비스 소개 : 우리 YOLORAY팀은 기존의 보안검색 과정을 실시간 X-ray 자동판독 솔루션 서비스를 <br>
  구축하여 정확성과 효율성 향상 및 보안 강화에 기여를 목적으로한 대시보드 형태의 자동판독 웹서비스 입니다.


## 📌주요 기능
- 사번을 활용한 2중 검사 로그인 지원.
- 신규고객, 회원가입시 설문조사를 통해 고객 맞춤 향수 추천 서비스.
- X-ray이미지 데이터 전처리 및 학습.
- 딥러닝 모델 분석을 통해 학습률 비교 및 최적의 모델 선택.
- 통계 대시보드 화면 (일일 검출량, 금주가장 많은 검출풀목, 이달가장 많은 검출품목).
- 대시보드 차트 구현 chart.js 사용.
- 실시간 분석 페이지(대량의 데이터 분석 및 자동 위해물품 탐지).
- 컨베이어 애니메이션 동작시 자동 위해물품 구분과 탐지후 자동 DB저장.
- 새로운 경험 선사 - 애니메이션, 퍼포먼스 구현.
- 단건 분석 페이지, 분석 다시보기.
- 관리자 페이지 기능.

## 🌟주요기술
![image](https://github.com/2023-SMHRD-KDT-AI-16/PLAY_SCENT/assets/157596156/1da8684d-af63-4f53-b564-807d078525a5)
**진희팀**의 대표적인 기술로는 **고객맞춤 향수추천** 서비스 입니다.<br>
신규 고객이 설문조사를 진행할때 **체크박스** 마다 향기의 대표적인 향들이 **value값으로** 들어 있습니다.<br>
원하는 항목으로 선택을 마치면 제출시 **우리의 대조군**과 비교할수 있는 **한줄의 데이터**가 완성됩니다.<br>
이대상을 **파이썬 기반의 flask서버**로 전송하여 flask서버에서 우리가 준비해놓은 **대조군들을 불러와**<br>
설문조사로 얻어진 한줄의 데이터 이둘을 **코사인유사도 체크 과정**을 거쳐 **가장 유사도가 높은 best5 향수식별자**를<br>
우리의 **자바 서블릿 서버**로 전송해 줍니다. 이 **식별자 번호**로 DB에 저장된 **향수 정보**를 불어와 프론트UI로 보여줍니다.

---- 
<br>
  
## 📅개발 기간 :  2024.05.06 ~ 2024.06.19

### ⛏ 개발 스택 : Spring, FastAPI, React, YOLOv8, Oracle, MyBatis, JAVA, Javascript, python, Slack, Jira, Apache, Github
![스크린샷 2024-06-29 212249](https://github.com/2023-SMHRD-KDT-AI-16/PLAY_SCENT/assets/82584287/8fbc26b3-8190-4c9c-80f0-8b45f1b0dff6)

----

<br>

## 📝 메뉴구성
<img width="700" alt="진희팀 유스2" src="https://github.com/2023-SMHRD-KDT-AI-16/PLAY_SCENT/assets/82584287/3159a63c-b914-4df3-af85-f22542dca35b">

## 🔄서비스 흐름도
<img width="749" alt="PLAYScent최종 서비스흐름도" src="https://github.com/2023-SMHRD-KDT-AI-16/PLAY_SCENT/assets/157596156/33ea78be-c9a3-4309-a6fc-1fa7a565cacf">
----

## 📝 ERD
![PSERD2](https://github.com/2023-SMHRD-KDT-AI-16/PLAY_SCENT/assets/157596156/5de04b42-13af-498d-b229-693e536662cd)

## 💻 PLAY SCENT Main페이지 입니다.
<img width="650" alt="mainps" src="https://github.com/2023-SMHRD-KDT-AI-16/PLAY_SCENT/assets/157596156/b2821843-255c-4d1a-b1f1-df3784b653f4">

## 💻 PLAY SCENT 서브페이지 입니다.
<img width="120" alt="설문조사" src="https://github.com/2023-SMHRD-KDT-AI-16/PLAY_SCENT/assets/157596156/24a8e243-11b3-4a68-848e-df41a0489c87">
<img width="330" alt="향수 상세페이지" src="https://github.com/2023-SMHRD-KDT-AI-16/PLAY_SCENT/assets/157596156/4276137b-9059-44a9-bc73-2af405264de8">
<img width="330" alt="플로럴" src="https://github.com/2023-SMHRD-KDT-AI-16/PLAY_SCENT/assets/157596156/2cd896be-47cb-4630-9ca6-2d0e4e692a37">
<img width="330" alt="장바구니페이지" src="https://github.com/2023-SMHRD-KDT-AI-16/PLAY_SCENT/assets/157596156/ed833375-eb7c-4364-ae41-62f88dc7b3d9">
<img width="330" alt="주문페이지" src="https://github.com/2023-SMHRD-KDT-AI-16/PLAY_SCENT/assets/157596156/d815383a-6130-49e1-9022-27d00b5fb337">
<img width="330" alt="최종결제" src="https://github.com/2023-SMHRD-KDT-AI-16/PLAY_SCENT/assets/157596156/3e42a1ca-7784-4530-abf2-6bce6b3fe91e">
<img width="330" alt="향기네컷" src="https://github.com/2023-SMHRD-KDT-AI-16/PLAY_SCENT/assets/157596156/e2f1225b-7215-4efe-9d83-187d42f81e06">
<img width="330" alt="마이페이지" src="https://github.com/2023-SMHRD-KDT-AI-16/PLAY_SCENT/assets/157596156/095e3d5b-4465-4893-a6c5-37bc5585dc93">

----
<br>

## <채영팀 YOLORAY>
### 👨🏻‍💻 팀원  
- **손채영 (팀장)**
- **오진희 (부팀장)**
- **임찬혁**
- **오종원**



## 🔖팀원소개 및 상세역활.
<img width="796" alt="팀원소개" src="https://github.com/2023-SMHRD-KDT-AI-16/PLAY_SCENT/assets/157596156/9422d7a3-b0c4-4371-8edd-419bdd9f3cf4">

----

<br>

## 🛠️ 트러블 이슈.
- ### 1). 파이썬 Flask 서버와 자바의 jsp 사이의 데이터 전송에 대해  언어가 서로 달라 어떻게 <br>
  전송해야할지 에대해 매몰되어 너무어렵게 생각하였다.
  <img width="785" alt="image" src="https://github.com/2023-SMHRD-KDT-AI-16/PLAY_SCENT/assets/157596156/3e66b393-5669-4da8-af68-8e764d130f76">

  **해결 방안): 간단하게 **플라스크서버 주소로 get요청**을보내 데이터를 실어 보내어 간단하게 해결 할수 있었습니다.** 
  <br>
  ( window.location.replace("flask서버주소") ) 사용.

- ### 2). 장바구니페이지에서 선택한 제품만의 수량 정보만 최종 주문/결제 페이지로 이동되지 않고 모든 수량 정보가 도착함.
    ![image](https://github.com/2023-SMHRD-KDT-AI-16/PLAY_SCENT/assets/157596156/e156e1ac-e2da-4b83-85e3-107cf3aa0da5)

   **해결 방안):** 이를 구분해주기위해 서버에서 다시한번 장바구니 목록 데이터를 불러와, **이중for문 돌려** <br>
   **선택한 체크박스의 장바구니 식별자**와 일치하는 순간의 **인덱스번호로 수량이 담긴 배열에서도 동일한 <br>
  인덱스번호 값**에 해당한 수량을 정확하게 뽑아와 해결할수 있었습니다.

  











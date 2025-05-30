# 🛫  쉬자Go COMPANY - VacationHolySystem
쉬자Go - 휴가 통합 관리 시스템


## 🖥️ 프로젝트 소개
쉬자Go 는 조직 내 모든 휴가 관련 정보를 통합하여 관리합니다.
직원은 쉽고 빠르게 휴가를 신청하고
관리자도 쉽고 빠르게 휴가신청서를 조회, 승인, 반려 할 수 있도록 지원하는 시스템입니다.
쉬자Go 라는 회사 내 휴가를 작성하는 프로그램을 만들어보았습니다.
<br>

## 🕰️ 개발 기간
* 2025-04-22 ~ 2025-04-25  

### 🧑‍🤝‍🧑 멤버구성
 - 개발자 : 최효진


### ⚙️ 개발 환경

| 항목         | 내용                                                         |
|--------------|--------------------------------------------------------------|
| 운영체제     | Windows 10 Home                                               |
| 개발 언어     | Java                                                         |
| JDK 버전     | JDK 21                                                       |
| 데이터베이스 | CSV                                                          |
| 개발 툴      | Eclipse                                                      |
| 문서화 도구   | Google Docs (문서, 스프레드시트, 프레젠테이션)             |
| 형상 관리     | Git                                                       |


## 📌 주요 기능

### 👤 사용자 페이지 
(사용자가 사번과 비밀번호를 입력하여 로그인)

![image](https://github.com/user-attachments/assets/11b38c0c-03cd-4939-9fa3-5d1267d957fa)

- 로그인 시 정확한 본인의 정보가 맞는지 출력을 해준다.
  
![image](https://github.com/user-attachments/assets/faa621c9-b7a2-4ffe-a0a7-a7f65bcc28e0)

- 휴가 (연차, 병가) 선택하고 날짜를 설정해 신청서를 작성한다.
  
![image](https://github.com/user-attachments/assets/0614c0a1-b47b-4a8c-8914-33c6900d1e23)


### 🧑‍💼 관리자 페이지
(관리자가 사번과 비밀번호를 입력하여 로그인)
- 관리자는 직원들을 조회할수있고, 정보수정 및 삭제도 가능하고 새로운 직원도 등록이 가능하다.

![image](https://github.com/user-attachments/assets/f3737a8b-440d-443e-aed7-eb0eacd2c795)


- 각 직원의 휴가 사용 이력과 잔여 일수를 실시간으로 조회할 수 있다.
  
![image](https://github.com/user-attachments/assets/c70c8fca-1671-408f-a2a7-6ca40f05de92)

![image](https://github.com/user-attachments/assets/f7115b62-07ba-45a3-a02f-2b7f5d2db63b)

- 관리자는 직원들의 휴가 신청 현황을 확인하고, **승인 또는 반려** 할 수 있다.
  
![image](https://github.com/user-attachments/assets/d05f5778-8686-40ed-b42b-089e9cc312e2)

-

![image](https://github.com/92choihyojin/HyoHub/blob/main/HR_Vacation_Project/HR_Vacation_Project.gif)

## 🗃️ DB 테이블 명세서

### ✅ 사원 테이블 (EMPLOYEE_TBL)

| 컬럼명        | 한글명     | 데이터 타입 | 크기 | Null 허용 | 유일키 | 키  | 비고                       |
|---------------|------------|--------------|------|-----------|--------|-----|----------------------------|
| emp_no        | 사원번호   | INT          | -    | N         | Y      | PK  | 사원 번호                  |
| password      | 비밀번호   | VARCHAR      | 20   | N         |        |     | 비밀번호                   |
| emp_name      | 이름       | VARCHAR      | 10   | N         |        |     | 사원 이름                  |
| position      | 직급       | VARCHAR      | 10   | N         |        |     | 직급 (ex. 사원, 대리 등)   |
| birth_date    | 생년월일   | DATE         | -    | N         |        |     | 생년월일 (YYYY-MM-DD)      |
| join_date     | 입사일     | DATE         | -    | N         |        |     | 입사일자 (YYYY-MM-DD)      |
| phone_number  | 전화번호   | VARCHAR      | 20   | Y         |        |     | 전화번호 (010-0000-0000)   |
| is_admin      | 관리자 여부| BOOLEAN      | -    | N         |        |     | 관리자 여부 (Y/N)          |

---

### ✅ 사원별 연차 휴가 테이블 (EMPLOYEE_LEAVE_BALANCE_TBL)

| 컬럼명         | 한글명         | 데이터 타입 | 크기 | Null 허용 | 유일키 | 키  | 비고            |
|----------------|----------------|--------------|------|-----------|--------|-----|-----------------|
| emp_no         | 사원 번호      | INT          | -    | N         |        | PK  | 사원 번호       |
| year           | 연차 연도      | INT          | -    | N         |        | PK  | 연차연도 (YYYY) |
| remaining_days | 잔여 휴가 일수 | INT          | -    | N         |        |     | 남은 휴가 일수  |

---

### ✅ 휴가 신청 테이블 (LEAVE_REQUEST_TBL)

| 컬럼명     | 한글명         | 데이터 타입 | 크기 | Null 허용 | 유일키 | 키  | 비고                          |
|------------|----------------|--------------|------|-----------|--------|-----|-------------------------------|
| req_no     | 휴가 신청 번호 | INT          | -    | N         | Y      | PK  | 휴가 신청 번호                |
| leave_type | 휴가 유형      | VARCHAR      | 10   | N         |        |     | 휴가 유형 (연차, 병가 등)     |
| start_date | 시작일자       | DATE         | -    | N         |        |     | 휴가 시작일 (YYYY-MM-DD)      |
| end_date   | 종료일자       | DATE         | -    | N         |        |     | 휴가 종료일 (YYYY-MM-DD)      |
| reason     | 사유           | VARCHAR      | 100  | N         |        |     | 휴가 사유                     |
| status     | 상태           | VARCHAR      | 10   | N         |        |     | 결재 상태 (대기, 승인, 반려) |
| emp_no     | 사원 번호      | INT          | -    | N         |        | FK  | 사원 번호                     |

---

## 📂 기타
DB로 작업해보기전에
파일로 관리해보는 경험을 해보고자
파일로 관리를 할수있게 만들어보았습니다.

- 모든 문서 및 산출물은 Google Drive를 통해 공유 및 관리됩니다.



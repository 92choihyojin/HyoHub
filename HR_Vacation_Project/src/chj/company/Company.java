package chj.company;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import chj.companyMenu.EmpMenu;
import chj.companyMenu.EmpModifyMenu;
import chj.companyMenu.Menu;
import chj.companyMenu.VacationMenu;


public class Company {

	public static void main(String[] args) throws FileNotFoundException {
		// 메인이 실행될 때 파일에서 사용자 리스트를 받아오게 만들어야 함.
		List<Employee> employeeList = new ArrayList<Employee>();
		List<HolyCnt> holyCntList = new ArrayList<HolyCnt>();
		List<HolyPropose> holyProposeList = new ArrayList<HolyPropose>();
		
		String employeeCSVFirstLine = "";
		String holyDtCntCSVFirstLine = "";
		String holyProposeCSVFirstLine = "";

		
		Employee tmpEmp = null;	// 로그인 된 사용자
		
		
		// CSV(영어: comma-separated values)는 몇 가지 필드를 쉼표(,)로 구분한 텍스트 데이터 및 텍스트 파일이다.
		// CSV,TSV로 작업시 엑셀화 하여 데이터를 쉽게 관리할 수 있음.
		// TSV(영어: tab-separated values) 라는 것도 있음.
		// 파일에서 사원목록을 가져오는 부분 시작
		FileInputStream fis = null; // ./src/chj/data/employee.csv에 있는 csv (txt랑 동일함) 파일의 내용을 가져오기 위한 스트림
		try {
			fis = new FileInputStream("./src/chj/data/employee.csv");
			Scanner scan = new Scanner(fis);
			if (scan.hasNextLine()) {
				employeeCSVFirstLine = scan.nextLine(); // 파일의 첫번째 줄은 임시저장
			}
			
			while (true) {
				if (!scan.hasNextLine()) { // employee.csv 파일의 두번째 줄부터 다음줄이 없으면 true
					// 조건식이 true면 아래 실행
					break; // while문을 끝냄.
				}
				employeeList.add(loadEmp(scan)); // employeeList에 한줄씩 데이터를 넣음
			}
		} catch (FileNotFoundException e) { 
			// 파일이 없으면 실행되는 부분
			e.printStackTrace();
		} finally {
			// 결국에는 FileInputStream을 종료하는 소스
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Collections.sort(employeeList, new EmployeeNoComparator()); // 사원조회를 하는데 사번으로 순차적으로 조회하기 위함
		
		FileInputStream fis2 = null; // ./src/chj/data/employee.csv에 있는 csv (txt랑 동일함) 파일의 내용을 가져오기 위한 스트림
		try {
			fis2 = new FileInputStream("./src/chj/data/holy_dt_cnt.csv");
			Scanner scan = new Scanner(fis2);
			if (scan.hasNextLine()) {
				holyDtCntCSVFirstLine = scan.nextLine(); // 파일의 첫번째 줄은 버리는거
			}
			
			while (true) {
				if (!scan.hasNextLine()) { // employee.csv 파일의 두번째 줄부터 다음줄이 없으면 true
					// 조건식이 true면 아래 실행
					break; // while문을 끝냄.
				}
				
				String holy_dt_cnt_str = scan.nextLine();
				
				String[] tokens = holy_dt_cnt_str.split(","); 
				// "1,2025,30,0,30,9999" 이거를 이렇게 만드는 거 => {"1" , "2025", "30", "0", "30", "9999"}
				
				int empHolyProposeId = Integer.parseInt(tokens[0]);
				int year = Integer.parseInt(tokens[1]);
				int totalDay = Integer.parseInt(tokens[2]);
				int usedDay = Integer.parseInt(tokens[3]);
				int remainDay = Integer.parseInt(tokens[4]);
				String empNo = tokens[5];
				
				holyCntList.add(new HolyCnt(empHolyProposeId, year, totalDay, usedDay, remainDay, empNo)); // employeeList에 한줄씩 데이터를 넣음
			}
		} catch (FileNotFoundException e) { 
			// 파일이 없으면 실행되는 부분
			e.printStackTrace();
		} finally {
			// 결국에는 FileInputStream을 종료하는 소스
			try {
				fis2.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		FileInputStream fis3 = null; // ./src/chj/data/employee.csv에 있는 csv (txt랑 동일함) 파일의 내용을 가져오기 위한 스트림
		try {
			fis3 = new FileInputStream("./src/chj/data/holy_propose.csv");
			Scanner scan = new Scanner(fis3);
			if (scan.hasNextLine()) {
				holyProposeCSVFirstLine = scan.nextLine(); // 파일의 첫번째 줄은 버리는거
			}
			
			while (true) {
				if (!scan.hasNextLine()) { // employee.csv 파일의 두번째 줄부터 다음줄이 없으면 true
					// 조건식이 true면 아래 실행
					break; // while문을 끝냄.
				}
				
				String holy_porpose_str = scan.nextLine();
				
				String[] tokens = holy_porpose_str.split(","); 
				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				
				int reqId = Integer.parseInt(tokens[0]);
				int reqType = Integer.parseInt(tokens[1]);
				LocalDate startDt = LocalDate.parse(tokens[2], formatter);
				LocalDate endDt = LocalDate.parse(tokens[3], formatter);
				int usedDay =  Integer.parseInt(tokens[4]);
				String eu =  tokens[5];
				int stat =  Integer.parseInt(tokens[6]);
				String empNo = tokens[7];
				
				holyProposeList.add(new HolyPropose(reqId, reqType, startDt, endDt, usedDay, eu, stat, empNo)); // employeeList에 한줄씩 데이터를 넣음
			}
		} catch (FileNotFoundException e) { 
			// 파일이 없으면 실행되는 부분
			e.printStackTrace();
		} finally {
			// 결국에는 FileInputStream을 종료하는 소스
			try {
				fis3.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//////////////////////////////////////////////////////////////////////////////////////////
		// ▼ 임시로 사원의 정보를 출력해주는곳
//		System.out.println("사원의 정보를 불러옵니다");
//		for(Employee e: employeeList) {
//			 System.out.println(e);
//		}
//		
//		System.out.println("사원의 잔여휴가일을 불러옵니다");
//		for(HolyCnt h: holyCntList) {
//			 System.out.println(h);
//		}
		
//		System.out.println("사원의 휴가목록을 불러옵니다");
//		for(HolyPropose hpl : holyProposeList) {
//			 System.out.println(hpl);
//		}
		
		
		///////////////////////////////////////////////////////////////////////////////////////////// 파일에서 사원목록을 가져오는 부분 끝
		
		 Scanner scan = new Scanner(System.in);
		 boolean mainMenuStop = false;
		
		 while (!mainMenuStop) {
		 	// 로그인 메뉴창
			Menu.LoginMenuDisplay();
	       
	        // 메뉴 선택하는 함수
	        int firstMenuNo = Menu.inputNo(3);
	        
	        
	        if(firstMenuNo==1 || firstMenuNo==2) {
	        	System.out.println("쉬자Go 로그인을 시작합니다.\n사원번호와 비밀번호를 입력해주세요.");
	        	System.out.print("사원번호 입력 : ");
	        	String empNo = scan.nextLine();
	        		
	        	for( Employee e : employeeList ) {
	        		if(e.getEmpNo().equals(empNo)) {	// 데이터값에 있는EmpNo을 가져와서 내가 입력한 empNo값이랑 같은지 비교
	        			tmpEmp = e;						// 같으면 tmpEmp에 임시보관
	        		}
	    		}
	        	System.out.print("비밀번호 입력: ");
	        	String password = scan.nextLine();
	        	System.out.println("\n");
	        	
	        	if(tmpEmp != null) { // 입력된 사번으로 조회된 직원이 있을 경우
	        		if(!(tmpEmp.getPassword().equals(password))) {
	        			//(tmpEmp.getPassword().equals(password)) 일치하면
	    	        	//(!(tmpEmp.getPassword().equals(password))) 일치하지 않을 때
	    	        	System.out.println("사원번호 혹은 비밀번호가 일치하지 않습니다.");
	    	        	tmpEmp = null; // 임시데이터 초기화
	        		} 
	        	} else { // 입력된 사번으로 조회된 직원이 없을 경우
	        		System.out.println("존재하지 않는 사원번호이거나 비밀번호를 확인해주세요.");
		        	tmpEmp = null; // 임시데이터 초기화
	        	}
	        	
	        	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	        	
		        if(firstMenuNo==1 && (tmpEmp != null)) { // 사용자 로그인
		        	
		        	boolean isStop = false;
		        	
		        	DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		    		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyyMMdd");
		        	
		        	while(isStop == false) {
		    			System.out.printf("어서오십시오. [ %s ] 님\n", tmpEmp.getName());
		    			// [ 본인정보 (사번, 이름, 직급, 생년월일, 전화번호, 잔여연차) ] 출력 되야함
		    			System.out.printf("사원번호 : %s  /  이름 : %s  /  직급 : %s\n생년월일 : %s  /  전화번호 : %s\n입사년도 : %s  /  잔여 연차 : %s\n",
		    					tmpEmp.getEmpNo(),tmpEmp.getName(),tmpEmp.getPosition(),tmpEmp.getBirthDt(),
		    					tmpEmp.getPhoneNo(),tmpEmp.getHireDt(),tmpEmp.getHolyDayCnt());
		    			//로그인정보출력
		    			Menu.EmpMenuDisplay();

		    			int no = Menu.inputNo(2);  // 1, 2 선택
		    			
		    			
		    			
		    			switch(no) { 
		    				case EmpMenu.휴가신청 : // 1. 연차,병가 신청
		    					// 연차신청을 하기위해 사유와 시작일 종료일 입력함.
		    					// 연차신청을 하면 관리자가 확인할 수 있도록 함
		    					// 저장하고 나가짐으로써 저장이 됨
		    					VacationMenu.Choice(); // 연차 병가 신청선택
		    			       
		    			        int vacationChoice = Menu.inputNo(3);
		    			        switch(vacationChoice) { 
			    				
		    			        	case VacationMenu.연차신청 : // 1.연차신청
		    			        		int remainDay = 0;
		    			        		final Employee tmpEmp2 = tmpEmp;
		    			        		Optional<Integer> maxRemainDay = holyCntList.stream()
		    			        				   .filter(holyCnt -> {
		    			        					   return holyCnt.getEmpNo().equals(tmpEmp2.getEmpNo());
		    			        					   })
		    			        				   .max((a, b) -> a.compareTo(b))
		    			        				   .map(holyCnt -> holyCnt.getRemainDay());

		    			        				if (maxRemainDay.isPresent()) {
		    			        					remainDay = maxRemainDay.get();
		    			        				} else {
		    			        					remainDay = 0;
		    			        				}
		    			        		System.out.printf("%s 님의 남은 연차 일수 : %s\n",tmpEmp.getName(), remainDay);
		    			        		System.out.println("+---------------------------------------+");
				    			        System.out.println("| 🌟    🌟    🌟 연차신청 🌟     🌟    🌟  |");
				    			        System.out.println("+---------------------------------------+");
				    			        System.out.print("| 사유: ");
				    			        String Eu = scan.nextLine();
				    			        
				    			        //날짜기간을 겹치지 않도록하고, 종료 사용일보다 시작 사용일이 과거이면 안된다.
				    			        
				    			        boolean validateDate = false;
				    			        while(!validateDate) {
					    			        //연차사용 시작일
					    			        boolean validateStartDt = true;
					    					LocalDate startDt = null;
					    					while(validateStartDt) {
					    						validateStartDt = false;
					    						System.out.print("| 연차사용 시작 날짜 : ");
					    						// 1992-12-27 또는 19921227 로 받게끔
					    						String startDtStr = scan.nextLine();// 연차사용 문자열
					    						try {
					    							startDt = LocalDate.parse(startDtStr , formatter1); // 1992-12-27
					    						} catch (Exception e) {
					    							try {
					    								startDt = LocalDate.parse(startDtStr , formatter2); // 19921227
					    							} catch (Exception e2) {
					    								System.out.println("잘못된 표기법입니다. 입력한 날짜를 확인해주세요."); // 입력한 날자를 확인해주세요
					    								validateStartDt = true;
					    							}
					    						}
					    					}
					    					//연차사용 종료일
					    			        boolean validateEndDt = true;
					    					LocalDate endDt = null;
					    					while(validateEndDt) {
					    						validateEndDt = false;
					    						System.out.print("| 연차사용 종료 날짜 : ");
					    						// 1992-12-27 또는 19921227 로 받게끔
					    						 String endDtStr = scan.nextLine();// 연차사용 문자열
					    						try {
					    							endDt = LocalDate.parse(endDtStr , formatter1); // 1992-12-27
					    						} catch (Exception e) {
					    							try {
					    								endDt = LocalDate.parse(endDtStr , formatter2); // 19921227
					    							} catch (Exception e2) {
					    								System.out.println("잘못된 표기법입니다. 입력한 날짜를 확인해주세요."); // 입력한 날자를 확인해주세요
					    								validateEndDt = true;
					    							}
					    						}
					    					}
					    					
					    					if(startDt.isBefore(endDt) || startDt.isEqual(endDt)) {
					    						// startDt <=  endDt
					    						validateDate = true;
					    						long daysBetween = ChronoUnit.DAYS.between(startDt, endDt) + 1;
					    						System.out.printf("연차 신청한 기간 :  %d 일\n", daysBetween);
					    						
					    						
					    						
				    			        		holyProposeList.add(new HolyPropose(
				    			        				holyProposeList.size()+1,	// 순번 +1 추가
				    			        				1,							// 연차는 1 병가는 2
				    			        				startDt,					// 시작일
				    			        				endDt,						// 종료일
				    			        				endDt.compareTo(startDt)+1,	// 신청기간
				    			        				Eu,							// 이유
				    			        				0,							// 0. 대기, 1. 승인 -1. 반려
				    			        				tmpEmp.getEmpNo()			// 신청자 사번
				    			        				)); // 1. 신청건을 리스트에 추가하고
				    			        		saveHolyProposeListToFile(holyProposeList, holyProposeCSVFirstLine); // 2. 신청건을 파일에 저장
					    						
					    						
					    					} else {
					    						System.out.println("종료일은 시작일보다 과거일 수 없습니다. 다시 입력바랍니다.");
					    					}
				    			        }
				    			        break;
				    			    
				    			       
		    			        	case VacationMenu.병가신청 : // 2.병가신청
		    			        		System.out.println("+---------------------------------------+");
		    			        	    System.out.println("| 🌟    🌟    🌟 병가신청 🌟     🌟    🌟  |");
				    			        System.out.println("+---------------------------------------+");
				    			        System.out.print("| 사유: ");
				    			        String Eu2 = scan.nextLine();
				    			        
				    			        //날짜기간을 겹치지 않도록하고, 종료 사용일보다 시작 사용일이 과거이면 안된다.
				    			        boolean validateDate2 = false;
				    			        while(!validateDate2) {
					    			        //연차사용 시작일
					    			        boolean validateStartDt = true;
					    					LocalDate startDt = null;
					    					while(validateStartDt) {
					    						validateStartDt = false;
					    						System.out.print("| 병가사용 시작 날짜 : ");
					    						// 1992-12-27 또는 19921227 로 받게끔
					    						String startDtStr = scan.nextLine();// 연차사용 문자열
					    						try {
					    							startDt = LocalDate.parse(startDtStr , formatter1); // 1992-12-27
					    						} catch (Exception e) {
					    							try {
					    								startDt = LocalDate.parse(startDtStr , formatter2); // 19921227
					    							} catch (Exception e2) {
					    								System.out.println("잘못된 표기법입니다. 입력한 날짜를 확인해주세요."); // 입력한 날자를 확인해주세요
					    								validateStartDt = true;
					    							}
					    						}
					    					}
					    					//병가사용 종료일
					    			        boolean validateEndDt = true;
					    					LocalDate endDt = null;
					    					while(validateEndDt) {
					    						validateEndDt = false;
					    						System.out.print("| 병가사용 종료 날짜 : ");
					    						// 1992-12-27 또는 19921227 로 받게끔
					    						 String endDtStr = scan.nextLine();// 연차사용 문자열
					    						try {
					    							endDt = LocalDate.parse(endDtStr , formatter1); // 1992-12-27
					    						} catch (Exception e) {
					    							try {
					    								endDt = LocalDate.parse(endDtStr , formatter2); // 19921227
					    							} catch (Exception e2) {
					    								System.out.println("잘못된 표기법입니다. 입력한 날짜를 확인해주세요."); // 입력한 날자를 확인해주세요
					    								validateEndDt = true;
					    							}
					    						}
					    					}
					    					
					    					if(startDt.isBefore(endDt) || startDt.isEqual(endDt)) {
					    						// startDt <=  endDt
					    						validateDate2 = true;
					    						
					    						long daysBetween = ChronoUnit.DAYS.between(startDt, endDt) + 1;

					    						System.out.printf("병가 신청한 기간 :  %d 일\n", daysBetween);
					    						
					    						holyProposeList.add(new HolyPropose(
				    			        				holyProposeList.size()+1,	// 순번 +1 추가
				    			        				2,							// 연차는 1, 병가는 2
				    			        				startDt,					// 시작일
				    			        				endDt,						// 종료일
				    			        				endDt.compareTo(startDt)+1,	// 신청기간
				    			        				Eu2,							// 이유
				    			        				0,							// 0. 대기, 1. 승인 -1. 반려
				    			        				tmpEmp.getEmpNo()			// 신청자 사번
				    			        				)); // 1. 신청건을 리스트에 추가하고
				    			        		saveHolyProposeListToFile(holyProposeList, holyProposeCSVFirstLine); // 2. 신청건을 파일에 저장
				    			        		
					    					} else {
					    						System.out.println("종료일은 시작일보다 과거일 수 없습니다. 다시 입력해주세요.");
					    					}
				    			        }
				    			        break;
		    			        		
		    			        	case VacationMenu.종료 : // 3.종료
		    			        		isStop = true;
				    					break;
				    					
				    				default:
				    					break;
		    			        }
		    			     
		    					System.out.printf("%s 님 %s 신청이 완료 되었습니다.\n",tmpEmp.getName(),"휴가");
		    					break;
		    					
		    				case EmpMenu.종료 : // 2번 종료
		    					isStop = true;
		    					break;
		    				default:
		    					break;
		    			}
		    			
		    		}
		        } else if(firstMenuNo==2 && (tmpEmp != null)) { // 관리자 로그인
		        	boolean isStop = false;
		        	if(tmpEmp.getIsAdmin()==true) {
		        		while((tmpEmp.getIsAdmin()==true) && (isStop == false)) {
			    			System.out.printf("어서오십시오 관리자 [ %s ]님\n", tmpEmp.getName());
			    			System.out.printf("사원번호 : %s  /  이름 : %s  /  직급 : %s\n생년월일 : %s  /  전화번호 : %s\n입사년도 : %s  /  잔여 연차 : %s\n",
			    					tmpEmp.getEmpNo(),tmpEmp.getName(),tmpEmp.getPosition(),tmpEmp.getBirthDt(),
			    					tmpEmp.getPhoneNo(),tmpEmp.getHireDt(),tmpEmp.getHolyDayCnt());
			    			// 관리자 메뉴선택창
			    			Menu.IsAdminMenuDisplay();
			    			
			    			//메뉴번호 선택
			    			int no = Menu.inputNo(9);
			    			switch(no) { 
			    			
			    				case Menu.사원조회 : // 사원조회
			    					Menu.showEmployeeList(employeeList);
			    					System.out.println("사원의 리스트를 출력하였습니다.");
			    					break;
			    					
			    				case Menu.신규사원등록 : // 신규사원등록
			    					
			    					employeeList = Menu.newReg(employeeList);
			    					//사원리스트를 파일로 저장하는 함수
			    					saveEmployeeListToFile(employeeList, employeeCSVFirstLine);
			    					break ;
			    					
			    				case Menu.사원정보수정 : // 사원 정보 수정
			    					isStop = false;
			    					System.out.printf("수정할 사원의 사번 > ");
			    					String inputEmpNo = scan.nextLine(); // 내가 입력한 값
			    					Employee findEmployee = null; // 찾은 것을 담을려고 만든 빈 객체
			    					for( Employee emp : employeeList ) { // employeeList를 순회하면서 하나씩 emp에 넣고 아래를 돌림
			    						// emp ==> 첫번째 : 9999, 관리자....
			    						// emp ==> 두번째 : 1000, 최효진....
			    						// ......
			    						// emp ==> n번째 : 1020, 누구누구....
			    						if(emp.getEmpNo().equals(inputEmpNo)) { // 찾으려는 사번 : 1020
			    							// emp ==> 첫번째 : 9999, 관리자....
				    						// emp ==> 두번째 : 1000, 최효진....
				    						// ......
				    						// emp ==> n번째 : 1020, 누구누구....
				    						// 두번째 돌 때 1000번을 찾았지만, n번쨰까지 계속 돌음
			    							findEmployee = emp;
			    							// 두번쨰 돌 때 찾은 데이터(emp)를 findEmployee에 넣음.
		    							}
			    					}
			    					if (findEmployee==null) { // 데이터가 있으면 안돌고, 데이터가 없어서 null인 상태이면 아래가 돌고
			    						System.out.printf("[%s] 해당 사번은 없습니다.\n", inputEmpNo); // null이랑 일치해서 돌면 보여줄 메시지가 이것임.
			    					
			    					} else {
			    					
			    						boolean isChangeDone = false;
			    						while(!isChangeDone) {
			    							Menu.EmpNoInformation(findEmployee);
			    							int empModifyNo = Menu.inputNo(4);
			    							if(empModifyNo == EmpModifyMenu.이름변경) {
			    								System.out.println("변경하실 이름을 입력해주세요 > ");
			    								String chgName = scan.nextLine();	
			    								
			    								employeeList.remove(findEmployee); // n번쨰 녀석의 이름
			    								findEmployee.setName(chgName); // 이 녀석의 이름
			    								employeeList.add(findEmployee);
			    								
			    								saveEmployeeListToFile(employeeList, employeeCSVFirstLine);
			    						
			    							} else if(empModifyNo == EmpModifyMenu.직급변경) {
			    								System.out.println("변경하실 직급을 입력해주세요 > ");
			    								String chgPosition = scan.nextLine();
			    								
			    								employeeList.remove(findEmployee); 
			    								findEmployee.setPosition(chgPosition);
			    								employeeList.add(findEmployee);
			    								
			    								saveEmployeeListToFile(employeeList, employeeCSVFirstLine);
			    								
			    							} else if (empModifyNo == EmpModifyMenu.전화번호수정) {
			    								System.out.println("변경하실 전화번호를 입력해주세요 > ");
			    								String chgPhoneNum = scan.nextLine();
			    								
			    								employeeList.remove(findEmployee); // n번쨰 녀석의 이름
			    								findEmployee.setPhoneNo(chgPhoneNum); // 이 녀석의 이름
			    								employeeList.add(findEmployee);
			    								
			    								saveEmployeeListToFile(employeeList, employeeCSVFirstLine);
			    								System.out.println("전화번호 변경 완료!");
			    						
			    							} else if(empModifyNo == EmpModifyMenu.종료) {
			    								isChangeDone = true;
			    							
			    							}
			    						}
			    					}
					    	        break;
					    	        
			    				case Menu.사원정보삭제 : // 사원정보삭제
			    					
			    					System.out.println("삭제할 사원의 사번을 입력 > ");
			    					String deleteEmpNo = scan.nextLine();
			    					boolean removeFlag = true;
			    					Employee deleteEmployee = null; // 삭제할 놈의 정보가 들어갈 객체
			    					for ( Employee Emp1 : employeeList ) { // 사원 리스트를 하나씩 돌면서 n번째 놈의 정보를 Emp1에 넣음
			    						if(Emp1.getEmpNo().equals(deleteEmpNo)) { // n번째 사원의 정보 중에 사번이 내가 입력한 사번과 같으면 실행
			    							System.out.printf("%s(%s)사원의 정보가 삭제 되었습니다.\n", Emp1.getEmpNo(), Emp1.getName());
			    							deleteEmployee = Emp1; // 삭제할 놈의 정보 Emp1인데 그걸  deleteEmployee에 집어 넣음.
			    							removeFlag = false; // 삭제할 놈을 찾았다는 의미로 깃발을 내림
			    						}
			    					}
			    					
			    					if(removeFlag) { // 깃발을 들고 있으면 삭제할 놈이 없다는 거고
			    						System.out.printf("삭제할 %s 사번이 없습니다.\n", deleteEmpNo);
			    					} else { // 그게 아니면 아래 실행
			    						employeeList.remove(deleteEmployee); // 리스트에서 '그' 놈을 삭제함.
			    					}
			    					
			    					//사원리스트를 파일로 저장하는 함수
			    					saveEmployeeListToFile(employeeList, employeeCSVFirstLine);
			    					break;
					    	        
			    				case Menu.휴가신청_리스트_확인 : // 병가 신청 리스트 확인
			    					//1. 숫자 순서대로 휴가신청한 사원의 리스트를 정리한다
			    					//2. 연차 or 병가 타입을 보여준다.
			    					//3. 시작일 / 종료일 을 보여준다.
			    					//4. 사유: 를 간단히 보여준다
			    					//5. 대기/승인/반려 가 되었는지 보여준다.
			    					//6. 사번을 보여준다.
			    					boolean isChanged = false;
			    					while(!isChanged) {
			    						Collections.sort(holyProposeList, Comparator.comparing(HolyPropose::getReqId)); // 신청번호를 순차적으로 정리해줌
			    						System.out.println("+---------------------------------------+");
		    			        	    System.out.println("| 🌟   🌟  🌟 휴가 신청 리스트 🌟   🌟   🌟  |");
				    			        System.out.println("+---------------------------------------+");
				    			        System.out.println("| 신청번호 |   사원   |  신청일수  |   결재상태  |");
				    			        
				    			        for(HolyPropose hp: holyProposeList) {
				    			        	Employee emp = null;
				    			        	for (Employee emp2: employeeList) {
				    			        		if(hp.getEmpNo().equals(emp2.getEmpNo())) {
				    			        			emp = emp2;
				    			        		}
				    			        	}
				    			        	System.out.printf("|   %d   |  %s   |   %d 일   |    %s    |\n",hp.getReqId(), emp.getName(), hp.getUsedDay(), hp.getStatStr());
				    			        	
				    			        }
				    			        System.out.println("+---------------------------------------+");
				    			        System.out.print("(0번 입력시 메뉴로 돌아가기) ");
				    			        int vacationSelect = Menu.inputNo0(holyProposeList.size());
				    			        if (vacationSelect == 0) {
				    			        	isChanged = true;
				    			        	break;
				    			        }
				    			        	
				    			        HolyPropose hp = null;
				    			        
				    			        for(HolyPropose tmpHP: holyProposeList) {
				    			        	if(tmpHP.getReqId()==vacationSelect) {
				    			        		hp = tmpHP;// 선택한 신청건 (1건)
				    			        	}
				    			        }
				    			        
				    			        Employee emp = null; // 그 신청건의 사원 정보 (처음에는 빈값)
			    			        	for (Employee emp2: employeeList) {
			    			        		if(hp.getEmpNo().equals(emp2.getEmpNo())) {
			    			        			emp = emp2;
			    			        		}
			    			        	}
				    			        
				    			        
				    			        
				    			        
				    			        System.out.println("+---------------------------------------+");
		    			        	    System.out.printf("| 🌟   🌟  🌟  %s 신청서    🌟   🌟   🌟  |\n", hp.getReqTypeStr() );
				    			        System.out.println("+---------------------------------------+");
				    			        System.out.printf("| 사 번 :  %s  |  사원명 : %s          |\n",emp.getEmpNo(), emp.getName());
				    			        System.out.printf("| 시작일 : %s | 종료일 : %s |\n", hp.getStartDt(), hp.getEndDt());
				    			        System.out.printf("| 결재 상태 : %s | %s기간 : %d일            |\n", hp.getStatStr(), hp.getReqTypeStr(), hp.getUsedDay());
				    			        System.out.printf("| 사 유 : %s \n", hp.getEu());
				    			        System.out.println("+---------------------------------------+");
				    			        System.out.println("| 1. 뒤로가기 | 2. 승인  | 3.반려  | 4. 대기  |");
				    			        System.out.println("+---------------------------------------+");
				    			        int statNo = Menu.inputNo(4);
				    			        
				    			        if(statNo == 1) {
				    			        	// 뒤로 가기를 누르면 아무것도 안하는 게 맞음
				    			        } else if(statNo == 2) {// 승인을 하는 거
				    			        	if(hp.getReqType() == 1) { // 이게 연차 여기서는 날짜가 감소
				    			        		// 이게 사원들의 연차 목록 리스트임 
				    			        		// 여기서 그 사원의 기록을 찾아서 감소 시켜야 함 
				    			        		holyProposeList.remove(hp); // 1. 기존 리스트에서 해당 신청건을 제거함
				    			        		hp.setStat(1); // 2. 신청건을 승인으로 바꾸고
				    			        		holyProposeList.add(hp); // 3. 바꾼 신청건을 리스트에 추가하고
				    			        		saveHolyProposeListToFile(holyProposeList, holyProposeCSVFirstLine); // 4. 신청건을 파일에 저장
				    			        		
				    			        		
				    			        		HolyCnt latestHC = null;
				    			        		for(HolyCnt hc: holyCntList) {
				    			        			if(hc.getEmpNo().equals(emp.getEmpNo())) {
				    			        				latestHC = hc;
				    			        			}
				    			        		}
				    			        		holyCntList.add(new HolyCnt(
			    			        				holyCntList.size()+1, 		// 휴가일수 목록 순번
			    			        				latestHC.getYear(), 		// 매년
			    			        				latestHC.getTotalDay(), 	// 총 휴가일수
			    			        				latestHC.getUsedDay()+hp.getUsedDay(),	// 누적 사용일수
			    			        				latestHC.getRemainDay()-hp.getUsedDay(),	// 잔여 휴가일수
			    			        				latestHC.getEmpNo()		// 사번
				    			        		));
				    			        		saveHolyCntListToFile(holyCntList, holyDtCntCSVFirstLine);
				    			        		System.out.printf("%s 님의 %s 신청 결재를 '%s' 로 변경 하였습니다.\n", emp.getName(), hp.getReqTypeStr(), hp.getStatStr());
				    			        		
				    			        	} else if(hp.getReqType() == 2) { // 이게 병가 여기서는 날짜 감소없음
				    			        		holyProposeList.remove(hp); // 1. 기존 리스트에서 해당 신청건을 제거함
				    			        		hp.setStat(1); // 2. 신청건을 승인으로 바꾸고
				    			        		holyProposeList.add(hp); // 3. 바꾼 신청건을 리스트에 추가하고
				    			        		saveHolyProposeListToFile(holyProposeList, holyProposeCSVFirstLine); // 4. 신청건을 파일에 저장
				    			        		
				    			        		
				    			        		HolyCnt latestHC = null;
				    			        		for(HolyCnt hc: holyCntList) {
				    			        			if(hc.getEmpNo().equals(emp.getEmpNo())) {
				    			        				latestHC = hc;
				    			        			}
				    			        		}
				    			        		holyCntList.add(new HolyCnt(
			    			        				holyCntList.size()+1, 		// 휴가일수 목록 순번
			    			        				latestHC.getYear(), 		// 매년
			    			        				latestHC.getTotalDay(), 	// 총 휴가일수
			    			        				latestHC.getUsedDay()+0,	// 누적 사용일수
			    			        				latestHC.getRemainDay()-0,	// 잔여 휴가일수
			    			        				latestHC.getEmpNo()		// 사번
				    			        		));
				    			        		saveHolyCntListToFile(holyCntList, holyDtCntCSVFirstLine);
				    			        		System.out.printf("%s 님의 %s 신청 결재를 '%s' 로 변경 하였습니다.\n", emp.getName(), hp.getReqTypeStr(), hp.getStatStr());
				    			        	}
				    			        } else if(statNo == 3) { // 반려 하는 곳 stat 1 : 승인, -1 : 반려, 0 : 대기 
				    			        	if(hp.getStat()==1 && hp.getReqType()==1) { // 이미 승인이 된 것이었으면 사용일수 복구
				    			        		HolyCnt latestHC = null;
				    			        		for(HolyCnt hc: holyCntList) {
				    			        			if(hc.getEmpNo().equals(emp.getEmpNo())) {
				    			        				latestHC = hc;
				    			        			}
				    			        		}
				    			        		holyCntList.add(new HolyCnt(
			    			        				holyCntList.size()+1, 		// 휴가일수 목록 순번
			    			        				latestHC.getYear(), 		// 매년
			    			        				latestHC.getTotalDay(), 	// 총 휴가일수
			    			        				latestHC.getUsedDay()-hp.getUsedDay(),	// 누적 사용일수
			    			        				latestHC.getRemainDay()+hp.getUsedDay(),	// 잔여 휴가일수
			    			        				latestHC.getEmpNo()		// 사번
				    			        		));
				    			        		saveHolyCntListToFile(holyCntList, holyDtCntCSVFirstLine);
				    			        	}
				    			        	// 반려를 하고 저장 여기서는 반려니까 날짜 감소는 없음
				    			        	holyProposeList.remove(hp); // 1. 기존 리스트에서 해당 신청건을 제거함
			    			        		hp.setStat(-1); // 2. 신청건을 반려로 바꾸고
			    			        		holyProposeList.add(hp); // 3. 바꾼 신청건을 리스트에 추가하고
			    			        		
			    			        		saveHolyProposeListToFile(holyProposeList, holyProposeCSVFirstLine); // 4. 신청건을 파일에 저장
			    			        		System.out.printf("%s 님의 %s 신청 결재를 '%s' 로 변경 하였습니다.\n", emp.getName(), hp.getReqTypeStr(), hp.getStatStr());
			    			        		
				    			        } else if((statNo == 4) && (hp.getStat()!=0) ) {
				    			        	// 대기로 변경하고 저장 여기서는 기존에 감소 시킨걸 되돌야함 
				    			        	if(hp.getReqType() == 1) { // 이게 연차 여기서는 날짜를 복구
				    			        		holyProposeList.remove(hp); // 1. 기존 리스트에서 해당 신청건을 제거함
				    			        		hp.setStat(0); // 2. 신청건을 대기로 바꾸고
				    			        		holyProposeList.add(hp); // 3. 바꾼 신청건을 리스트에 추가하고
				    			        		saveHolyProposeListToFile(holyProposeList, holyProposeCSVFirstLine); // 4. 신청건을 파일에 저장
				    			        		
				    			        		
				    			        		HolyCnt latestHC = null;
				    			        		for(HolyCnt hc: holyCntList) {
				    			        			if(hc.getEmpNo().equals(emp.getEmpNo())) {
				    			        				latestHC = hc;
				    			        				System.out.printf("%s 님의 %s 신청 결재를 '%s' 로 변경 하였습니다.\n", emp.getName(), hp.getReqTypeStr(), hp.getStatStr() );
				    			        			}
				    			        		}
				    			        		holyCntList.add(new HolyCnt(
			    			        				holyCntList.size()+1, 		// 휴가일수 목록 순번
			    			        				latestHC.getYear(), 		// 매년
			    			        				latestHC.getTotalDay(), 	// 총 휴가일수
			    			        				latestHC.getUsedDay()-hp.getUsedDay(),	// 누적 사용일수
			    			        				latestHC.getRemainDay()+hp.getUsedDay(),	// 잔여 휴가일수
			    			        				latestHC.getEmpNo()		// 사번
				    			        		));
				    			        		saveHolyCntListToFile(holyCntList, holyDtCntCSVFirstLine);
				    			        		System.out.printf("%s 님의 %s 신청 결재를 '%s' 로 변경 하였습니다.\n",emp.getName(), hp.getReqTypeStr(), hp.getStatStr() );
				    			        		
				    			        	} else if(hp.getReqType() == 2) { // 이게 병가 여기서는 날짜 감소없음
				    			        		holyProposeList.remove(hp); // 1. 기존 리스트에서 해당 신청건을 제거함
				    			        		hp.setStat(0); // 2. 신청건을 대기로 바꾸고
				    			        		holyProposeList.add(hp); // 3. 바꾼 신청건을 리스트에 추가하고
				    			        		saveHolyProposeListToFile(holyProposeList, holyProposeCSVFirstLine); // 4. 신청건을 파일에 저장
				    			        	}
				    			        }
			    					}
			    					
			    					// 신청서를 저장하고 종료
			    					// 저장하는 로직
			    					
			    					break;
			    					
			    					
			    				case Menu.오래된순_정렬 : // 오름차순 정렬
			    					Collections.sort(employeeList);
			    					System.out.println("사원의 입사년도가 '가장 오래된 순' 으로 정리합니다.\n");
			    					break;
			    					
			    				case Menu.최신순_정렬 : // 내림차순 정렬
			    					Collections.sort(employeeList,Collections.reverseOrder());
			    					System.out.println("사원의 입사년도가 '가장 최신순' 으로 정리합니다.\n");
			    					break;
			    					
			    				case Menu.종료 : // 저장하고 로그인창으로 돌아가기 
			    					isStop = true;
			    					break;
			    					

			    				default:
			    					break;
			    			}
			    		}
		        	}
		        }
	        } else {
	        	System.out.printf("쉬자Go 프로그램을 종료합니다");
	        	mainMenuStop = true;
	        }
		 }
	}

	//파일에 저장해주는 코드
	

	//사원리스트를 파일로 저장하는 함수
	public static void saveEmployeeListToFile(List<Employee> employeeList, String employeeCSVFirstLine) throws FileNotFoundException {
		// 위 리스트를 파일에 저장하는 코드
		FileOutputStream fos = new FileOutputStream("./src/chj/data/employee.csv");
		PrintStream ps = new PrintStream(fos);
		
		//파일의 맨 윗줄을 추가해주는 코드
		ps.printf("%s", employeeCSVFirstLine);
		for ( Employee emp : employeeList ) {
			// 1002,0002,김지현,2000-07-31,2025-01-01,010-4321-8765,대리,15,FALSE
			
			ps.printf("\n%s,%s,%s,%s,%s,%s,%s,%d,%s",emp.getEmpNo(),emp.getPassword(),emp.getName(),emp.getBirthDt(),
					emp.getHireDt(),emp.getPhoneNo(),emp.getPosition(),emp.getHolyDayCnt(),emp.getIsAdmin());
		}
		ps.close();
	}
	
	public static void saveHolyCntListToFile(List<HolyCnt> list, String CSVFirstLine) throws FileNotFoundException {
		// 위 리스트를 파일에 저장하는 코드
		FileOutputStream fos = new FileOutputStream("./src/chj/data/holy_dt_cnt.csv");
		PrintStream ps = new PrintStream(fos);
		
		//파일의 맨 윗줄을 추가해주는 코드
		ps.printf("%s", CSVFirstLine);
		
		for ( HolyCnt hc : list ) {
			ps.printf("\n%d,%d,%d,%d,%d,%s",
					hc.getEmpHolyProposeId(), hc.getYear(), 
					hc.getTotalDay(), hc.getUsedDay(), 
					hc.getRemainDay(), hc.getEmpNo() );
		}
		ps.close();
	}
	
	public static void saveHolyProposeListToFile(List<HolyPropose> list, String CSVFirstLine) throws FileNotFoundException {
		// 위 리스트를 파일에 저장하는 코드
		FileOutputStream fos = new FileOutputStream("./src/chj/data/holy_propose.csv");
		PrintStream ps = new PrintStream(fos);
		
		//파일의 맨 윗줄을 추가해주는 코드
		ps.printf("%s", CSVFirstLine);
		for ( HolyPropose hp : list ) {
			ps.printf("\n%d,%d,%s,%s,%d,%s,%d,%s",
					hp.getReqId(), hp.getReqType(), 
					hp.getStartDt(), hp.getEndDt(), 
					hp.getUsedDay(), hp.getEu(), 
					hp.getStat(), hp.getEmpNo() );
		}
		ps.close();
	}
		
	
	//Scanner 데이터를 입력 값으로 받아서 Employee를 반환하겠다는 의미의 함수(매소드)
	public static Employee loadEmp(Scanner scan) {
		String data = scan.nextLine();
		
		// "9999,0000,관리자,1950-06-25,2024-01-01,010-1234-5678,사장,30,true" <= 이렇게 되어 있는 것의 ','콤마들로 전부 나눠서
		// 이렇게 만드는거임 => {"9999","0000","관리자","1950-06-25","2024-01-01","010-1234-5678","사장","30","true"}
		String[] tokens = data.split(",");

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		String empNo = tokens[0];			//사번				
		String password = tokens[1];		//비번				
		String name = tokens[2];			//이름
		String birthDtString = tokens[3]; 	//생년월일 (문자열)
		String hireDtString = tokens[4];	//입사날짜 문자열
		String phoneNo = tokens[5];			//전화번호
		String position = tokens[6];		//직급
		int holyDtCnt = Integer.parseInt(tokens[7]);	//연차갯수
		boolean isAdmin = Boolean.parseBoolean(tokens[8]);	//관리자여부
		LocalDate birthDt = LocalDate.parse(birthDtString, formatter);		// 생년월일 날짜값
		LocalDate hireDt = LocalDate.parse(hireDtString, formatter);		// 입자날짜 날짜값
		
		return new Employee(empNo, password, phoneNo, name, birthDt, hireDt, position, holyDtCnt, isAdmin);
	}
}


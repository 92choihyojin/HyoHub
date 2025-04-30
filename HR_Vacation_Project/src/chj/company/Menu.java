package chj.company;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Menu {

	//로그인 메뉴선택 창
	public static void LoginMenuDisplay() { 
        System.out.println("+---------------------------------------+");
        System.out.println("| 🌟     🌟    🌟 쉬자Go 🌟    🌟     🌟  |");
        System.out.println("+---------------------------------------+");
        System.out.println("| 1. 사용자 로그인                   	|");
        System.out.println("| 2. 관리자 로그인                    	|");
        System.out.println("| 3. 종료                          	|");
        System.out.println("+---------------------------------------+");
	}
	
	
	
	//관리자 메뉴선택 창
	public static void IsAdminMenuDisplay() {
		System.out.println("+---------------------------------------+");
        System.out.println("| 🌟  🌟   🌟 관 리 자  메 뉴 🌟   🌟  🌟   |");
        System.out.println("+---------------------------------------+");
        System.out.println("| 1. 사원조회            6. 오래된순 정렬     |");
        System.out.println("| 2. 신규사원등록         7. 최신순 정렬       |");
        System.out.println("| 3. 사원정보수정         8. 종료            |");
        System.out.println("| 4. 사원정보삭제                           |");
        System.out.println("| 5. 휴가신청_리스트_확인                     |");
        System.out.println("+---------------------------------------+");
	}
	
	// 관리자 페이지 메뉴목록
	public static final int 사원조회 = 1;
	public static final int 신규사원등록 = 2;
	public static final int 사원정보수정 = 3;
	public static final int 사원정보삭제 = 4;
	public static final int 휴가신청_리스트_확인 = 5;
	public static final int 오래된순_정렬 = 6;
	public static final int 최신순_정렬 = 7;
	public static final int 종료 = 8;
	
	// 사원정보수정 메뉴창
	public static void EmpNoInformation(Employee findEmployee) {
		System.out.println("+---------------------------------------+");
        System.out.println("| 🌟    🌟   쉬자Go 정보수정 메뉴   🌟    🌟  |");
        System.out.println("+---------------------------------------+");
        System.out.printf("| 1. [ %s ] 이름변경                     |\n",findEmployee.getName());
        System.out.printf("| 2. [ %s ] 직급변경                      |\n",findEmployee.getPosition());
        System.out.printf("| 3. [ %s ] 전화번호 수정        |\n",findEmployee.getPhoneNo());
        System.out.println("| 4. 종료                                |");
        System.out.println("+---------------------------------------+");
	}
	
	// 사원 메뉴선택 창
	public static void EmpMenuDisplay() {
		System.out.println("+---------------------------------------+");
        System.out.println("| 🌟     🌟     🌟 메뉴 🌟     🌟      🌟 |");
        System.out.println("+---------------------------------------+");
        System.out.println("| 1. 휴가신청                              |");
        System.out.println("|                                       |");
        System.out.println("| 2. 종료                                |");
        System.out.println("+---------------------------------------+");
		
	}
	
	// 번호 선택하는 함수
		public static int inputNo0(int maxNumber) { // 파라미터(parameter) 매개변수 0~n번 전용
				Scanner scan = new Scanner(System.in);

				int choice = 0;
				while(true) {
					System.out.printf("번호 선택 > ");
					String input = scan.nextLine();
					boolean isInputCheck = Pattern.matches("^[0-"+maxNumber+"]$", input);
					if (isInputCheck) {
						choice = Integer.parseInt(input);
						break;
					} else {
						System.out.println("올바른 번호를 입력해주세요.");
					}
				}
				return choice; // 리턴밸류(return value) 반환값
		}
	
	// 번호 선택하는 함수
	public static int inputNo(int maxNumber) { // 파라미터(parameter) 매개변수
			Scanner scan = new Scanner(System.in);

			int choice = 0;
			while(true) {
				System.out.printf("번호 선택 > ");
				String input = scan.nextLine();
				boolean isInputCheck = Pattern.matches("^[1-"+maxNumber+"]$", input);
				if (isInputCheck) {
					choice = Integer.parseInt(input);
					break;
				} else {
					System.out.println("올바른 번호를 입력해주세요.");
				}
			}
			return choice; // 리턴밸류(return value) 반환값
	}
	
	// 사원조회 //페이징기법으로 6명씩 출력한다.
	public static void showEmployeeList(List<Employee> employeeList) {
		
//		System.out.println(employeeList.size());
		int pageIndex = 1;			// 페이지 장수
		while(pageIndex != 0) {		// 페이지 장수가 0이 아닐 때
			int pageSize = 6;		// 한페이지에서 보여줄 목록 겟수
			int pageLength = employeeList.size()/pageSize;  // 예 5/6 = 0.833.... => 0장 
			if (employeeList.size() % pageSize !=0) { // 예 5/6 나누기 하고 난 나머지 값을 구하는 연산자 (%) = 0하고 나머지가 5
				// employeeList.size()  == 5
				// pageSize  == 6
				// 5 % 6 = 5
				// 5 != 0 => true
				
				pageLength++;
			}
			// pageLength => 0 + 1 => 1
			// pageLength == 1
			System.out.println("――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――");
			if ((pageIndex > pageLength) || (pageIndex < 0)) {  // ==> if(false) {}
				// pageIndex == -1 사용자가 입력하는 장수
				// pageLength == 2 실제 페이지 장수
				// pageIndex > pageLength
				// -1 > 2 == 거짓
				// 2 이상 입력하면 아래 출력
				
				// pageIndex == -1 사용자가 입력하는 장수
				// pageIndex < 0
				System.out.println("올바른 페이지를 입력 > ");
			} else {
				// pageSize == 6   // 한페이지에 보여줄 목록 갯수
				// pageLength == 3 // 실제 페이지 장수
				
				// pageIndex == 2  // 사용자가 입력하는 장수
				System.out.println("현재 페이지 : " + pageIndex + "/" + pageLength);
				// 현재 페이지 : 2/3
				for	(int i = 0; i < employeeList.size(); i++) {
					// employeeList.size()  == 15
					if ( i >= (pageIndex * pageSize) - pageSize) {
						// 0 >= (2 * 6)-6 | 0 >= 6
						// 1 >= (2 * 6)-6 | 1 >= 6
						// 2 >= (2 * 6)-6 | 2 >= 6
						// 3 >= (2 * 6)-6 | 3 >= 6
						// 4 >= (2 * 6)-6 | 4 >= 6
						// 5 >= (2 * 6)-6 | 5 >= 6
						// 6 >= (2 * 6)-6 | 6 >= 6  // 여기부터 2번쨰 페이지 시작
						// .....
						// 14 >= (2 * 6)-6
						if(i <=(pageIndex * pageSize) - 1) {
							// 0 <= (2 * 6) - 1 | 0 <= 11
							// ......
							// 11 <= (2 * 6) - 1 | 11 <= 11 여기까지 2번째 페이지 끝
							
							// 12 <= (2 * 6) - 1 | 12 <= 11 여기부터 3번째 페이지
							// 13 <= (2 * 6) - 1 | 13 <= 11
							// 14 <= (2 * 6) - 1 | 14 <= 11
							System.out.println(employeeList.get(i));
							// System.out.println(employeeList.get(6)); 여기부터 2번쨰 페이지 시작
							// System.out.println(employeeList.get(7));
							// ....
							// System.out.println(employeeList.get(11)); 여기까지 2번째 페이지 끝
							
						}
					}
				}
			} // else 끝나는 곳
			System.out.println("페이지 번호 입력 (0 입력시 종료) > ");
			pageIndex = Integer.parseInt(new Scanner(System.in).nextLine());

		} // 페이지 장수를 입력하는 곳, (0 입력시 종료)
		// pageIndex => 사용자가 입력하는 장수
		System.out.println("사원조회 종료");
	}



	// 신규사원 등록 함수
	public static List<Employee> newReg(List<Employee> employeeList) {
		Scanner scan = new Scanner(System.in);
		
		DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyyMMdd");
		
		boolean validateEmpNo = true;		// validate 검증
		String newEmpNo = null;
		while (validateEmpNo) {
			validateEmpNo = false;
			System.out.print("신규사원 등록할 사번 > ");
			newEmpNo = scan.nextLine();
			// 사번을 입력을 했을 떄 기존에 있는 사번이면 안되게 하고 싶음
			for(Employee employee : employeeList)	{
				if(employee.getEmpNo().equals(newEmpNo)) {
					System.out.println("해당 사번은 중복됩니다. 다시 입력해주세요");
					newEmpNo = null;
					validateEmpNo = true;
				}
			}
		}
		
		
		System.out.print("신규사원 등록할 비밀번호 > ");
		String newPassword = scan.nextLine();
		
		System.out.print("신규사원 등록할 이름 > ");
		String newName = scan.nextLine();
		
		
		boolean validateBirthDt = true;
		LocalDate newBirthDt = null;
		while(validateBirthDt) {
			validateBirthDt = false;
			System.out.print("신규사원 등록할 생년월일 > ");
			// 1992-12-27 또는 19921227 로 받게끔
			String newBirthDtSt = scan.nextLine();// 생년월일 문자열
			try {
				newBirthDt = LocalDate.parse(newBirthDtSt , formatter1); // 1992-12-27
			} catch (Exception e) {
				try {
					newBirthDt = LocalDate.parse(newBirthDtSt , formatter2); // 19921227
				} catch (Exception e2) {
					System.out.println("잘못된 표기법입니다. 입력한 날짜를 확인해주세요."); // 입력한 날자를 확인해주세요
					validateBirthDt = true;
				}
			}
		}
		
		boolean validateHireDt = true;
		LocalDate newHireDt = null;
		while(validateHireDt) {
			validateHireDt = false;
			System.out.print("신규사원 등록할 입사년도 > ");
			String newHireDtSt = scan.nextLine();
			try {
				newHireDt = LocalDate.parse(newHireDtSt, formatter1); // 1992-12-27
			} catch (Exception e) {
				try {
					newHireDt = LocalDate.parse(newHireDtSt, formatter2);// 19921227
				} catch (Exception e2) {
					System.out.println("잘못된 표기법입니다."); // 입력한 날자를 확인해주세요
					validateHireDt = true;
				}
			}
		}
		
		System.out.print("신규사원 등록할 전화번호 > ");
		String newPhoneNo = scan.nextLine();
		
		System.out.print("신규사원 등록할 직책 > ");
		String newPosition = scan.nextLine();
		
		System.out.print("신규사원 등록할 연차갯수 > ");
		int newHolyDtCnt = Integer.parseInt(scan.nextLine());
		
		
		boolean validateIsAdmin = true;
		boolean isAdmin = false;
		while(validateIsAdmin) {
			validateIsAdmin = false;
			System.out.print("신규사원의 관리자 여부( Y , N ) > ");
			String isAdminSt = scan.nextLine();
			if(isAdminSt.equals("Y")) {
				isAdmin = true;
			} else if(isAdminSt.equals("N")) {
				isAdmin = false;
			} else {
				System.out.println("잘못된 입력값입니다. Y나 N을 입력해주세요.");
				validateIsAdmin = true;
			}
		}
		
		Employee addEmployee = new Employee(newEmpNo, newPassword, newPhoneNo, newName, newBirthDt, newHireDt, newPosition, newHolyDtCnt, isAdmin);
		employeeList.add(addEmployee);
		System.out.println("\n신규사원이 성공적으로 등록 되었습니다!\n");
		
		return employeeList;
	}
	
}
	

package chj.companyMenu;

import java.util.Scanner;
import java.util.regex.Pattern;

public class VacationMenu {
	public static final int 연차신청 = 1;
	public static final int 병가신청 = 2;
	public static final int 종료 = 3;
	
	public static void Choice() {
		System.out.println("+---------------------------------------+");
        System.out.println("| 🌟     🌟     🌟 메뉴 🌟     🌟     🌟  |");
        System.out.println("+---------------------------------------+");
        System.out.println("| 1. 연차신청                              |");
        System.out.println("| 2. 병가신청                              |");
        System.out.println("| 3. 종료                                |");
        System.out.println("+---------------------------------------+");
		
	}

	public static void List() {
		System.out.println("+---------------------------------------+");
        System.out.println("| 🌟    🌟  쉬자Go 휴가 신청 리스트  🌟    🌟 |");
        System.out.println("+---------------------------------------+");
        System.out.println("                                    ");
        System.out.println("                                     ");
        System.out.println("                                     ");
        System.out.println("                                     ");
        System.out.println("                                     ");
        System.out.println("                                     ");
       
        System.out.println("번호 입력 (0 입력시 종료) > ");
		
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
}

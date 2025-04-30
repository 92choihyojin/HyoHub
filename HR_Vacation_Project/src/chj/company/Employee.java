package chj.company;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Date;



public class Employee implements Comparable{
	// 사번, 이름, 생년월일(6자리), 전화번호, 직책, 연차갯수
	private String empNo; // 사번                 수정>>사번은 4자리로만 int 값으로 
	private String password; // 비번
	private String name; // 이름
	private LocalDate birthDt; // 생년월일
	private LocalDate hireDt; // 입사날짜
	private String phoneNo; // 전화번호 
	private String position; // 직책
	private int holyDtCnt; // 연차갯수
	private boolean isAdmin; // 관리자 여부
	
	
	
	// 생성자들
	public Employee() {
		
	} // 기본 생성자

	// 사원을 추가 할 때 쓰는 생성자
	public Employee(String name, LocalDate birthDt, String phoneNo, String position) {
		this.empNo = generateEmpNo(); // 사번을 자동으로 생성되게 만들어야 함
		this.password = "1234"; // 처음 사원을 등록할 때는 1234로 초기값 셋팅
		this.name = name;
		this.birthDt = birthDt;
		this.phoneNo = phoneNo;
		this.hireDt = LocalDate.now(); // 처음 사원을 등록한 날짜로 셋팅
		this.position = position;
		this.holyDtCnt = generateHolyDayCnt(LocalDate.now()); // 포지션에 따라서 15~ 생성되게 해야 함 
		this.isAdmin = false;			//관리자가 아님
	}
	
	// 파일에 저장된 사원의 정보를 담는 생성자 
	public Employee(String empNo, String password, String phoneNo, String name, LocalDate birthDt, LocalDate hireDt, String position, int holyDayCnt, boolean isAdmin) {
		this.isAdmin = isAdmin;		//관리자
		this.password = password;
		this.phoneNo = phoneNo;
		this.empNo = empNo;			
		this.name = name;
		this.birthDt = birthDt;
		this.hireDt = hireDt;
		this.position = position;
		this.holyDtCnt = holyDayCnt;
	}

	public String getEmpNo() {
		return empNo;
	}

	public LocalDate getBirthDt() {
		return birthDt;
	}
	public boolean getIsAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public LocalDate getHireDt() {
		return hireDt;
	}

	public void setBirthDt(LocalDate birthDt) {
		this.birthDt = birthDt;
	}
	
	public void setHireDt(LocalDate hireDt) {
		this.birthDt = hireDt;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getHolyDayCnt() {
		return holyDtCnt;
	}

	public void setHolyDayCnt(int holyDayCnt) {
		this.holyDtCnt = holyDayCnt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	@Override
	public String toString() {
		return "쉬자GO [사번= " + empNo + ", 패스워드= " + password + ", 이름= " + name + ", 생년월일= " + birthDt
				+ ", 입사년도= " + hireDt + ", 전화번호= " + phoneNo + ", 직책= " + position + ", 연차갯수= " + holyDtCnt
				+ "]";
	}

	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;			// 관리자인지 아닌지 설정하기 위함
	}
	
	public String generateEmpNo() { // EmpNo를 생성하는 함수
		// 여기서 자동으로 생성되게 만들어야 함.
		// 자동으로 만들어야 하는데, 조건 : 기존에 있는 사번과 겹치면 안됨.
		// 겹치지 않기 위해서는 기존 사번을 가져와서
		// 날짜 + 시간 + 랜덤 값으로 만들어진 사번과 기존 사번을 비교해서 ,
		//// 그 값이 서로 일치하면 다시 랜덤으로 돌리고 일치하지 않으면 그 값을 반환함.
		return "사번"; 
	}
	
	public int generateHolyDayCnt(LocalDate hire_dt) {
		// 각자의 포지션 값에 해당하는 연차갯수를 반환한다.
		// hire_dt로 현재 날짜와 차이값을 계산해서
		// 1년차면 15+1
		// 2년차면 15+2
		// 3년차면 15+3
		// 4년차면 15+4
		// ...... 이런식으로
		
		return 0;
	}


	@Override
	public int compareTo(Object object) {
		//Object있는지 점검한다.
		Employee emp = null;
		if(object instanceof Employee) {
			emp = (Employee)object;
		}
		//비교한다 입사년도를 비교한다
		if(this.getHireDt().isBefore(emp.getHireDt())) {
			return 1;
		}else if(this.getHireDt().isAfter(emp.getHireDt())) {
			return -1;
		}else {
			return 0;
		}
	}
	
	
}

class EmployeeNoComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee e1, Employee e2) {
        return e1.getEmpNo().compareTo(e2.getEmpNo());
    }
}

// Employee employee = new Employee();
// employee.getName();
// employee.birthDt = new Date("1992-01-01");
// 
// Employee employee = new Employee("최효진", "920101", "직원");
// Employee employee = new Employee("00001", "최효진", "920101", "직원", "15");
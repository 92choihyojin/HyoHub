package chj.company;

public class HolyCnt implements Comparable{

	private int empHolyProposeId;
	private int year;
	private int totalDay;
	private int usedDay;
	private int remainDay;
	private String empNo;

	// 기본생성자
	public HolyCnt() {

	}

	public HolyCnt(int empHolyProposeId, int year, int totalDay, int usedDay, int remainDay, String empNo) {
		this.empHolyProposeId = empHolyProposeId;
		this.year = year;
		this.totalDay = totalDay;
		this.usedDay = usedDay;
		this.remainDay = remainDay;
		this.empNo = empNo;
	}

	public int getEmpHolyProposeId() {
		return empHolyProposeId;
	}

	public void setEmpHolyProposeId(int empHolyProposeId) {
		this.empHolyProposeId = empHolyProposeId;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getTotalDay() {
		return totalDay;
	}

	public void setTotalDay(int totalDay) {
		this.totalDay = totalDay;
	}

	public int getUsedDay() {
		return usedDay;
	}

	public void setUsedDay(int usedDay) {
		this.usedDay = usedDay;
	}

	public int getRemainDay() {
		return remainDay;
	}

	public void setRemainDay(int remainDay) {
		this.remainDay = remainDay;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	@Override
	public String toString() {
		return "HolyCnt [잔여휴가일ID = " + empHolyProposeId + ", 년도 = " + year + ", 전체휴가일수 = " + totalDay + ", 사용휴가일수 = "
				+ usedDay + ", 잔여휴가일수 = " + remainDay + ", 사원번호 = " + empNo + "]";
	}
	
	@Override
	public int compareTo(Object object) {
		//Object있는지 점검한다.
		HolyCnt cnt = null;
		if(object instanceof HolyCnt) {
			cnt = (HolyCnt)object;
		}
		//비교한다 입사년도를 비교한다
		if(this.getEmpHolyProposeId() > cnt.getEmpHolyProposeId()) {
			return 1;
		}else if(this.getEmpHolyProposeId() < cnt.getEmpHolyProposeId()) {
			return -1;
		}else {
			return 0;
		}
	}

}
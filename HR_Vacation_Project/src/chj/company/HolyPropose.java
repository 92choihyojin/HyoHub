package chj.company;

import java.time.LocalDate;

public class HolyPropose {

	// req_id,req_type,start_dt,end_dt,used_day,eu,stat,emp_no
	private int reqId;
	private int reqType;
	private LocalDate startDt;
	private LocalDate endDt;
	private int usedDay;
	private String eu;
	private int stat;
	private String empNo;

	// 기본생성자
	public HolyPropose() {
	}

	public HolyPropose(int reqId, int reqType, LocalDate startDt, LocalDate endDt, int usedDay, String eu, int stat,
			String empNo) {
		this.reqId = reqId;
		this.reqType = reqType;
		this.startDt = startDt;
		this.endDt = endDt;
		this.usedDay = usedDay;
		this.eu = eu;
		this.stat = stat;
		this.empNo = empNo;
	}

	public int getReqId() {
		return reqId;
	}

	public void setReqId(int reqId) {
		this.reqId = reqId;
	}

	public String getReqTypeStr() {
		return ((1==reqType)?"연차":"병가");
	}
	
	public int getReqType() {
		return reqType;
	}

	public void setReqType(int reqType) {
		this.reqType = reqType;
	}

	public LocalDate getStartDt() {
		return startDt;
	}

	public void setStartDt(LocalDate startDt) {
		this.startDt = startDt;
	}

	public LocalDate getEndDt() {
		return endDt;
	}

	public void setEndDt(LocalDate endDt) {
		this.endDt = endDt;
	}

	public int getUsedDay() {
		return usedDay;
	}

	public void setUsedDay(int usedDay) {
		this.usedDay = usedDay;
	}

	public String getEu() {
		return eu;
	}

	public void setEu(String eu) {
		this.eu = eu;
	}

	public String getStatStr() {
		return ((1==stat)?"승인":(-1==stat)?"반려":"대기");
	}
	
	public int getStat() {
		return stat;
	}

	public void setStat(int stat) {
		this.stat = stat;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	
	
	@Override
	public String toString() {
		return "HolyPropose [신청번호 = " + reqId + ", 신청유형 = " + ((1==reqType)?"연차":"병가") + ", 시작일 = " + startDt + ", 종료일 = " + endDt
				+ ", 사용할 날짜 = " + usedDay + ", 이유 = " + eu + ", 결재 상태 = " + ((1==stat)?"승인":(-1==stat)?"반려":"대기") + ", 사번 = " + empNo + "]";
	}

}

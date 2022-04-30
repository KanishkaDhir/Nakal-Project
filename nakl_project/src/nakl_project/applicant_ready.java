package nakl_project;

public class applicant_ready {

	String SrNo,Name,MobileNo,Nakl,NoOfPages,InitialFee,BalanceFee,TotalFee,AppliedDate,TentativeDeliveryDate,ActualDate;
	public applicant_ready(){}
	public applicant_ready(String SrNo,String Name,String MobileNo,String Nakl,String NoOfPages,String InitialFee,String BalanceFee,String TotalFee,String AppliedDate,String TentativeDeliveryDate,String ActualDate )
	{
		super();
		this.SrNo=SrNo;
		this.Name=Name;
		this.MobileNo=MobileNo;
		this.Nakl=Nakl;
		this.NoOfPages=NoOfPages;
		this.InitialFee=InitialFee;
		this.BalanceFee=BalanceFee;
		this.TotalFee=TotalFee;
		this.AppliedDate=AppliedDate;
		this.TentativeDeliveryDate=TentativeDeliveryDate;
		this.ActualDate=ActualDate;
	}
	public String getSrNo() {
		return SrNo;
	}
	
	public void setSrNo(String srNo) {
		SrNo = srNo;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getMobileNo() {
		return MobileNo;
	}
	public void setMobileNo(String mobileNo) {
		MobileNo=mobileNo;
	}
	
	public String getNakl() {
		return Nakl;
	}
	public void setNakl(String nakl) {
		Nakl = nakl;
	}
	public String getNoOfPages() {
		return NoOfPages;
	}
	public void setNoOfPages(String noOfPages) {
		NoOfPages = noOfPages;
	}
	public String getInitialFee() {
		return InitialFee;
	}
	public void setInitialFee(String initialFee) {
		InitialFee = initialFee;
	}
	public String getBalanceFee() {
		return BalanceFee;
	}
	public void setBalanceFee(String balanceFee) {
		BalanceFee = balanceFee;
	}
	public String getTotalFee() {
		return TotalFee;
	}
	public void setTotalFee(String totalFee) {
		TotalFee = totalFee;
	}
	public String getAppliedDate() {
		return AppliedDate;
	}
	public void setAppliedDate(String appliedDate) {
		AppliedDate = appliedDate;
	}
	public String getTentativeDeliveryDate() {
		return TentativeDeliveryDate;
	}
	public void setTentativeDeliveryDate(String tentativeDeliveryDate) {
		TentativeDeliveryDate = tentativeDeliveryDate;
	}
	public String getActualDate() {
		return ActualDate;
	}
	public void setActualDate(String actualDate) {
		ActualDate = actualDate;
	}
}

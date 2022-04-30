package nakl_project;

public class applicant_pending {

	String SrNo,Name,MobileNo,Nakl,InitialFee,AppliedDate,TentativeDeliveryDate;
	public applicant_pending(){}
	public applicant_pending(String SrNo,String Name,String MobileNo,String Nakl,
			String InitialFee,String AppliedDate,String TentativeDeliveryDate)
	{
		super();
		this.SrNo=SrNo;
		this.Name=Name;
		this.MobileNo=MobileNo;
		this.Nakl=Nakl;
		this.InitialFee=InitialFee;
		this.AppliedDate=AppliedDate;
		this.TentativeDeliveryDate=TentativeDeliveryDate;
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
		MobileNo = mobileNo;
	}
	public String getNakl() {
		return Nakl;
	}
	public void setNakl(String nakl) {
		Nakl = nakl;
	}
	public String getInitialFee() {
		return InitialFee;
	}
	public void setInitialFee(String initialFee) {
		InitialFee = initialFee;
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

}

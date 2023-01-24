package com.solvd.hospitalsystem.models.patient;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.solvd.hospitalsystem.models.Model;

@XmlRootElement(name = "PatientInsurance")
@XmlAccessorType(XmlAccessType.FIELD)
public class PatientInsurance extends Model{

	@XmlElement
    private String policyNumber;
	
	@XmlElement
    private String insuranceProvider;
	
	@XmlElement
    private String coverageDetails;
	
	@XmlElement
    private String providerPhone;
	
	@XmlElement
    private long patientId;
    
    public PatientInsurance() {
    	super();
    }
    
	public PatientInsurance(long id, String policyNumber, String insuranceProvider, String coverageDetails,
			String providerPhone, long patientId) {
		super(id);
		this.policyNumber = policyNumber;
		this.insuranceProvider = insuranceProvider;
		this.coverageDetails = coverageDetails;
		this.providerPhone = providerPhone;
		this.patientId = patientId;
	}
	public String getPolicyNumber() {
		return policyNumber;
	}
	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}
	public String getInsuranceProvider() {
		return insuranceProvider;
	}
	public void setInsuranceProvider(String insuranceProvider) {
		this.insuranceProvider = insuranceProvider;
	}
	public String getCoverageDetails() {
		return coverageDetails;
	}
	public void setCoverageDetails(String coverageDetails) {
		this.coverageDetails = coverageDetails;
	}
	public String getProviderPhone() {
		return providerPhone;
	}
	public void setProviderPhone(String providerPhone) {
		this.providerPhone = providerPhone;
	}
	public long getPatientId() {
		return patientId;
	}
	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}
    
    


}


package com.example.xinmeng.sia;
import com.google.gson.annotations.SerializedName;
/**
 * Created by jrnew on 24/10/2017.
 */

public class DefectsFetcher {
    private String regn;
    private String fleet;
    private String stn;
    private String dateRaised;
    private int ageing;
    private int ata;
    private String defects;
    private String action;
    private String partDetails;
    private String deferralReason;
    private String category;
    private String classCode;
    private Boolean assigned;
    private Boolean inProgress;
    private Boolean resolved;
    private String technicianID;

    public DefectsFetcher(Defects defect)
    {
        this.regn = defect.regn;
        this.fleet = defect.fleet;
        this.stn = defect.stn;
        this.dateRaised = defect.dateRaised;
        this.ata = defect.ata;
        this.ageing = defect.age;
        this.defects = defect.description;
        this.action = defect.action;
        this.partDetails = defect.parts;
        this.action = defect.action;
        this.deferralReason = defect.deferralReason;
        this.category = defect.category;
        this.assigned = defect.inProgress;
        this.inProgress = defect.inProgress;
        this.resolved = defect.resolved;
        this.technicianID = defect.techID;
        if (defect.classCode == Database.Economy)
            this.classCode = "Economy";
        else if (defect.classCode==Database.Premium)
            this.classCode = "Premium";
        else if (defect.classCode == Database.Business)
            this.classCode = "Business";
        else if (defect.classCode == Database.First)
            this.classCode = "First";

    }

    @com.google.gson.annotations.SerializedName("id")
    private String mId;
    public String getId() { return mId; }
    public final void setId(String id) { mId = id; }

    @com.google.gson.annotations.SerializedName("regn")
    public String getRegn() {
        return regn;
    }

    public void setRegn(String regn) {
        this.regn = regn;
    }

    @com.google.gson.annotations.SerializedName("fleet")
    public String getFleet() {
        return fleet;
    }

    public void setFleet(String fleet) {
        this.fleet = fleet;
    }

    @com.google.gson.annotations.SerializedName("stn")
    public String getStn() {
        return stn;
    }

    public void setStn(String stn) {
        this.stn = stn;
    }

    @com.google.gson.annotations.SerializedName("dateRaised")
    public String getDateRaised() {
        return dateRaised;
    }

    public void setDateRaised(String dateRaised) {
        this.dateRaised = dateRaised;
    }

    @com.google.gson.annotations.SerializedName("ageing")
    public int getAgeing() {
        return ageing;
    }

    public void setAgeing(int ageing) {
        this.ageing = ageing;
    }

    @com.google.gson.annotations.SerializedName("ata")
    public int getAta() {
        return ata;
    }

    public void setAta(int ata) {
        this.ata = ata;
    }

    @com.google.gson.annotations.SerializedName("defects")
    public String getDefects() {
        return defects;
    }

    public void setDefects(String defects) {
        this.defects = defects;
    }

    @com.google.gson.annotations.SerializedName("action")
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @com.google.gson.annotations.SerializedName("partDetails")
    public String getPartDetails() {
        return partDetails;
    }

    public void setPartDetails(String partDetails) {
        this.partDetails = partDetails;
    }

    @com.google.gson.annotations.SerializedName("deferralReason")
    public String getDeferralReason() {
        return deferralReason;
    }

    public void setDeferralReason(String deferralReason) {
        this.deferralReason = deferralReason;
    }

    @com.google.gson.annotations.SerializedName("category")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @com.google.gson.annotations.SerializedName("classCode")
    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    @com.google.gson.annotations.SerializedName("assigned")
    public Boolean getAssigned() {
        return assigned;
    }

    public void setAssigned(Boolean assigned) {
        this.assigned = assigned;
    }

    @com.google.gson.annotations.SerializedName("inProgress")
    public Boolean getInProgress() {
        return inProgress;
    }

    public void setInProgress(Boolean inProgress) {
        this.inProgress = inProgress;
    }

    @com.google.gson.annotations.SerializedName("resolved")
    public Boolean getResolved() {
        return resolved;
    }

    public void setResolved(Boolean resolved) {
        this.resolved = resolved;
    }

    @com.google.gson.annotations.SerializedName("technicianID")
    public String getTechnicianID() {
        return technicianID;
    }

    public void setTechnicianID(String technicianID) {
        this.technicianID = technicianID;
    }
}

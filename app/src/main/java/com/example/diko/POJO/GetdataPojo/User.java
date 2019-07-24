
package com.example.diko.POJO.GetdataPojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("USER_TOKEN")
    @Expose
    private String uSERTOKEN;
    @SerializedName("USER_ID")
    @Expose
    private String uSERID;
    @SerializedName("FULL_NAME")
    @Expose
    private String fULLNAME;
    @SerializedName("FIRST_NAME")
    @Expose
    private String fIRSTNAME;
    @SerializedName("LAST_NAME")
    @Expose
    private String lASTNAME;
    @SerializedName("EMAIL")
    @Expose
    private String eMAIL;
    @SerializedName("GROUPS_NAME")
    @Expose
    private String gROUPSNAME;
    @SerializedName("PROFILE_PIC_MINI")
    @Expose
    private String pROFILEPICMINI;
    @SerializedName("APP_ID")
    @Expose
    private String aPPID;
    @SerializedName("APP_OPTION")
    @Expose
    private String aPPOPTION;
    @SerializedName("SITE_ID")
    @Expose
    private String sITEID;

    public String getUSERTOKEN() {
        return uSERTOKEN;
    }

    public void setUSERTOKEN(String uSERTOKEN) {
        this.uSERTOKEN = uSERTOKEN;
    }

    public String getUSERID() {
        return uSERID;
    }

    public void setUSERID(String uSERID) {
        this.uSERID = uSERID;
    }

    public String getFULLNAME() {
        return fULLNAME;
    }

    public void setFULLNAME(String fULLNAME) {
        this.fULLNAME = fULLNAME;
    }

    public String getFIRSTNAME() {
        return fIRSTNAME;
    }

    public void setFIRSTNAME(String fIRSTNAME) {
        this.fIRSTNAME = fIRSTNAME;
    }

    public String getLASTNAME() {
        return lASTNAME;
    }

    public void setLASTNAME(String lASTNAME) {
        this.lASTNAME = lASTNAME;
    }

    public String getEMAIL() {
        return eMAIL;
    }

    public void setEMAIL(String eMAIL) {
        this.eMAIL = eMAIL;
    }

    public String getGROUPSNAME() {
        return gROUPSNAME;
    }

    public void setGROUPSNAME(String gROUPSNAME) {
        this.gROUPSNAME = gROUPSNAME;
    }

    public String getPROFILEPICMINI() {
        return pROFILEPICMINI;
    }

    public void setPROFILEPICMINI(String pROFILEPICMINI) {
        this.pROFILEPICMINI = pROFILEPICMINI;
    }

    public String getAPPID() {
        return aPPID;
    }

    public void setAPPID(String aPPID) {
        this.aPPID = aPPID;
    }

    public String getAPPOPTION() {
        return aPPOPTION;
    }

    public void setAPPOPTION(String aPPOPTION) {
        this.aPPOPTION = aPPOPTION;
    }

    public String getSITEID() {
        return sITEID;
    }

    public void setSITEID(String sITEID) {
        this.sITEID = sITEID;
    }

}

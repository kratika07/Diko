
package com.example.diko.POJO.GetdataPojo;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetData  {

    @SerializedName("ITEM_ID")
    @Expose
    private String iTEMID;
    @SerializedName("ITEM_NAME")
    @Expose
    private String iTEMNAME;
    @SerializedName("PARENT_ID")
    @Expose
    private String pARENTID;
    @SerializedName("STYPE_ID")
    @Expose
    private Integer sTYPEID;
    @SerializedName("STYPE")
    @Expose
    private STYPE sTYPE;
    @SerializedName("TAGS")
    @Expose
    private List<Object> tAGS = null;
    @SerializedName("CREATED_DATE")
    @Expose
    private String cREATEDDATE;
    @SerializedName("MODIFIED_DATE")
    @Expose
    private String mODIFIEDDATE;
    @SerializedName("RESERVED_DATE")
    @Expose
    private String rESERVEDDATE;
    @SerializedName("CREATED_BY_NAME")
    @Expose
    private String cREATEDBYNAME;
    @SerializedName("MODIFIED_BY_NAME")
    @Expose
    private String mODIFIEDBYNAME;
    @SerializedName("RESERVED_BY_NAME")
    @Expose
    private String rESERVEDBYNAME;
    @SerializedName("PATH")
    @Expose
    private String pATH;
    @SerializedName("VNID")
    @Expose
    private String vNID;
    @SerializedName("PERMISSION")
    @Expose
    private PERMISSION pERMISSION;
    @SerializedName("CATEGORIES")
    @Expose
    private List<Object> cATEGORIES = null;
    @SerializedName("ITEM_ACCESS_RIGHTS")
    @Expose
    private List<ITEMACCESSRIGHT> iTEMACCESSRIGHTS = null;
    @SerializedName("PRIVATE_LINK")
    @Expose
    private String pRIVATELINK;

    public String getITEMID() {
        return iTEMID;
    }

    public void setITEMID(String iTEMID) {
        this.iTEMID = iTEMID;
    }

    public String getITEMNAME() {
        return iTEMNAME;
    }

    public void setITEMNAME(String iTEMNAME) {
        this.iTEMNAME = iTEMNAME;
    }

    public String getPARENTID() {
        return pARENTID;
    }

    public void setPARENTID(String pARENTID) {
        this.pARENTID = pARENTID;
    }

    public Integer getSTYPEID() {
        return sTYPEID;
    }

    public void setSTYPEID(Integer sTYPEID) {
        this.sTYPEID = sTYPEID;
    }

    public STYPE getSTYPE() {
        return sTYPE;
    }

    public void setSTYPE(STYPE sTYPE) {
        this.sTYPE = sTYPE;
    }

    public List<Object> getTAGS() {
        return tAGS;
    }

    public void setTAGS(List<Object> tAGS) {
        this.tAGS = tAGS;
    }

    public String getCREATEDDATE() {
        return cREATEDDATE;
    }

    public void setCREATEDDATE(String cREATEDDATE) {
        this.cREATEDDATE = cREATEDDATE;
    }

    public String getMODIFIEDDATE() {
        return mODIFIEDDATE;
    }

    public void setMODIFIEDDATE(String mODIFIEDDATE) {
        this.mODIFIEDDATE = mODIFIEDDATE;
    }

    public String getRESERVEDDATE() {
        return rESERVEDDATE;
    }

    public void setRESERVEDDATE(String rESERVEDDATE) {
        this.rESERVEDDATE = rESERVEDDATE;
    }

    public String getCREATEDBYNAME() {
        return cREATEDBYNAME;
    }

    public void setCREATEDBYNAME(String cREATEDBYNAME) {
        this.cREATEDBYNAME = cREATEDBYNAME;
    }

    public String getMODIFIEDBYNAME() {
        return mODIFIEDBYNAME;
    }

    public void setMODIFIEDBYNAME(String mODIFIEDBYNAME) {
        this.mODIFIEDBYNAME = mODIFIEDBYNAME;
    }

    public String getRESERVEDBYNAME() {
        return rESERVEDBYNAME;
    }

    public void setRESERVEDBYNAME(String rESERVEDBYNAME) {
        this.rESERVEDBYNAME = rESERVEDBYNAME;
    }

    public String getPATH() {
        return pATH;
    }

    public void setPATH(String pATH) {
        this.pATH = pATH;
    }

    public String getVNID() {
        return vNID;
    }

    public void setVNID(String vNID) {
        this.vNID = vNID;
    }

    public PERMISSION getPERMISSION() {
        return pERMISSION;
    }

    public void setPERMISSION(PERMISSION pERMISSION) {
        this.pERMISSION = pERMISSION;
    }

    public List<Object> getCATEGORIES() {
        return cATEGORIES;
    }

    public void setCATEGORIES(List<Object> cATEGORIES) {
        this.cATEGORIES = cATEGORIES;
    }

    public List<ITEMACCESSRIGHT> getITEMACCESSRIGHTS() {
        return iTEMACCESSRIGHTS;
    }

    public void setITEMACCESSRIGHTS(List<ITEMACCESSRIGHT> iTEMACCESSRIGHTS) {
        this.iTEMACCESSRIGHTS = iTEMACCESSRIGHTS;
    }

    public String getPRIVATELINK() {
        return pRIVATELINK;
    }

    public void setPRIVATELINK(String pRIVATELINK) {
        this.pRIVATELINK = pRIVATELINK;
    }

}

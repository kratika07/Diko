
package com.example.diko.POJO.GetdataPojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ITEMACCESSRIGHT {

    @SerializedName("ITEM_ID")
    @Expose
    private String iTEMID;
    @SerializedName("ACCESS_RIGHT_ID")
    @Expose
    private Integer aCCESSRIGHTID;
    @SerializedName("UNIT_ID")
    @Expose
    private String uNITID;

    public String getITEMID() {
        return iTEMID;
    }

    public void setITEMID(String iTEMID) {
        this.iTEMID = iTEMID;
    }

    public Integer getACCESSRIGHTID() {
        return aCCESSRIGHTID;
    }

    public void setACCESSRIGHTID(Integer aCCESSRIGHTID) {
        this.aCCESSRIGHTID = aCCESSRIGHTID;
    }

    public String getUNITID() {
        return uNITID;
    }

    public void setUNITID(String uNITID) {
        this.uNITID = uNITID;
    }

}

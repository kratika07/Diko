
package com.example.diko.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class STYPE {

    @SerializedName("COLOR_CODE")
    @Expose
    private String cOLORCODE;
    @SerializedName("STYPE_ID")
    @Expose
    private Integer sTYPEID;

    public String getCOLORCODE() {
        return cOLORCODE;
    }

    public void setCOLORCODE(String cOLORCODE) {
        this.cOLORCODE = cOLORCODE;
    }

    public Integer getSTYPEID() {
        return sTYPEID;
    }

    public void setSTYPEID(Integer sTYPEID) {
        this.sTYPEID = sTYPEID;
    }

}

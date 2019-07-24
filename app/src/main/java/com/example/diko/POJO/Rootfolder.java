
package com.example.diko.POJO;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rootfolder {

    @SerializedName("COUNT")
    @Expose
    private Integer cOUNT;
    @SerializedName("TOTAL_COUNT")
    @Expose
    private Integer tOTALCOUNT;
    @SerializedName("START")
    @Expose
    private Integer sTART;
    @SerializedName("LIMIT")
    @Expose
    private Integer lIMIT;
    @SerializedName("DATA")
    @Expose
    private List<DATum> dATA = null;

    public Rootfolder(String responsedata) {
    }

    public Integer getCOUNT() {
        return cOUNT;
    }

    public void setCOUNT(Integer cOUNT) {
        this.cOUNT = cOUNT;
    }

    public Integer getTOTALCOUNT() {
        return tOTALCOUNT;
    }

    public void setTOTALCOUNT(Integer tOTALCOUNT) {
        this.tOTALCOUNT = tOTALCOUNT;
    }

    public Integer getSTART() {
        return sTART;
    }

    public void setSTART(Integer sTART) {
        this.sTART = sTART;
    }

    public Integer getLIMIT() {
        return lIMIT;
    }

    public void setLIMIT(Integer lIMIT) {
        this.lIMIT = lIMIT;
    }

    public List<DATum> getDATA() {
        return dATA;
    }

    public void setDATA(List<DATum> dATA) {
        this.dATA = dATA;
    }

}

package com.example.diko.Class;

public class User {


    private String USER_TOKEN;
    private String USER_ID;
    private String FULL_NAME;
    private String FIRST_NAME;
    private String LAST_NAME;
    private String EMAIL;
    private String GROUPS_NAME;
    private String PROFILE_PIC_MINI;
    private String APP_ID;
    private String APP_OPTION;
    private String SITE_ID;


    public User(String USER_TOKEN, String USER_ID, String FULL_NAME, String FIRST_NAME, String LAST_NAME, String EMAIL, String GROUPS_NAME, String PROFILE_PIC_MINI, String APP_ID, String APP_OPTION, String SITE_ID) {
        this.USER_TOKEN = USER_TOKEN;
        this.USER_ID = USER_ID;
        this.FULL_NAME = FULL_NAME;
        this.FIRST_NAME = FIRST_NAME;
        this.LAST_NAME = LAST_NAME;
        this.EMAIL = EMAIL;
        this.GROUPS_NAME = GROUPS_NAME;
        this.PROFILE_PIC_MINI = PROFILE_PIC_MINI;
        this.APP_ID = APP_ID;
        this.APP_OPTION = APP_OPTION;
        this.SITE_ID = SITE_ID;
    }


    public User() {
    }


    public String getUSER_TOKEN() {
        return USER_TOKEN;
    }

    public void setUSER_TOKEN(String USER_TOKEN) {
        this.USER_TOKEN = USER_TOKEN;
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }

    public String getFULL_NAME() {
        return FULL_NAME;
    }

    public void setFULL_NAME(String FULL_NAME) {
        this.FULL_NAME = FULL_NAME;
    }

    public String getFIRST_NAME() {
        return FIRST_NAME;
    }

    public void setFIRST_NAME(String FIRST_NAME) {
        this.FIRST_NAME = FIRST_NAME;
    }

    public String getLAST_NAME() {
        return LAST_NAME;
    }

    public void setLAST_NAME(String LAST_NAME) {
        this.LAST_NAME = LAST_NAME;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getGROUPS_NAME() {
        return GROUPS_NAME;
    }

    public void setGROUPS_NAME(String GROUPS_NAME) {
        this.GROUPS_NAME = GROUPS_NAME;
    }

    public String getPROFILE_PIC_MINI() {
        return PROFILE_PIC_MINI;
    }

    public void setPROFILE_PIC_MINI(String PROFILE_PIC_MINI) {
        this.PROFILE_PIC_MINI = PROFILE_PIC_MINI;
    }

    public String getAPP_ID() {
        return APP_ID;
    }

    public void setAPP_ID(String APP_ID) {
        this.APP_ID = APP_ID;
    }

    public String getAPP_OPTION() {
        return APP_OPTION;
    }

    public void setAPP_OPTION(String APP_OPTION) {
        this.APP_OPTION = APP_OPTION;
    }

    public String getSITE_ID() {
        return SITE_ID;
    }

    public void setSITE_ID(String SITE_ID) {
        this.SITE_ID = SITE_ID;
    }
}

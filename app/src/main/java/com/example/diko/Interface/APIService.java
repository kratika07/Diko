package com.example.diko.Interface;

import com.example.diko.Class.User;
import com.example.diko.POJO.GetdataPojo.GetData;
import com.example.diko.POJO.Rootfolder;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIService {

    @POST("DIKO/Login?")
    Call<User> UserLogin(
            @Query("APP_ID") String app_id,
            @Query("USER_ID") String user_id,
            @Query("PASSWORD") String password,
            @Query("LOCALE") String locale
          );

    @POST("Library/GetRootFolderID?")
    Call<String> getStringResponse(@Query("USER_TOKEN") String usertoken);

    @POST("Diko/ForgetPasswordCode?")
    Call<String> getForgotResponse(@Query("APP_ID") String appid,@Query("USER_ID") String user_id);


    @POST("Diko/ChangePassword?")
    Call<String> getChangePassword(@Query("APP_ID") String appid,@Query("USER_TOKEN") String usertoken,
                                   @Query("NEW_PASSWORD")String newpassowrd,@Query("OLD_PASSWORD")String oldpassword
                                   );

    @POST("Diko/ResetPassword?")
    Call<String> getResetPassword(@Query("APP_ID") String appid,@Query("VALIDATION_CODE") String validation_code,
                                   @Query("NEW_PASSWORD")String newpassowrd
    );


    @POST("Library/List?")
    Call<Rootfolder>getRootFolderList(@Query("USER_TOKEN") String user_token,
                                      @Query("PARENT_ID") String parent_id,
                                      @Query("START") int start,
                                      @Query("LIMIT") int limit);

    @POST("Library/Get?")
    Call<GetData>getRootflderData(@Query("USER_TOKEN") String user_token,
                                  @Query("ITEM_ID") String item_id)
                                    ;




}

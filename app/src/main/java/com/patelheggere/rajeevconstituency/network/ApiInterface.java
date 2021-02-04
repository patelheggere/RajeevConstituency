package com.patelheggere.rajeevconstituency.network;


import com.patelheggere.rajeevconstituency.model.APIResponseModel;
import com.patelheggere.rajeevconstituency.model.BeneficiaryModel;
import com.patelheggere.rajeevconstituency.model.UploadDataModel;
import com.patelheggere.rajeevconstituency.model.VillageModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    /*
     * Retrofit get annotation with our URL
     * And our method that will return us details of student.
     */

   /* //mine AIzaSyD_Zbbwx7aYQaAWnl5O2Dv4-6r2G3dhEUI
    //ind AIzaSyDexSpfSK4WI1XnxQCuusnateV57knMJww
    @GET("api/place/nearbysearch/json?sensor=true&rankby=distance&key=AIzaSyDexSpfSK4WI1XnxQCuusnateV57knMJww")
    Call<Place> getNearbyPlaces(@Query("types") String type, @Query("location") String location);
    //Call<Place> getNearbyPlaces(@Query("location") String location);

    @GET("api/place/nearbysearch/json?sensor=true&rankby=distance&key=AIzaSyDexSpfSK4WI1XnxQCuusnateV57knMJww")
    Call<Place> getNearbyPlacesWithToken(@Query("location") String location, @Query("pagetoken") String token);*/

    // with type
    //Call<Place> getNearbyPlaces(@Query("types") String type, @Query("location") String location);

   // Call<Place> getNearbyPlaces(@Query("types") String type, @Query("location") String location, @Query("radius") int radius);

    @GET("beneficiary/getByMobile.php")
    Call<BeneficiaryModel> getByMobile(@Query("mobile") String mobile);

    @GET("beneficiary/getByAck.php")
    Call<BeneficiaryModel> getByAck(@Query("sl_no") String ack);

    @GET("beneficiary/getByEPIC.php")
    Call<BeneficiaryModel> getByEPIC(@Query("epic") String epic);

    @GET("beneficiary/GetAllVillage.php")
    Call<List<VillageModel>> getVillage(@Query("id") String id, @Query("type") String type);

    @GET("beneficiary/GetAllBeneficiary.php")
    Call<List<BeneficiaryModel>> getByDataByVillage(@Query("village") String id, @Query("type") String type);
    @GET("beneficiary/GetBoothLeaders.php")
    Call<List<BeneficiaryModel>> getboothLeaders(@Query("booth_no") int id);

    @POST("beneficiary/InsertBeneficiary.php")
    Call<APIResponseModel> Uplaod(@Body UploadDataModel uploadDataModel);

}

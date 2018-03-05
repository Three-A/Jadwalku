package com.example.yudha.jadwalku.api

import com.example.yudha.jadwalku.model.Base
import com.example.yudha.jadwalku.model.Jadwal
import com.example.yudha.jadwalku.model.User
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by yudha on 3/4/2018.
 */
interface ApiService {

    // USER SERVICES

    @FormUrlEncoded
    @POST("api/users/signin")
    fun login(@Field("email") email : String,
              @Field("password") pass : String): Call<User>

    @FormUrlEncoded
    @POST("api/users/signup")
    fun signup(@Field("email") email: String,
               @Field("password") password: String,
               @Field("name") name: String,
               @Field("phoneNumber") phoneNumber: String): Call<Base>
    // JADWAL SERVICES

    @GET("api/jadwals")
    fun getAllJadwal() : Call<ArrayList<Jadwal>> //kalau kurung siku(array) pakai arraylist

    @FormUrlEncoded
    @POST("api/jadwals")
    fun addJadwal(@Field("kelas") kelas : String,
                  @Field("hari") hari : String,
                  @Field("matkul") matkul : String,
                  @Field("ruang") ruang : String): Call<Base> //kalau kurawal(object) callbacknya tanpa array

    @FormUrlEncoded
    @PUT("api/jadwals/{id}")
    fun updateJadwal(@Path("id") id : String,
                  @Field("kelas") kelas : String,
                  @Field("hari") hari : String,
                  @Field("matkul") matkul : String,
                  @Field("ruang") ruang : String): Call<Base>

    @DELETE("api/jadwals/{id}")
    fun deleteJadwal(@Path("id") id : String) : Call<Base> //kalau kurung siku(array) pakai arraylist

}
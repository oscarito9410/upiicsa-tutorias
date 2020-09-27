package com.aboolean.movies.data.remote

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface TutoriasEndpoints {
    @GET("popular")
    fun getPopularMovies(
           // @Query("page") page: Int,
           // @Query("api_key") apiKey: String = API_KEY,
          //  @Query("language") language: String = DEFAULT_LANGUAGE,
           // @Query("region") region: String = DEFAULT_REGION
    )//: Single<PageMovie>


}
package com.danilloteles.aularxjava.api

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface JsonPlaceApi {

   @GET("posts")
   fun recuperarPostagens() : Observable<List<Postagem>>
}
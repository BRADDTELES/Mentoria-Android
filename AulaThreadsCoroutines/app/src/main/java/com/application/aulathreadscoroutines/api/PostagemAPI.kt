package com.application.aulathreadscoroutines.api

import com.application.aulathreadscoroutines.model.Comentario
import com.application.aulathreadscoroutines.model.Postagem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PostagemAPI {

    // https://jsonplaceholder.typicode.com/posts
    @GET("posts")
    suspend fun recuperarPostagens() : Response < List< Postagem > >

    @GET("posts/{id}")
    suspend fun recuperarPostagemUnica( @Path("id") id: Int ) : Response < Postagem >

    @GET("posts/{id}/comments")//Path
    suspend fun recuperarComentariosParaPostagem( @Path("id") id: Int ) : Response < List< Comentario > >

    @GET("comments")//Query comments?postId=1&...
    suspend fun recuperarComentariosParaPostagemQuery( @Query("postId") id: Int ) : Response < List< Comentario > >
}
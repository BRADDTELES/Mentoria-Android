package com.application.aulathreadscoroutines.api

import com.application.aulathreadscoroutines.model.Comentario
import com.application.aulathreadscoroutines.model.Postagem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface PostagemAPI {

    // https://jsonplaceholder.typicode.com/posts
    @GET("posts")// Requisição GET
    suspend fun recuperarPostagens() : Response < List< Postagem > >

    /*@GET("posts/{id}")
    suspend fun recuperarPostagemUnica( @Path("id") id: Int ) : Response < Postagem >

    @GET("posts/{id}/comments")//Path
    suspend fun recuperarComentariosParaPostagem( @Path("id") id: Int ) : Response < List< Comentario > >

    @GET("comments")//Query comments?postId=1&...
    suspend fun recuperarComentariosParaPostagemQuery( @Query("postId") id: Int ) : Response < List< Comentario > >*/

    @POST("posts")// Requisição POST
    suspend fun salvarPostagens( @Body postagem: Postagem ) : Response < Postagem >

    @FormUrlEncoded
    @POST("posts")// Requisição POST
    suspend fun salvarPostagemFormulario(
        @Field("userId") userId: Int,
        @Field("id") id: Int,
        @Field("title") titulo: String,
        @Field("body") body: String  // Field é para form-data e QueryParam é para query string
    ) : Response < Postagem >

    @PUT("posts/{id}")//Requisição PUT - Atualização completa
    suspend fun atualizarPostagemPut(
        @Path("id") id: Int,
        @Body postagem: Postagem
    ) : Response < Postagem >

    @PATCH("posts/{id}")//Requisição PATCH - Atualização parcial
    suspend fun atualizarPostagemPatch(
        @Path("id") id: Int,
        @Body postagem: Postagem
    ) : Response < Postagem >

    @DELETE("posts/{id}")//Requisição DELETE
    suspend fun removerPostagem( @Path("id") id: Int ) : Response < Unit >
}
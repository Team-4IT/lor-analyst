package foit.product.loranalyst.api

import foit.product.loranalyst.utils.TOKEN
import foit.product.loranalyst.vo.card.CardResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("set1-en_us.json")
    suspend fun getCardGallery(
        @Query("token") token : String = TOKEN
    ): Response<CardResult>
}
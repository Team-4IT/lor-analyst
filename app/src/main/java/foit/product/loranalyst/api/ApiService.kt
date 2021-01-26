package foit.product.loranalyst.api

import foit.product.loranalyst.vo.card.Card
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("set1-en_us.json")
    suspend fun getCardGallery(): Response<List<Card>>
}
package foit.product.loranalyst.api

import foit.product.loranalyst.vo.card.Card
import retrofit2.Response

interface ApiHelper {
    suspend fun getCardGallery(): Response<List<Card>>
}
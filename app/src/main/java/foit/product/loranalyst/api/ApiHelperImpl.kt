package foit.product.loranalyst.api

import foit.product.loranalyst.vo.card.Card
import retrofit2.Response

class ApiHelperImpl(private val apiService: ApiService): ApiHelper {
    override suspend fun getCardGallery(): Response<List<Card>> = apiService.getCardGallery()

}
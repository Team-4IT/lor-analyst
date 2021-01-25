package foit.product.loranalyst.ui.gallery

import foit.product.loranalyst.api.ApiHelper

class CardGalleryRepository (private val apiHelper: ApiHelper) {
    suspend fun getCardGallery() = apiHelper.getCardGallery()
}
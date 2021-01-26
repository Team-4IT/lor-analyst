package foit.product.loranalyst.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import foit.product.loranalyst.utils.NetworkHelper
import foit.product.loranalyst.vo.Resource
import foit.product.loranalyst.vo.card.Card
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CardGalleryViewModel(
    private val cardGalleryRepository: CardGalleryRepository,
    private val networkHelper: NetworkHelper
): ViewModel() {

    private val _cardGallery = MutableLiveData<Resource<List<Card>>>()
    val cardGallery: LiveData<Resource<List<Card>>>
        get() = _cardGallery

    init {
        fetchCardGallery()
    }

    private fun fetchCardGallery() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _cardGallery.postValue(Resource.loading(null))
                if (networkHelper.isNetworkConnected()) {
                    cardGalleryRepository.getCardGallery().let {
                        if (it.isSuccessful) {
                            _cardGallery.postValue(Resource.success(it.body()))
                        } else _cardGallery.postValue(
                            Resource.error(
                                it.errorBody().toString(),
                                null
                            )
                        )
                    }
                } else _cardGallery.postValue(Resource.error("No internet connection", null))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }
}
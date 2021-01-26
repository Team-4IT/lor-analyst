package foit.product.loranalyst.di

import foit.product.loranalyst.ui.gallery.CardGalleryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel{
        CardGalleryViewModel(get(), get())
    }
}
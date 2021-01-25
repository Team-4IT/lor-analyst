package foit.product.loranalyst.di

import foit.product.loranalyst.ui.gallery.CardGalleryRepository
import org.koin.dsl.module

val repoModule = module {
    single { CardGalleryRepository(get()) }
}
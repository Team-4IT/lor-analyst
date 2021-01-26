package foit.product.loranalyst.adapter

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("app:imageUrl")
fun bindImage(imgView: AppCompatImageView, url: String) {
    if (url.isNotBlank()) {
        Glide.with(imgView.context)
            .load(url)
            .into(imgView)
    } else  {
        Glide.with(imgView.context)
            .load("https://dd.b.pvp.net/2_0_0/set1/en_us/img/cards/01IO012.png")
            .into(imgView)
    }

//    Glide.with(imgView.context)
//        .load("https://dd.b.pvp.net/2_0_0/set1/en_us/img/cards/01IO012.png")
//        .into(imgView)
}

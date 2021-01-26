package foit.product.loranalyst.ui.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import foit.product.loranalyst.databinding.ItemCardGalleryBinding
import foit.product.loranalyst.vo.card.Card

class CardGalleryAdapter (
        val cardClickCallback: (Card) -> Unit
): ListAdapter<Card, CardGalleryAdapter.CardGalleryViewHolder>(DiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardGalleryViewHolder {
        val itemBiding = ItemCardGalleryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardGalleryViewHolder(itemBiding)
    }

    override fun onBindViewHolder(holder: CardGalleryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CardGalleryViewHolder(private val itemBiding: ItemCardGalleryBinding):
            RecyclerView.ViewHolder(itemBiding.root) {
        fun bind(card: Card) {
            itemBiding.card = card
            itemBiding.root.setOnClickListener {
                card.let { cardClickCallback.invoke(it) }
            }
            itemBiding.cardImage.let {
                Picasso.with(itemView.context)
                    .load(card.assets[0].gameAbsolutePath)
                    .into(it)
            }

            itemBiding.cardName.text = card.name
        }
    }

    class DiffCallBack: DiffUtil.ItemCallback<Card>() {
        override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean {
            return oldItem.cardCode == newItem.cardCode
        }

        override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean {
            return oldItem == newItem
        }
    }
}
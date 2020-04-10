package com.dicoding.nicolas.home

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.nicolas.UI.UIItemLiga
import com.dicoding.nicolas.model.ItemLiga
import com.squareup.picasso.Picasso
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

class HomeAdapter(
    private val items: List<ItemLiga>,
    private val listener: (ItemLiga) -> Unit
) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            UIItemLiga().createView(
                AnkoContext.create(parent.context)
            )
        )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val name: TextView = view.find(UIItemLiga.nameItem)
        private val image: ImageView = view.find(UIItemLiga.imgItem)

        fun bindItem(items: ItemLiga, listener: (ItemLiga) -> Unit) {
            name.text = items.nama
            items.image.let { Picasso.get().load(it!!).fit().into(image) }
            itemView.setOnClickListener {
                listener(items)
            }
        }
    }

}
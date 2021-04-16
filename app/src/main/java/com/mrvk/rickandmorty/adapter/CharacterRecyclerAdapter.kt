package com.mrvk.rickandmorty.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mrvk.rickandmorty.R
import com.mrvk.rickandmorty.model.Character.Result
import com.mrvk.rickandmorty.util.imageDownload
import com.mrvk.rickandmorty.util.placeHolderCreate
import kotlinx.android.synthetic.main.item_character_list.view.*

class CharacterRecyclerAdapter(var characterList: ArrayList<Result>, val listener: CharacterAdapterListener) :
    RecyclerView.Adapter<CharacterRecyclerAdapter.CharacterViewHolder>() {
    class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_character_list, parent, false)
        return CharacterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        clickItem(holder.itemView, characterList, position)
        holder.itemView.tv_item_character_list_name.text = characterList.get(position).name
        characterList.get(position).image?.let {
            holder.itemView.img_item_character_list.imageDownload(
                it,
                placeHolderCreate(holder.itemView.context))
        }
    }

    private fun clickItem(itemView: View, chatacterList: List<Result>, position: Int) {
        itemView.setOnClickListener {
            listener.onClicked(chatacterList.get(position))
        }
    }

    fun characterListRefresh(newCharacterList : List<Result>) {
        characterList.addAll(newCharacterList)
        notifyDataSetChanged()
    }

    interface CharacterAdapterListener {
        fun onClicked(model:Result)
    }

}
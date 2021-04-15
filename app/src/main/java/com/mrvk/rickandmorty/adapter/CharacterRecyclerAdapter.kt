package com.mrvk.rickandmorty.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.mrvk.rickandmorty.R
import com.mrvk.rickandmorty.model.Character.Result
import com.mrvk.rickandmorty.util.imageDownload
import com.mrvk.rickandmorty.util.placeHolderCreate
import com.mrvk.rickandmorty.view.CharacterListFragmentDirections
import kotlinx.android.synthetic.main.item_character_list.view.*

class CharacterRecyclerAdapter(var chatacterList: ArrayList<Result>) :
    RecyclerView.Adapter<CharacterRecyclerAdapter.CharacterViewHolder>() {
    class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_character_list, parent, false)
        return CharacterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return chatacterList.size
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        chatacterList.get(position).id?.let { clickItem(holder.itemView, it) }
        holder.itemView.tv_item_character_list_name.text = chatacterList.get(position).name
        chatacterList.get(position).image?.let {
            holder.itemView.img_item_character_list.imageDownload(
                it,
                placeHolderCreate(holder.itemView.context))
        }
    }

    private fun clickItem(itemView: View, id: Int) {
        itemView.setOnClickListener {
            val action = CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailFragment(id)
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun characterListRefresh(newCharacterList : List<Result>) {
        chatacterList.addAll(newCharacterList)
        notifyDataSetChanged()
    }


}
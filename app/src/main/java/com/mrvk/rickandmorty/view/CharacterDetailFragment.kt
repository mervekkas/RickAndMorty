package com.mrvk.rickandmorty.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.mrvk.rickandmorty.R
import com.mrvk.rickandmorty.model.Character.Result
import com.mrvk.rickandmorty.util.imageDownload
import com.mrvk.rickandmorty.util.placeHolderCreate
import kotlinx.android.synthetic.main.fragment_character_detail.*
import kotlinx.android.synthetic.main.fragment_character_detail.view.*
import kotlinx.android.synthetic.main.tool_bar_layout.*

class CharacterDetailFragment : Fragment() {
    val characterLiveData = MutableLiveData<Result>()
    private lateinit var characterName:String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_character_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeLiveData()
    }

    private fun setToolBar() {
        img_left.visibility = View.VISIBLE
        tool_bar_title.text = characterName
        backClick()
    }

    private fun observeLiveData() {
        characterLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                characterName = it.name.toString()
                tv_detail_character_name.text = it.name
                tv_detail_character_status.text = it.status
                tv_detail_character_species.text = ", " + it.species
                tv_detail_character_gender.text = it.gender
                context?.let { it1 -> placeHolderCreate(it1) }?.let { it2 ->
                    img_character_detail.imageDownload(it.image.toString(),
                        it2
                    )
                }
                setToolBar()
            }
        })
    }

    private fun backClick() {
        img_left.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }
}
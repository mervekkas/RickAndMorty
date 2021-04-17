package com.mrvk.rickandmorty.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.mrvk.rickandmorty.R
import com.mrvk.rickandmorty.model.Character.Result
import com.mrvk.rickandmorty.model.Episodes.ResultEpisodes
import com.mrvk.rickandmorty.util.imageDownload
import com.mrvk.rickandmorty.util.placeHolderCreate
import kotlinx.android.synthetic.main.fragment_character_detail.*
import kotlinx.android.synthetic.main.tool_bar_layout.*

class CharacterDetailFragment : Fragment() {
    val characterLiveData = MutableLiveData<Result>()
    private lateinit var characterName: String
    var episodesList = mutableListOf<ResultEpisodes>()
    private var nameEpisodesList = mutableListOf<String?>()
    private var openClose: Boolean = false

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
        setEpisode()
    }

    private fun setEpisode() {
        episodesList.forEach {
            nameEpisodesList.add(it.name + " (" + it.episode + ")")
        }
        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, nameEpisodesList)
        lv_episodes.adapter = adapter
        setClickEpisodes()
    }

    private fun setClickEpisodes() {

        rv_episodes.setOnClickListener {
            if (openClose){
                lv_episodes.visibility = View.GONE
                openClose = false
                img_detail_episodes.setImageResource(R.drawable.ic_down_34)
            } else{
                lv_episodes.visibility = View.VISIBLE
                openClose = true
                img_detail_episodes.setImageResource(R.drawable.ic__up_34)
            }

        }
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
                    img_character_detail.imageDownload(
                        it.image.toString(),
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
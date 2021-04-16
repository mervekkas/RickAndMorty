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

class CharacterDetailFragment : Fragment() {
    val characterLiveData = MutableLiveData<Result>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_character_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //viewModel = ViewModelProviders.of(this).get(CharacterDetailViewModel::class.java)

        observeLiveData()
    }

    private fun observeLiveData() {
        characterLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                tv_detail_character_name.text = it.name
                tv_detail_character_status.text = it.status
                tv_detail_character_species.text = ", " + it.species
                tv_detail_character_gender.text = it.gender
                context?.let { it1 -> placeHolderCreate(it1) }?.let { it2 ->
                    img_character_detail.imageDownload(it.image.toString(),
                        it2
                    )
                }
                //img_character_detail.imageDownload(it.image, placeHolderCreate(it.this))
            }
        })
    }

    private fun backClick() {
       // img_tool_bar_back.setOnClickListener {
            requireActivity().onBackPressed()
      //  }
    }
}
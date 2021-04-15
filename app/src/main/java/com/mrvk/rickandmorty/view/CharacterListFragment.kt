package com.mrvk.rickandmorty.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mrvk.rickandmorty.R
import com.mrvk.rickandmorty.adapter.CharacterRecyclerAdapter
import com.mrvk.rickandmorty.viewmodel.CharacterListViewModel
import kotlinx.android.synthetic.main.fragment_character_list.*

class CharacterListFragment : Fragment() {

    private lateinit var viewModel : CharacterListViewModel
    private val characterAdapter = CharacterRecyclerAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_character_list, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(CharacterListViewModel::class.java)
        viewModel.refreshList()

        rv_character_list.layoutManager = LinearLayoutManager(context)
        rv_character_list.adapter = characterAdapter

        setListener()
        observeLiveData()
    }

    fun observeLiveData() {
        viewModel.loading.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it)
                    loadingVisible()
            }
        })
        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it)
                    errorVisible()
                else
                    listVisible()
            }
        })
        viewModel.characterList.observe(viewLifecycleOwner, Observer {
            it?.let {
                rv_character_list.visibility = View.VISIBLE
                characterAdapter.characterListRefresh(it)
            }
        })
    }

    private fun errorVisible() {
        rv_character_list.visibility = View.GONE
        tv_character_list_error_message.visibility = View.VISIBLE
        progress_character_list.visibility = View.GONE
    }

    private fun loadingVisible() {
        rv_character_list.visibility = View.GONE
        progress_character_list.visibility = View.VISIBLE
    }

    private fun listVisible() {
        rv_character_list.visibility = View.VISIBLE
        progress_character_list.visibility = View.GONE
        tv_character_list_error_message.visibility = View.GONE
    }

    fun setListener() {
        rv_character_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if(isLastItemDisplaying(recyclerView)){
                    viewModel.getMoreData()
                }
            }
        })
    }
    private fun isLastItemDisplaying(recyclerView: RecyclerView):Boolean {
        if(recyclerView.getAdapter()?.getItemCount() != 0){
            var lastVisibleItemPosition: Int = (recyclerView?.getLayoutManager() as LinearLayoutManager).findLastCompletelyVisibleItemPosition()

            if(lastVisibleItemPosition != RecyclerView.NO_POSITION && lastVisibleItemPosition == recyclerView.getAdapter()?.getItemCount()
                    ?.minus(1) ?: -1)
                return true
        }

        return false
    }
}
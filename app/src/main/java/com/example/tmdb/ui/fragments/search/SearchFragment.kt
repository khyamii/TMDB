package com.example.tmdb.ui.fragments.search

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager

import com.example.tmdb.ui.fragments.search.di.SearchModule
import com.example.tmdb.R
import com.example.tmdb.base.BaseFragment
import com.example.tmdb.databinding.FragmentSearchBinding
import com.example.tmdb.di.provideCoreComponent
import com.example.tmdb.extensions.observe
import com.example.tmdb.networking.NetworkState
import com.example.tmdb.ui.adapter.MoviesAdapter
import com.example.tmdb.ui.fragments.search.di.DaggerSearchComponent

/**
 * View listing the all marvel characters with option to display the detail view.
 *
 * @see BaseFragment
 */
class SearchFragment :
    BaseFragment<FragmentSearchBinding, SearchViewModel>(
        layoutId = R.layout.fragment_search
    ),View.OnClickListener {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.rvSearchResult.layoutManager = GridLayoutManager(requireContext(), 2)

        observe(viewModel.networkState, ::onStateChange)

        viewBinding.back.setOnClickListener(this)


    }

    /**
     * Initialize dagger injection dependency graph.
     */
    override fun onInitDependencyInjection() {

        DaggerSearchComponent
            .builder()
            .coreComponent(provideCoreComponent(requireCompatActivity().application))
            .searchModule(SearchModule(this))
            .build()
            .inject(this)

    }

    /**
     * Initialize view data binding variables.
     */
    override fun onInitDataBinding() {
        viewBinding.viewModel = viewModel
    }


    //
    private fun onStateChange(state: NetworkState) {

        when (state) {
            is NetworkState.Loading -> {
            }
            is NetworkState.Success -> {
                viewBinding.rvSearchResult.adapter = viewModel.movies.value?.let {
                    MoviesAdapter(requireContext(), it)
                }

            }
            is NetworkState.Error -> {
                showSnackBar(state.errorMessage)
            }

        }
    }

    override fun onClick(v: View?) {

        when (v!!.id) {

            R.id.back -> this.findNavController().popBackStack()
        }


    }
}

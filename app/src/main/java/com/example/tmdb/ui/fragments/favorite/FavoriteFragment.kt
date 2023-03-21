package com.example.tmdb.ui.fragments.favorite

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmdb.R
import com.example.tmdb.base.BaseFragment
import com.example.tmdb.databinding.FragmentFavoriteBinding
import com.example.tmdb.di.provideCoreComponent
import com.example.tmdb.extensions.observe
import com.example.tmdb.networking.NetworkState
import com.example.tmdb.ui.adapter.FavoriteAdapter
import com.example.tmdb.ui.adapter.MoviesAdapter
import com.example.tmdb.ui.fragments.favorite.di.DaggerFavoritesComponent
import com.example.tmdb.ui.fragments.favorite.di.FavoritesModule


class FavoriteFragment : BaseFragment<FragmentFavoriteBinding, FavoritesViewModel>(
    layoutId = R.layout.fragment_favorite
) ,View.OnClickListener{

    private var navController: NavController? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.rvFavoriteProducts.layoutManager = LinearLayoutManager(requireContext())
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
        observe(viewModel.networkState, ::onStateChange)

        viewBinding.back.setOnClickListener (this)


    }

    override fun onInitDependencyInjection() {

        DaggerFavoritesComponent
            .builder()
            .coreComponent(provideCoreComponent(requireCompatActivity().application))
            .favoritesModule(FavoritesModule(this))
            .build()
            .inject(this)

    }

    /**
     * Initialize view data binding variables.
     */
    override fun onInitDataBinding() {
        viewBinding.viewModel = viewModel
    }


    private fun onStateChange(state: NetworkState) {

        when (state) {
            is NetworkState.Loading -> {
            }
            is NetworkState.Success -> {

                viewBinding.rvFavoriteProducts.adapter = viewModel.movies.value?.let {
                    FavoriteAdapter(requireContext(), it)
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
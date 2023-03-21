package com.example.tmdb.ui.fragments.main.movies

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tmdb.R
import com.example.tmdb.base.BaseFragment
import com.example.tmdb.databinding.FragmentMoviesBinding
import com.example.tmdb.di.provideCoreComponent
import com.example.tmdb.extensions.observe
import com.example.tmdb.networking.NetworkState
import com.example.tmdb.ui.adapter.MoviesAdapter
import com.example.tmdb.ui.fragments.main.movies.di.DaggerMoviesComponent
import com.example.tmdb.ui.fragments.main.movies.di.MoviesModule


class MoviesFragment(private val path:String) : BaseFragment<FragmentMoviesBinding, MoviesViewModel>(
    layoutId = R.layout.fragment_movies
) {

    private var navController: NavController? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.rvMovies.layoutManager = GridLayoutManager(requireContext(), 2)
        observe(viewModel.networkState, ::onStateChange)
        viewModel.fetchMovies(path = path)
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)



    }


    override fun onInitDependencyInjection() {


        DaggerMoviesComponent.builder ()
            .coreComponent(provideCoreComponent(requireCompatActivity().application))
            .moviesModule(MoviesModule(this))
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

                viewBinding.rvMovies.adapter = viewModel.movies.value?.let {
                    MoviesAdapter(requireContext(), it)
                }

            }
            is NetworkState.Error -> {

                showSnackBar(state.errorMessage)
            }

        }
    }


}


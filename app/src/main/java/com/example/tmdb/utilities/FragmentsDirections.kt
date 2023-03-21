package com.example.tmdb.utilities


import android.os.Bundle
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.tmdb.R

object FragmentsDirections {

    fun actionMainFragmentToSearchFragment(): NavDirections {
        return ActionOnlyNavDirections(R.id.action_mainFragment_to_searchFragment)
    }

    fun actionMainFragmentToFavoriteFragment(): NavDirections {
        return ActionOnlyNavDirections(R.id.action_mainFragment_to_favoriteFragment)
    }

}

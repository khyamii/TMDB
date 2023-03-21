package com.example.tmdb.ui.fragments.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.viewpager2.widget.ViewPager2
import com.example.tmdb.R
import com.example.tmdb.databinding.FragmentMainBinding
import com.example.tmdb.ui.adapter.TabFragmentsAdapter
import com.example.tmdb.ui.fragments.main.movies.MoviesFragment
import com.example.tmdb.utilities.FragmentsDirections
import com.example.tmdb.utilities.GlobalVariables
import com.google.android.material.tabs.TabLayout


class MainFragment : Fragment(),View.OnClickListener {

    private lateinit var binding : FragmentMainBinding
    private lateinit var tabPageList: List<Fragment>
    private var adapter: TabFragmentsAdapter? = null
    private var navController: NavController? = null


    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_main,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
        binding.search.setOnClickListener(this)
        binding.wishlist.setOnClickListener(this)

        fillTabsLayout()



    }

    private fun fillTabsLayout(){

        tabPageList = listOf(

            MoviesFragment(GlobalVariables.POPULAR_CAT_PATH),
            MoviesFragment(GlobalVariables.NOW_PLAYING_CAT_PATH),
            MoviesFragment(GlobalVariables.UPCOMING_CAT_PATH),
            MoviesFragment(GlobalVariables.TOP_RATED_CAT_PATH),


            )

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(getString(R.string.popular)))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(getString(R.string.now_playing)))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(getString(R.string.upcoming)))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(getString(R.string.top_rated)))


        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        adapter = TabFragmentsAdapter(fragmentManager, lifecycle,tabPageList)

        binding.pager.adapter = adapter

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.pager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        binding.pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position))
            }
        })

    }

    override fun onClick(v: View?) {

        when (v!!.id) {


            R.id.search -> navController!!.navigate(FragmentsDirections.actionMainFragmentToSearchFragment())
            R.id.wishlist -> navController!!.navigate(FragmentsDirections.actionMainFragmentToFavoriteFragment())


        }

    }
}
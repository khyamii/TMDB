package com.example.tmdb.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabFragmentsAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val storeCategoryList: List<Fragment>?) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun createFragment(position: Int):Fragment = storeCategoryList!![position]

    override fun getItemCount(): Int {
        return storeCategoryList?.size ?: 0
    }


}
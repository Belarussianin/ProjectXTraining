package com.example.projectxtraining.ui.training.onboarding

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.projectxtraining.R

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = NUM_TABS

    override fun createFragment(position: Int) = ImageFragment.getInstance(
        when (position) {
            0 -> R.drawable.placeholder
            else -> R.drawable.placeholder
        }
    )

    private companion object {
        const val NUM_TABS = 3
    }
}
package com.example.projectxtraining.ui.training.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.projectxtraining.R
import com.example.projectxtraining.databinding.FragmentOnboardingBinding
import com.example.projectxtraining.setDebouncedOnClickListener
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel

class OnboardingFragment : Fragment() {

    private var _binding: FragmentOnboardingBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<OnboardingViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentOnboardingBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bindUI().subscribeUI()
    }

    private fun FragmentOnboardingBinding.bindUI() = this.apply {
        val navController = findNavController()

        signUpButton.setDebouncedOnClickListener {
            navController.navigate(R.id.action_onboardingFragment_to_signUpFragment)
        }

        signInButton.setDebouncedOnClickListener {
            navController.navigate(R.id.action_onboardingFragment_to_loginFragment)
        }

        viewPager.apply {
            adapter = ViewPagerAdapter(requireActivity().supportFragmentManager, lifecycle)
            getChildAt(0).apply {
                if (this is RecyclerView) setOverScrollMode(View.OVER_SCROLL_NEVER)
            }
        }

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.apply {
                icon = ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.dot_indicator,
                    requireContext().applicationContext.theme
                )
            }
        }.attach()

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.position?.let {
                    viewModel.onImageSelected(position = it)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.position?.let {
                    viewModel.onImageUnselected(position = it)
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun FragmentOnboardingBinding.subscribeUI() = this.apply {

        val inLeft = AnimationUtils.loadAnimation(
            requireActivity(),
            android.R.anim.slide_in_left
        )
        val outRight = AnimationUtils.loadAnimation(
            requireActivity(),
            android.R.anim.slide_out_right
        )
        val inRight = AnimationUtils.loadAnimation(
            requireActivity(),
            R.anim.slide_in_right
        )
        val outLeft = AnimationUtils.loadAnimation(
            requireActivity(),
            R.anim.slide_out_left
        )

        viewModel.apply {

            positionChange.observe(viewLifecycleOwner) { positions ->
                val (previousPosition, currentPosition) = positions

                onboardingMainText.apply {
                    when {
                        previousPosition == null || currentPosition == null || previousPosition == currentPosition -> {
                            return@observe
                        }
                        previousPosition < currentPosition -> {
                            inAnimation = inRight
                            outAnimation = outLeft
                        }
                        else -> {
                            inAnimation = inLeft
                            outAnimation = outRight
                        }
                    }

                    setText(
                        resources.getString(
                            when (currentPosition) {
                                0 -> R.string.onboarding_main_text1
                                1 -> R.string.onboarding_main_text2
                                2 -> R.string.onboarding_main_text3
                                else -> R.string.onboarding_main_text1
                            }
                        )
                    )
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
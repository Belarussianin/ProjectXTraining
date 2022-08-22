package com.example.projectxtraining.ui.training

import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.projectxtraining.R
import com.example.projectxtraining.databinding.LoginBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: LoginBinding
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = LoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bindUI().subscribeUI()
    }

    private fun LoginBinding.bindUI() = this.apply {
        viewPager.apply {
            overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        }

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.apply {
                icon = ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.dot_indicator,
                    applicationContext.theme
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

    private fun LoginBinding.subscribeUI() = this.apply {
        viewModel.apply {

            positionChange.observe(this@MainActivity) { positions ->
                val (previousPosition, currentPosition) = positions

                loginMainText.apply {
                    val inLeft = AnimationUtils.loadAnimation(
                        this@MainActivity,
                        android.R.anim.slide_in_left
                    )
                    val outRight = AnimationUtils.loadAnimation(
                        this@MainActivity,
                        android.R.anim.slide_out_right
                    )
                    val inRight = AnimationUtils.loadAnimation(
                        this@MainActivity,
                        R.anim.slide_in_right
                    )
                    val outLeft = AnimationUtils.loadAnimation(
                        this@MainActivity,
                        R.anim.slide_out_left
                    )
                    when {
                        previousPosition < currentPosition -> {
                            inAnimation = inRight
                            outAnimation = outLeft
                        }
                        previousPosition == currentPosition -> {
                            return@observe
                        }
                        else -> {
                            inAnimation = inLeft
                            outAnimation = outRight
                        }
                    }

                    resources.apply {
                        loginMainText.setText(
                            when (currentPosition) {
                                0 -> getString(R.string.login_main_text1)
                                1 -> getString(R.string.login_main_text2)
                                2 -> getString(R.string.login_main_text3)
                                else -> getString(R.string.login_main_text1)
                            }
                        )
                    }
                }
            }
        }
    }
}
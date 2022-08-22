package com.example.projectxtraining.ui.training

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.projectxtraining.R
import com.example.projectxtraining.databinding.FragmentImageBinding

class ImageFragment : Fragment() {

    private var _binding: FragmentImageBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun getInstance(
            @DrawableRes image: Int
        ) = ImageFragment().apply {
            arguments = bundleOf(IMAGE_ID to image)
        }

        const val IMAGE_ID = "IMAGE_ID"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentImageBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            arguments?.let { arg ->
                when (val imageId = arg.getInt(IMAGE_ID)) {
                    0 -> {
                        image.setImageResource(R.drawable.placeholder)
                    }
                    else -> {
                        image.setImageResource(imageId)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.example.projectxtraining.ui.training.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.projectxtraining.R
import com.example.projectxtraining.databinding.FragmentSignupBinding
import com.example.projectxtraining.setDebouncedOnClickListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpFragment : Fragment() {

    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<SignUpViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentSignupBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bindUI().subscribeUI()
    }

    private fun FragmentSignupBinding.bindUI() = this.apply {
        toolbar.setNavigationOnClickListener {
            findNavController().apply {
                if (!popBackStack()) {
                    navigate(R.id.action_signUpFragment_to_onboardingFragment)
                }
            }
        }
        continueButton.setDebouncedOnClickListener {
            
        }
    }

    private fun FragmentSignupBinding.subscribeUI() = this.apply {
        viewModel.apply {

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
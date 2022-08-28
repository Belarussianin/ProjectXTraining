package com.example.projectxtraining.ui.training.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.projectxtraining.R
import com.example.projectxtraining.databinding.FragmentLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<LoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentLoginBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bindUI().subscribeUI()
    }

    private fun FragmentLoginBinding.bindUI() = this.apply {
        toolbarLayout.toolbar.setNavigationOnClickListener {
            findNavController().apply {
                if (!popBackStack()) {
                    navigate(R.id.action_loginFragment_to_onboardingFragment)
                }
            }
        }
    }

    private fun FragmentLoginBinding.subscribeUI() = this.apply {
        viewModel.apply {

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
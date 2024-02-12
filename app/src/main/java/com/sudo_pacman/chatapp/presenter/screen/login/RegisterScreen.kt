package com.sudo_pacman.chatapp.presenter.screen.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.sudo_pacman.chatapp.R
import com.sudo_pacman.chatapp.databinding.RegisterScreenBinding
import com.sudo_pacman.chatapp.utils.myLog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class RegisterScreen : Fragment(R.layout.register_screen) {
    private val binding by viewBinding(RegisterScreenBinding::bind)
    private val viewModel: RegisterViewModel by viewModels<RegisterViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.btnRegister.setOnClickListener {
            val name = binding.nameEditText.text.toString()
            val phone = binding.phoneEditText.text.toString()

            "register screen -> name: $name,  phone: $phone".myLog()

            viewModel.register(name, phone)

            findNavController().navigate(R.id.action_registerScreen_to_screenMain)
        }

        viewModel.successFlow.onEach {

        }.launchIn(lifecycleScope)
    }
}
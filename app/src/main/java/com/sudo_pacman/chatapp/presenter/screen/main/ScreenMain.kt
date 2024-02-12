package com.sudo_pacman.chatapp.presenter.screen.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.sudo_pacman.chatapp.R
import com.sudo_pacman.chatapp.databinding.ScreenMainBinding
import com.sudo_pacman.chatapp.presenter.adapter.MessageAdapter
import com.sudo_pacman.chatapp.utils.myLog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ScreenMain : Fragment(R.layout.screen_main) {
    private val binding by viewBinding(ScreenMainBinding::bind)
    private val viewModel: ChatViewModel by viewModels<ChatViewModelImpl>()
    private val adapter = MessageAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.rvChat.adapter = adapter
        binding.rvChat.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, true)

        viewModel.messagesFlow.onEach {
            "main screen keldi: ${it.size}".myLog()
            adapter.submitList(it)
        }.launchIn(lifecycleScope)
    }
}
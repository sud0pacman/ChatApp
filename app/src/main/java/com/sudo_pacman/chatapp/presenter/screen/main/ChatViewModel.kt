package com.sudo_pacman.chatapp.presenter.screen.main

import com.sudo_pacman.chatapp.data.ChatData
import kotlinx.coroutines.flow.MutableSharedFlow

interface ChatViewModel {
    val messagesFlow: MutableSharedFlow<List<ChatData>>
    val successFlow: MutableSharedFlow<String>
    val errorMessageFlow: MutableSharedFlow<String>
}
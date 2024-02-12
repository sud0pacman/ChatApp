package com.sudo_pacman.chatapp.presenter.screen.main

import androidx.lifecycle.ViewModel
import com.sudo_pacman.chatapp.domain.AppRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject

@HiltViewModel
class ChatViewModelImpl @Inject constructor(
    private val repository: AppRepositoryImpl
): ChatViewModel, ViewModel() {

    override val messagesFlow = repository.messageDataFlow

    override val successFlow = MutableSharedFlow<String>()
    override val errorMessageFlow = repository.errorMessage

}
package com.sudo_pacman.chatapp.presenter.screen.login

import androidx.lifecycle.ViewModel
import com.sudo_pacman.chatapp.data.AddChatData
import com.sudo_pacman.chatapp.data.ChatData
import com.sudo_pacman.chatapp.data.MyShared
import com.sudo_pacman.chatapp.data.UserData
import com.sudo_pacman.chatapp.domain.AppRepositoryImpl
import com.sudo_pacman.chatapp.utils.myLog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RegisterViewModelImpl @Inject constructor(
    private val repository: AppRepositoryImpl
) : ViewModel(), RegisterViewModel {

    override val successFlow: MutableSharedFlow<String> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_LATEST)
    override val errorMessage: MutableSharedFlow<String> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_LATEST)

    override fun register(userName: String, phone: String) {
        repository.register(AddChatData("bo'shda", phone)).onEach { result ->
            result.onSuccess {
                MyShared.setPhone(it)
                successFlow.emit("Success")
                "model register succes".myLog()
            }

            result.onFailure {
                errorMessage.run { emit(it.toString()) }
                "model register fail".myLog()
            }
        }
    }
}
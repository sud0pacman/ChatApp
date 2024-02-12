package com.sudo_pacman.chatapp.presenter.screen.login

import kotlinx.coroutines.flow.MutableSharedFlow

interface RegisterViewModel {
    val successFlow: MutableSharedFlow<String>
    val errorMessage: MutableSharedFlow<String>

    fun register(userName: String, phone: String)
}
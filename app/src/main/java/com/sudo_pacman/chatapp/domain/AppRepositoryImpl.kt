package com.sudo_pacman.chatapp.domain

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.sudo_pacman.chatapp.data.AddChatData
import com.sudo_pacman.chatapp.data.ChatData
import com.sudo_pacman.chatapp.data.UserData
import com.sudo_pacman.chatapp.utils.myLog
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepositoryImpl @Inject constructor() {
    private val fireStore = Firebase.firestore
    val messageDataFlow = MutableSharedFlow<List<ChatData>>(replay = 1, onBufferOverflow = BufferOverflow.DROP_LATEST)
    val errorMessage = MutableSharedFlow<String>(replay = 1, onBufferOverflow = BufferOverflow.DROP_LATEST)

    init {
        fireStore.collection("chat").addSnapshotListener { value, error ->
            val data = ArrayList<ChatData>()

            value?.forEach {
                data.add(
                    ChatData(
                        docID = it.id,
                        message = it.data.getOrDefault("message", "bo'sh") as String,
                        phone = it.data.getOrDefault("phone", "") as String
                    )
                )
            }

            "repo keldi: ${data.size}".myLog()
            messageDataFlow.tryEmit(data)
        }
    }



    fun register(chat: AddChatData): Flow<Result<String>> = callbackFlow {
        fireStore.collection("chat")
            .document(System.currentTimeMillis().toString())
            .set(AddChatData(chat.message, chat.phone))
            .addOnSuccessListener {
                "repo register success".myLog()
                trySend(Result.success(chat.phone))
            }
            .addOnFailureListener {
                "repo register failure: $it".myLog()
                trySend(Result.failure(Throwable(it.message.toString())))
            }
            .addOnCanceledListener {
                "repo register cancel".myLog()
            }


        awaitClose()

        "repo register end".myLog()
    }
}
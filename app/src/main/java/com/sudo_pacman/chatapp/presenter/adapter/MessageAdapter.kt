package com.sudo_pacman.chatapp.presenter.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sudo_pacman.chatapp.data.ChatData
import com.sudo_pacman.chatapp.data.MyShared
import com.sudo_pacman.chatapp.databinding.ItemMeBinding
import com.sudo_pacman.chatapp.databinding.ItemOtherBinding
import java.text.SimpleDateFormat

class MessageAdapter : ListAdapter<ChatData, MessageAdapter.Holder>(ChatDiffUtil) {

    var onClickItem: ((sms: ChatData) -> Unit)? = null
    var onLongClickItem: ((sms: ChatData) -> Unit)? = null


    object ChatDiffUtil : DiffUtil.ItemCallback<ChatData>() {
        override fun areItemsTheSame(oldItem: ChatData, newItem: ChatData): Boolean = oldItem.message == newItem.message
        override fun areContentsTheSame(oldItem: ChatData, newItem: ChatData): Boolean = oldItem == newItem
    }

    inner class Holder1(private var binding: ItemMeBinding) : Holder(binding.root) {

        @SuppressLint("SimpleDateFormat")
        override fun bind() {

//            val time = SimpleDateFormat("hh:mm:ss").format(getItem(adapterPosition).time.toLong())
            binding.textMessage.text = getItem(adapterPosition).message
//            binding.textTime.text = time

            binding.root.setOnLongClickListener {
                onLongClickItem?.invoke(getItem(adapterPosition))
                return@setOnLongClickListener true
            }
        }
    }

    inner class Holder2(private var binding: ItemOtherBinding) : Holder(binding.root) {

        @SuppressLint("SimpleDateFormat")
        override fun bind() {
//            val time = SimpleDateFormat("hh:mm:ss").format(getItem(adapterPosition).time.toLong())
            binding.textMessage.text = getItem(adapterPosition).message
//            binding.textTime.text = time

            binding.root.setOnLongClickListener {
                onLongClickItem?.invoke(getItem(adapterPosition))
                return@setOnLongClickListener true
            }
        }

    }

    open class Holder(view: View) : RecyclerView.ViewHolder(view) {
        open fun bind() {}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder =
        when (viewType) {
            0 -> Holder1(ItemMeBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            else -> Holder2(ItemOtherBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }

    override fun onBindViewHolder(holder: Holder, position: Int) = holder.bind()

    override fun getItemViewType(position: Int): Int {
        super.getItemViewType(position)

        return if (getItem(position).phone == MyShared.getPhone()) 0 else 1

    }

    fun getEndPos(): Int {
        return itemCount - 1
    }

}
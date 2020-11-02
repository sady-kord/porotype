package com.demo.sample.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.demo.sample.R
import com.demo.sample.data.dto.BookDto
import com.demo.sample.databinding.AdapterBookItemBinding
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class BookAdapter : RecyclerView.Adapter<BookAdapter.BookHolder>() {

    private val clickSubject = PublishSubject.create<BookDto>()
    private var list = mutableListOf<BookDto>()

    fun setBookList(
        listOnline: List<BookDto>
    ) {
        list.addAll(listOnline)
    }

    override fun onBindViewHolder(holder: BookHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: AdapterBookItemBinding = DataBindingUtil
            .inflate(layoutInflater, R.layout.adapter_book_item, parent, false)

        return BookHolder(binding)
    }

    val clickEvent: Observable<BookDto> = clickSubject

    inner class BookHolder(private val binding: AdapterBookItemBinding): RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                clickSubject.onNext(list[layoutPosition])
            }
        }

        fun bind(item: BookDto) {
            binding.bookDto = item
            Glide.with(binding.root.context).load(item.bookImage).into(binding.bookImage)
        }

    }
}
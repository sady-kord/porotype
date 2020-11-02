package com.demo.sample.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demo.sample.dao.BookDao
import com.demo.sample.data.dto.BookDto
import com.demo.sample.network.repositories.BookRepository
import javax.inject.Inject


class MainViewModel @Inject constructor(
    private val bookRepository: BookRepository,
    private var bookDao : BookDao
) : ViewModel() {

    fun getBooks() = bookRepository.getResult()

    fun saveBooks(books : List<BookDto>)  = bookDao.saveBooks(books)

    private var _bookSelected = MutableLiveData<Int>()
    var bookSelected : LiveData<Int> = _bookSelected

    fun setBookSelected(rank : Int){
        _bookSelected.value = rank
    }

}
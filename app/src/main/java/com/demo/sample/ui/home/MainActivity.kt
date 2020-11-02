package com.demo.sample.ui.home

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demo.sample.R
import com.demo.sample.databinding.ActivityMainBinding
import com.demo.sample.di.Injectable
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.Disposable
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity(), Injectable {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private  var adapter = BookAdapter()
    private var subscribe: Disposable? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(MainViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.toolbar.setTitle(resources.getString(R.string.home_title))

        setupRecyclerView()

        setupItemClick()

        getBooks()

    }

    private fun setupItemClick() {
        subscribe = adapter.clickEvent
            .subscribe {
                viewModel.setBookSelected(it?.rank!!)
                val bookDialogFragment = BookDialogFragment.newInstance(it)
                bookDialogFragment.show(supportFragmentManager, "")
            }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this,
            RecyclerView.VERTICAL,
            false)
    }

    private fun getBooks() {
        viewModel.getBooks().observe(this, Observer {
            it.handle { it ->
                viewModel.saveBooks(it.results.books!!)
                adapter.notifyDataSetChanged()
                adapter.setBookList(it.results.books!!)
            }
        })
    }

}

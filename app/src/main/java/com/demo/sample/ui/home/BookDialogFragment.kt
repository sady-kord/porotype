package com.demo.sample.ui.home

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.demo.sample.R
import com.demo.sample.data.dto.BookDto
import kotlinx.android.synthetic.main.dialog_book.view.*

class BookDialogFragment : DialogFragment() {

    private lateinit var bookDto: BookDto

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(activity)
        val view = requireActivity().layoutInflater.inflate(R.layout.dialog_book, null)

        if (dialog != null && dialog!!.window != null) {
            dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog!!.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        }

        builder.setView(view)
        val alertDialog = builder.create()

        if (arguments != null) {
            bookDto = requireArguments().getParcelable<BookDto>("data")!!
        }

        Glide.with(view.context).load(bookDto.bookImage).into(view.user_image)
        view.book_description_text.text = bookDto.description
        view.book_name_text.text = bookDto.title
        view.publisher_text.text = bookDto.publisher

        return alertDialog
    }

    companion object {
        @JvmStatic
        fun newInstance(data: BookDto?): BookDialogFragment {
            val frag = BookDialogFragment()
            val args = Bundle()
            args.putParcelable("data", data)
            frag.arguments = args
            return frag
        }
    }

}
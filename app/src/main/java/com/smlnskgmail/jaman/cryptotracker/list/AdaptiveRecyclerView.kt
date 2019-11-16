package com.smlnskgmail.jaman.cryptotracker.list

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ProgressBar
import androidx.core.widget.ContentLoadingProgressBar
import androidx.recyclerview.widget.RecyclerView

class AdaptiveRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(
    context,
    attrs,
    defStyleAttr
) {

    private lateinit var emptyListMessageView: View
    private lateinit var progressBar: ProgressBar

    private val recyclerViewObserver = object : AdapterDataObserver() {
        override fun onChanged() {
           checkContent()
        }
    }

    private fun checkContent() {
        progressBar.visibility = View.GONE
        if (adapter?.itemCount == 0) {
            emptyListMessageView.visibility = View.VISIBLE
            visibility = View.GONE
        } else {
            emptyListMessageView.visibility = View.GONE
            visibility = View.VISIBLE
        }
    }

    fun withMessage(
        message: View
    ) {
        this.emptyListMessageView = message
    }

    fun withProgressBar(
        progressBar: ProgressBar
    ) {
        this.progressBar = progressBar
    }

    override fun setAdapter(
        adapter: Adapter<*>?
    ) {
        getAdapter()?.unregisterAdapterDataObserver(
            recyclerViewObserver
        )
        super.setAdapter(
            adapter
        )
        adapter?.registerAdapterDataObserver(
            recyclerViewObserver
        )
        checkContent()
    }

}
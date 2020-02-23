package com.smlnskgmail.jaman.cryptotracker.utils

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import org.junit.Assert.assertEquals

class RecyclerViewCountAssertion(
    private val count: Int
) : ViewAssertion {

    override fun check(
        view: View?,
        noViewFoundException: NoMatchingViewException?
    ) {
        if (noViewFoundException != null) {
            throw noViewFoundException
        }

        val recyclerView = view as RecyclerView
        assertEquals(
            count,
            recyclerView.adapter!!.itemCount
        )
    }

}

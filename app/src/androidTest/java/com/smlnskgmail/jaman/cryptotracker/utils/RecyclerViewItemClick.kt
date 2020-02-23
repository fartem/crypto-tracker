package com.smlnskgmail.jaman.cryptotracker.utils

import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import org.hamcrest.Matcher

class RecyclerViewItemClick(
    private val id: Int
) : ViewAction {

    override fun perform(
        uiController: UiController?,
        view: View?
    ) {
        view!!.findViewById<View>(id).performClick()
    }

    override fun getConstraints(): Matcher<View>? {
        return null
    }

    override fun getDescription(): String {
        return "Action for handling click on item in RecyclerView"
    }

}

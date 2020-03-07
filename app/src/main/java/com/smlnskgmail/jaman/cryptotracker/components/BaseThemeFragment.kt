package com.smlnskgmail.jaman.cryptotracker.components

import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.smlnskgmail.jaman.cryptotracker.components.preferences.PreferencesManager

abstract class BaseThemeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val contextThemeWrapper = ContextThemeWrapper(
            activity,
            PreferencesManager.theme(
                context!!
            ).themeResId
        )
        val localInflater = inflater.cloneInContext(
            contextThemeWrapper
        )
        return localInflater.inflate(
            layoutResId(),
            container,
            false
        )
    }

    abstract fun layoutResId(): Int

}

package com.smlnskgmail.jaman.cryptotracker.components

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smlnskgmail.jaman.cryptotracker.R

abstract class BaseThemeBottomSheet : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            layoutResId(),
            container,
            false
        )
    }

    abstract fun layoutResId(): Int

    override fun getTheme(): Int = R.style.AppBottomSheetStyle

}

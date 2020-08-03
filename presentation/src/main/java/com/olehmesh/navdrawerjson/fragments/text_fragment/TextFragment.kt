package com.olehmesh.navdrawerjson.fragments.text_fragment

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import com.olehmesh.navdrawerjson.R
import com.olehmesh.navdrawerjson.base.BaseFragment
import com.olehmesh.navdrawerjson.utils.Constants.Companion.TEXT
import kotlinx.android.synthetic.main.fragment_text.*

class TextFragment : BaseFragment() {

    override val layoutResId: Int
        get() = R.layout.fragment_text

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments == null) return
        tvText.text = requireArguments().getString(TEXT)
        tvText.movementMethod = ScrollingMovementMethod()
    }

    companion object {
        fun newInstance(url: String?): TextFragment? {
            val textFragment = TextFragment()
            val arguments = Bundle()
            arguments.putString(TEXT, url)
            textFragment.arguments = arguments
            return textFragment
        }
    }
}
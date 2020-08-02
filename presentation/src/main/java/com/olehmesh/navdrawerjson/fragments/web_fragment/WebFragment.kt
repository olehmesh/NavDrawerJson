package com.olehmesh.navdrawerjson.fragments.web_fragment

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.NonNull
import com.olehmesh.navdrawerjson.R
import com.olehmesh.navdrawerjson.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_web.*

class WebFragment : BaseFragment() {

    override val layoutResId: Int
        get() = R.layout.fragment_web

    override fun onViewCreated(@NonNull view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments == null) return

        webView.webViewClient = object : WebViewClient() {

            @TargetApi(Build.VERSION_CODES.N)
            override fun shouldOverrideUrlLoading(
                view: WebView,
                request: WebResourceRequest
            ): Boolean {
                view.loadUrl(request.url.toString())
                return true
            }

        }

        webView.loadUrl(requireArguments().getString(R.string.url.toString()))
        webView.settings.javaScriptEnabled
    }

    companion object {
        fun newInstance(url: String?): WebFragment? {
            val webFragment = WebFragment()
            val arguments = Bundle()
            arguments.putString(R.string.url.toString(), url)
            webFragment.arguments = arguments
            return webFragment
        }
    }
}

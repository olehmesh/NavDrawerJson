package com.olehmesh.navdrawerjson.fragments.image_fragment

import android.os.Bundle
import android.view.View
import androidx.annotation.NonNull
import com.bumptech.glide.Glide
import com.olehmesh.navdrawerjson.R
import com.olehmesh.navdrawerjson.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_image.*

class ImageFragment : BaseFragment() {

    override val layoutResId: Int
        get() = R.layout.fragment_image

    override fun onViewCreated(@NonNull view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments == null) return

        Glide.with(this)
            .load(requireArguments().getString(R.string.image.toString()))
            .placeholder(R.drawable.ic_placeholder)
            .error(R.drawable.ic_placeholder)
            .into(imageCircle)
    }

    companion object {
        fun newInstance(url: String?): ImageFragment? {
            val imageFragment = ImageFragment()
            val arguments = Bundle()
            arguments.putString(R.string.image.toString(), url)
            imageFragment.arguments = arguments
            return imageFragment
        }
    }

}
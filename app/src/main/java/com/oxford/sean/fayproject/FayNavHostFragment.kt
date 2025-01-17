package com.oxford.sean.fayproject

import android.content.Context
import androidx.fragment.app.FragmentFactory
import androidx.navigation.fragment.NavHostFragment
import javax.inject.Inject

class FayNavHostFragment : NavHostFragment() {

    @Inject
    lateinit var fragmentFactory: FragmentFactory

    override fun onAttach(context: Context) {
        (activity?.application as FayApp).appComponent.inject(this)
        childFragmentManager.fragmentFactory = fragmentFactory
        super.onAttach(context)
    }
}
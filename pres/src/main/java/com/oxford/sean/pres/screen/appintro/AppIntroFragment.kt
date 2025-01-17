package com.oxford.sean.pres.screen.appintro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.oxford.sean.pres.R
import com.oxford.sean.pres.clipRoundCorners
import com.oxford.sean.pres.setTouchRipple

class AppIntroFragment(private val viewModelFactory: ViewModelProvider.Factory) : Fragment() {

    private val viewModel: AppIntroFragmentViewModel by viewModels { viewModelFactory }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val v = inflater.inflate(R.layout.fragment_app_intro, null)

        val loginCta = v.findViewById<Button>(R.id.Button_app_intro_fragment_login_cta)
        loginCta.setTouchRipple(R.color.brand_color, R.color.background_primary)
        loginCta.clipRoundCorners()

        loginCta.setOnClickListener { viewModel.onLoginClicked() }

        return v
    }

}
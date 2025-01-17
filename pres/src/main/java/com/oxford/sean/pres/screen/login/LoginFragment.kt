package com.oxford.sean.pres.screen.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.oxford.sean.pres.R
import com.oxford.sean.pres.clipRoundCorners
import com.oxford.sean.pres.doOnDoneClick
import com.oxford.sean.pres.setTouchRipple

class LoginFragment(private val viewModelFactory: ViewModelProvider.Factory) : Fragment() {

    private val viewModel: LoginFragmentViewModel by viewModels { viewModelFactory }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val v = inflater.inflate(R.layout.fragment_login, null)

        val usernameEditText = v.findViewById<EditText>(R.id.EditText_login_fragment_username)
        val passwordEditText = v.findViewById<EditText>(R.id.EditText_login_fragment_password)
        val loginCta = v.findViewById<Button>(R.id.Button_login_fragment_cta)

        passwordEditText.doOnDoneClick {
            viewModel.onLoginClicked(usernameEditText.text.toString(), passwordEditText.text.toString())
        }

        loginCta.setTouchRipple(R.color.brand_color, R.color.background_primary)
        loginCta.clipRoundCorners()

        loginCta.setOnClickListener {
            viewModel.onLoginClicked(usernameEditText.text.toString(), passwordEditText.text.toString())
        }

        return v
    }

}
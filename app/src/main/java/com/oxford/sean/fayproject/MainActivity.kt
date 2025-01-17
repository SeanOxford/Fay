package com.oxford.sean.fayproject

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.oxford.sean.pres.PresActivity
import com.oxford.sean.pres.window.LoadingDialogView
import javax.inject.Inject

class MainActivity : AppCompatActivity(), PresActivity {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var rootView: ConstraintLayout
    private lateinit var fragmentContainer: FragmentContainerView
    private lateinit var viewModel: MainActivityViewModel

    private var currentlyShownLoadingDialog: LoadingDialogView? = null

    override val navController: NavController
        get() = fragmentContainer.findNavController()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        // This won't leak since a recreated activity in single-activity architecture reassigns the reference
        (application as FayApp).mainActivity = this
        (application as FayApp).appComponent.inject(this)
        viewModel = viewModelFactory.create(MainActivityViewModel::class.java)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        rootView = findViewById(R.id.ConstraintLayout_main_activity_root)
        fragmentContainer = findViewById(R.id.NavHostFragment_main_activity)

        viewModel.onCreate()
    }

    override fun showLoadingDialog() {
        val loadingDialog = LoadingDialogView(this, onClosed = { this.rootView.removeView(it) })
        currentlyShownLoadingDialog = loadingDialog
        this.rootView.addView(loadingDialog)
    }

    override fun hideLoadingDialog() {
        currentlyShownLoadingDialog?.dismiss()
        currentlyShownLoadingDialog = null
    }

}
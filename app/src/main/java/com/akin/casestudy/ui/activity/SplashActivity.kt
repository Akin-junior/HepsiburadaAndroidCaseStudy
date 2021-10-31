package com.akin.casestudy.ui.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import com.akin.casestudy.databinding.ActivityMainBinding
import com.akin.casestudy.databinding.ActivitySplashBinding
import com.akin.casestudy.domain.viewmodels.CategoriesViewModel

class SplashActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private val categoriesViewModel: CategoriesViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        checkIfFirst()

        binding.motionLayout.addTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {

            }

            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {
            }

            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            }

            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {
            }

        })
    }

    private fun checkIfFirst() {
        //insert data for Categories only one time
        val prefs = this
            .getSharedPreferences("pref", Context.MODE_PRIVATE)
        val firstStart = prefs.getBoolean("firstStartHome", true)

        if (firstStart) {
            categoriesViewModel.insertDataForCategories()
            val prefs: SharedPreferences =
                this.getSharedPreferences("pref", Context.MODE_PRIVATE)
            val editor = prefs.edit()
            editor.putBoolean("firstStartHome", false)
            editor.apply()
        }

    }

}
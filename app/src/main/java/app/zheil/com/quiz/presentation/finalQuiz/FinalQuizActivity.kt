package app.zheil.com.quiz.presentation.finalQuiz

import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import app.zheil.com.quiz.R
import kotlinx.android.synthetic.main.activity_finish.*

class FinalQuizActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)

        setFonts()
    }

    private fun setFonts() {
        val typeface = Typeface.createFromAsset(assets, "fonts/krabuler.ttf")
        tvVictorinaFinish.typeface = typeface

    }
}
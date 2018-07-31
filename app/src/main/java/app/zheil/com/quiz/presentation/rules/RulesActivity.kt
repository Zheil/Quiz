package app.zheil.com.quiz.presentation.rules

import app.zheil.com.quiz.R
import app.zheil.com.quiz.click
import app.zheil.com.quiz.presentation.base.BaseActivity
import kotlinx.android.synthetic.main.activity_rules.*

class RulesActivity: BaseActivity() {

    override fun setLayoutView(): Int = R.layout.activity_rules

    override fun onChildCreate() {
        initListeners()
    }

    private fun initListeners() {
        btnOk.click {
            finish()
        }
    }
}
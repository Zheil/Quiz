package app.zheil.com.quiz.presentation.base.baseQuiz

import android.os.Bundle
import android.view.View
import app.zheil.com.quiz.hide
import app.zheil.com.quiz.presentation.base.BaseActivity
import com.bigkoo.svprogresshud.SVProgressHUD

abstract class BaseQuiz: BaseActivity(), BaseQuizView {
    private lateinit var mLoadingBar: SVProgressHUD

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBasic()
        onChildCreate()
    }

    fun hideViews(vararg view: View) {
        for (i in 0 until view.size)
            view[i].hide()
    }

    private fun initBasic() {
        mLoadingBar = SVProgressHUD(this)
    }

    override fun hideLoading() {
        mLoadingBar.dismiss()
    }

    override fun startLoading() {
        mLoadingBar.show()
    }

}
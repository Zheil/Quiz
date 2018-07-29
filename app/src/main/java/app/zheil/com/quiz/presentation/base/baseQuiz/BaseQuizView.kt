package app.zheil.com.quiz.presentation.base.baseQuiz

import com.arellomobile.mvp.MvpView

interface BaseQuizView: MvpView {
    fun hideLoading()
    fun startLoading()
}
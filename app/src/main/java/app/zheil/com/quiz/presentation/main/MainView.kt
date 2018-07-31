package app.zheil.com.quiz.presentation.main

import com.arellomobile.mvp.MvpView


interface MainView: MvpView {
    fun showAboutApp()
    fun showQuizActivity()
    fun initView()
}
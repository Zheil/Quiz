package app.zheil.com.quiz.presentation.main

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter


@InjectViewState
class MainPresenter: MvpPresenter<MainView>() {

    fun showAboutApp() {
        viewState.showAboutApp()
    }

    fun showQuizActivity() {
        viewState.showQuizActivity()
    }

    fun initView() {
        viewState.initView()
    }
}
package app.zheil.com.quiz

import android.app.Application
import app.zheil.com.quiz.di.koin.koinModule
import org.koin.android.ext.android.startKoin


class AppQuiz: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(listOf(koinModule))
    }
}
package app.zheil.com.quiz.presentation.quiz

import android.content.Context
import app.zheil.com.quiz.data.DataQuestion
import app.zheil.com.quiz.di.presenter.DaggerPresenterComponent
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


@InjectViewState
class QuizGamePresenter: MvpPresenter<QuizGameView>() {
    private var mIndexQuestion = 0

    @Inject
    lateinit var mData: DataQuestion


    fun initPresenterStart(context: Context) {
        DaggerPresenterComponent.create().inject(this)
        viewState.hideWorkPlace()
        readDataFromDataBase(context)
    }

    private fun readDataFromDataBase(context: Context) {
        viewState.startLoading()
        val interactor = QuizInteractor(context)
        Observable.fromCallable {interactor.getAllQuestionsFromAssetsDataBase() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    res -> mData.setQuestionArray(res)
                    startGame()
                    viewState.hideLoading()
                    viewState.visibleWorkPlace()
                }

    }

    private fun startGame() {
        startQuestion()
        setProgress()
    }

    private fun startQuestion() {
        viewState.nextQuestion(mData.getQuestion(0))
    }

    fun nextQuestion() {
      if(mData.getMaxIndex() > mIndexQuestion)
        viewState.nextQuestion(mData.getQuestion(++mIndexQuestion))
      else
          viewState.finishVictorina()

        setProgress()
   }

    fun prevQuestion() {
        if(mIndexQuestion-1 >= 0)
           viewState.nextQuestion(mData.getQuestion(--mIndexQuestion))

        setProgress()
    }

    private fun setProgress() {
       viewState.setCurrentNumberQuestion(mIndexQuestion + 1, mData.getMaxIndex() + 1)
    }

}
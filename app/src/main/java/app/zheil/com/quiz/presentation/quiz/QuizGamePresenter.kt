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

    @Inject
    lateinit var mDataQuestion: DataQuestion

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
                    res -> mDataQuestion.setQuestionArray(res)
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
        viewState.nextQuestion(mDataQuestion.getFirstQuestion())
    }

    fun nextQuestion() {
      if(mDataQuestion.isFinishQuestion())
          viewState.nextQuestion(mDataQuestion.getQuestion(++mDataQuestion.mCurrentIndexQuestion))
      else
          viewState.finishQuiz()
        setProgress()
   }

    fun prevQuestion() {
        if(mDataQuestion.isNotEmptyQuestion())
           viewState.nextQuestion(mDataQuestion.getQuestion(--mDataQuestion.mCurrentIndexQuestion))

        setProgress()
    }

    private fun setProgress() {
       viewState.setCurrentNumberQuestion(mDataQuestion.mCurrentIndexQuestion + 1, mDataQuestion.getMaxIndex() + 1)
    }
}
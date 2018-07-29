package app.zheil.com.quiz.presentation.quiz

import android.content.Context
import app.zheil.com.quiz.data.DataQuestion
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


@InjectViewState
class QuizGamePresenter: MvpPresenter<QuizGameView>() {
    private var mIndexQuestion = 0
    private val mData = DataQuestion()


    fun initPresenterStart(context: Context) {
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
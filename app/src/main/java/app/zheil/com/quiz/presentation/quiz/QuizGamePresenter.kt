package app.zheil.com.quiz.presentation.quiz

import android.content.Context
import app.zheil.com.quiz.Const
import app.zheil.com.quiz.MemoryApp
import app.zheil.com.quiz.R
import app.zheil.com.quiz.data.DataQuestion
import app.zheil.com.quiz.di.presenter.DaggerPresenterComponent
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
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
                    resultList -> mDataQuestion.setQuestionArray(resultList)
                    startGame()
                }
    }

    private fun startGame() {
        visibleAndUnlockGame()
        startQuestion()
        setProgress()
    }

    private fun visibleAndUnlockGame() {
        viewState.hideLoading()
        viewState.visibleWorkPlace()
    }

    private fun startQuestion() {
        viewState.nextQuestion(mDataQuestion.getFirstQuestion())
    }

    fun nextQuestion() {
        blockButtonAfterClick()
      if (mDataQuestion.isNotFinishQuestion())  {

          if (MemoryApp.ANIMATION.ANIMATION_STATUS)
            viewState.animateBtnClick()

          viewState.nextQuestion(mDataQuestion.getQuestion(++mDataQuestion.mCurrentIndexQuestion))
      } else {

          if (MemoryApp.ANIMATION.ANIMATION_STATUS)
            viewState.finishAnimation()

          viewState.finishQuiz()
      }

        if (MemoryApp.ANIMATION.ANIMATION_STATUS)
            viewState.animateCard()

        setProgress()
   }

    fun prevQuestion() {
        if(mDataQuestion.isNotEmptyQuestion()) {
           viewState.nextQuestion(mDataQuestion.getQuestion(--mDataQuestion.mCurrentIndexQuestion))

            if (MemoryApp.ANIMATION.ANIMATION_STATUS)
              viewState.animateCard()
        }

        setProgress()
    }

    private fun setProgress() {
       viewState.setCurrentNumberQuestion(mDataQuestion.mCurrentIndexQuestion + 1, mDataQuestion.getMaxIndex() + 1)
    }

    private fun blockButtonAfterClick() {
        viewState.blockBtn()
        doAsync {
            Thread.sleep(Const.Settings.BUTTON_NEXT_QUESTION_SLEEP)
            uiThread {
                viewState.unblockBtn()
            }
        }

    }
}
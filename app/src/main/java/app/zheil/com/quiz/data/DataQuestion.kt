package app.zheil.com.quiz.data

class DataQuestion {
    private var mQuestion =  listOf<String>()
    var mCurrentIndexQuestion = 0

    fun getQuestion(id: Int): String = mQuestion[id]

    fun setQuestionArray(newList: List<String>) {
        mQuestion = newList
    }

    fun getMaxIndex(): Int {
        return mQuestion.size - 1
    }

    fun getFirstQuestion(): String = mQuestion[0]

    fun isNotEmptyQuestion(): Boolean = mCurrentIndexQuestion - 1 >= 0

    fun isNotFinishQuestion(): Boolean = getMaxIndex()  > mCurrentIndexQuestion
}
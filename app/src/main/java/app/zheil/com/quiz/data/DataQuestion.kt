package app.zheil.com.quiz.data

class DataQuestion {
    private var mQuestion =  listOf<String>()


    fun getQuestion(id: Int): String = mQuestion[id]

    fun setQuestionArray(newList: List<String>) {
        mQuestion = newList
    }

    fun getMaxIndex(): Int {
        return mQuestion.size - 1
    }
}
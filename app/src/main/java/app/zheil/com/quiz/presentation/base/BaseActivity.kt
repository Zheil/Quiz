package app.zheil.com.quiz.presentation.base

import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.TextView
import app.zheil.com.quiz.visible
import com.arellomobile.mvp.MvpAppCompatActivity
import org.greenrobot.eventbus.EventBus

abstract class BaseActivity: MvpAppCompatActivity() {

    abstract fun setLayoutView(): Int
    abstract fun onChildCreate()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setLayoutView())

        onChildCreate()
    }


    fun visibleViews(vararg view: View) {
        for (i in 0 until view.size)
            view[i].visible()
    }

    fun setFontViews(vararg view: TextView) {
        val newTypeface = Typeface.createFromAsset(assets, "fonts/krabuler.ttf")
        for (i in 0 until view.size)
            view[i].typeface = newTypeface
    }

    fun blockView(view: TextView, textForBlock: String) {
        view.isEnabled = false
        view.text = textForBlock
    }

    fun unblockView(view: TextView, textForUnblock: String) {
        view.isEnabled = true
        view.text = textForUnblock
    }

    fun sendEvent(event: EventBus) {
        EventBus.getDefault().post(event)
    }

    fun sendStickyEvent(event: EventBus) {
        EventBus.getDefault().postSticky(event)
    }

     fun registerEventBus() {
        EventBus.getDefault().register(this)
    }

     fun unregisterEventBus() {
        EventBus.getDefault().unregister(this)
    }
}
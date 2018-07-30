package app.zheil.com.quiz

import android.app.Activity
import android.support.v4.app.Fragment
import android.view.View
import android.widget.Toast
import com.jakewharton.rxbinding2.view.RxView
import org.greenrobot.eventbus.EventBus
import java.util.concurrent.TimeUnit

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

//clicks
fun View.click(onClick: () -> Unit) {
    RxView.clicks(this)
            .throttleFirst(500, TimeUnit.MILLISECONDS)
            .subscribe {
                onClick()
            }
}


//Toast
fun Activity.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Activity.toast(messageId: Int) {
    Toast.makeText(this, getString(messageId), Toast.LENGTH_LONG).show()
}

fun Fragment.toast(message: String) {
    Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
}

fun Fragment.toast(messageId: Int) {
    Toast.makeText(activity, getString(messageId), Toast.LENGTH_LONG).show()
}

//EventBus
//fun Any.sendEvent(event: EventBusEvent) {
  //  EventBus.getDefault().post(event)
//}

fun Activity.registerEventBus() {
    if (!EventBus.getDefault().isRegistered(this)) {
        EventBus.getDefault().register(this)
    }
}

fun Activity.unregisterEventBus() {
    EventBus.getDefault().unregister(this)
}

fun Fragment.registerEventBus() {
    if (!EventBus.getDefault().isRegistered(this)) {
        EventBus.getDefault().register(this)
    }
}

fun Fragment.unregisterEventBus() {
    EventBus.getDefault().unregister(this)
}
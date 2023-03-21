package com.example.tmdb.extensions

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.tmdb.R
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import java.math.BigDecimal


@BindingAdapter("visible")
fun setVisibility(view: View, value: Boolean) {
    view.visibility = if (value) View.VISIBLE else View.GONE
}

@BindingAdapter("progress")
fun progress(view: TextView, progressValue:Double) {

    view.text = truncateDecimal(progressValue,1).toString()

}

@BindingAdapter("progressBarValue")
fun progressBarValue(view: CircularProgressBar, progressValue:Double) {

    view.progress = progressValue.toFloat()

    when {
        progressValue.toInt() >= 7 -> {

            view.progressBarColor = Color.GREEN

        }
        else -> {

            view.progressBarColor = Color.YELLOW


        }


    }



}

fun truncateDecimal(x: Double, scale:Int): BigDecimal? {
    return if (x > 0) {
        BigDecimal(x.toString()).setScale(scale, BigDecimal.ROUND_FLOOR)
    } else {
        BigDecimal(x.toString()).setScale(scale, BigDecimal.ROUND_CEILING)
    }
}







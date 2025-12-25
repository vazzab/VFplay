package com.vazzab.vfplay

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // вызываем функцию анимации для всех постеров
        setClickAnimation(view.findViewById(R.id.midPoster1))
        setClickAnimation(view.findViewById(R.id.midPoster2))
        setClickAnimation(view.findViewById(R.id.midPoster3))
        setClickAnimation(view.findViewById(R.id.midPoster4))

        return view
    }

    // функция анимации
    private fun setClickAnimation(view: View) {
        view.setOnClickListener {
            view.animate()
                .scaleX(1.1f)
                .scaleY(1.1f)
                .translationZ(10f)
                .setDuration(150)
                .withEndAction {
                    view.animate()
                        .scaleX(1f)
                        .scaleY(1f)
                        .translationZ(0f)
                        .setDuration(150)
                        .start()
                }
                .start()
        }
    }
}

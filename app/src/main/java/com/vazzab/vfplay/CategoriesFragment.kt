package com.vazzab.vfplay

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import android.view.animation.AnticipateInterpolator

class CategoriesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rocket = view.findViewById<ImageView>(R.id.image_rocket)

        // Анимация вверх
        rocket.setOnClickListener {
            rocket.animate()
                .setDuration(300)
                .translationY(-1000f)
                .setInterpolator(AnticipateInterpolator())
                .alpha(0f)
                .start()
        }
    }
}

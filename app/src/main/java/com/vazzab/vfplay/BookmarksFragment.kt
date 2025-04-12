package com.vazzab.vfplay

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnticipateInterpolator
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.Fragment

class BookmarksFragment : Fragment() {

    private lateinit var container: LinearLayout
    private lateinit var buttonAdd: Button
    private lateinit var buttonRemove: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView = inflater.inflate(R.layout.fragment_bookmarks, container, false) ?:
        throw IllegalStateException("Не удалось инфлейтить разметку")

        // Инициализация View с проверкой на null
        this.container = rootView.findViewById(R.id.container) as? LinearLayout
            ?: throw IllegalStateException("Не найден контейнер")

        buttonAdd = rootView.findViewById(R.id.button_add) as? Button
            ?: throw IllegalStateException("Не найдена кнопка добавления")

        buttonRemove = rootView.findViewById(R.id.button_remove) as? Button
            ?: throw IllegalStateException("Не найдена кнопка удаления")

        buttonAdd.setOnClickListener {
            val newButton = Button(context).apply {
                text = "Кнопка ${this@BookmarksFragment.container.childCount + 1}"
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(0, 8, 0, 8)
                }
                alpha = 0f // Начальное состояние - невидимая
            }

            // Анимация появления
            newButton.animate()
                .setDuration(500)
                .alpha(1f)
                .setInterpolator(AnticipateInterpolator())
                .withEndAction {
                    this.container.addView(newButton)
                }
                .start()
        }

        buttonRemove.setOnClickListener {
            if (this.container.childCount > 0) {
                val lastView = this.container.getChildAt(this.container.childCount - 1)
                // Анимация исчезновения
                lastView.animate()
                    .setDuration(500)
                    .alpha(0f)
                    .setInterpolator(AnticipateInterpolator())
                    .withEndAction {
                        this.container.removeView(lastView)
                    }
                    .start()
            }
        }

        return rootView
    }
}

package com.vazzab.vfplay

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vazzab.vfplay.adapter.ProductAdapter
import com.vazzab.vfplay.model.Ad
import com.vazzab.vfplay.model.Product

class BookmarksFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductAdapter
    private lateinit var layoutManager: LinearLayoutManager

    private var savedPositionFirst = 0
    private var savedPositionLast = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bookmarks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Инициализация RecyclerView и его компонентов
        recyclerView = view.findViewById(R.id.recycler_view)
        layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager

        adapter = ProductAdapter()
        adapter.items = arrayListOf(
            Product(
                0,
                R.drawable.ic_apple,
                "Apple",
                "Juicy Apple fruit, which is eaten fresh, serves as a raw material in cooking and for making drinks."
            ),
            Ad("Акция", "Скидка на бананы 15%"),
            Product(
                1,
                R.drawable.ic_banana,
                "Banana",
                "It is one of the oldest food crops, and for tropical countries it is the most important food plant and the main export item."
            ),
            Product(
                2,
                R.drawable.ic_lemon,
                "Lemon",
                "Lemons are eaten fresh, and are also used in the manufacture of confectionery and soft drinks, in the liquor and perfume industry."
            ),
            Product(
                3,
                R.drawable.ic_pear,
                "Pear",
                "Under favorable conditions, the pear reaches a large size-up to 5-25 meters in height and 5 meters in diameter of the crown."
            ),
            Product(
                4,
                R.drawable.ic_strawberry,
                "Strawberry",
                "A perennial herbaceous plant 5-20 cm high, with a thick brown rhizome. \"Mustache\" is short. The stem is thin."
            ),
            Product(
                5,
                R.drawable.ic_orange,
                "Orange",
                "Orange juice is widely used as a drink in restaurants and cafes."
            ),
            Product(
                6,
                R.drawable.ic_banana,
                "Banana",
                "It is one of the oldest food crops, and for tropical countries it is the most important food plant and the main export item."
            ),
            Product(
                7,
                R.drawable.ic_banana,
                "Banana",
                "It is one of the oldest food crops, and for tropical countries it is the most important food plant and the main export item."
            ),
            Product(
                8,
                R.drawable.ic_banana,
                "Banana",
                "It is one of the oldest food crops, and for tropical countries it is the most important food plant and the main export item."
            ),
            Product(
                9,
                R.drawable.ic_banana,
                "Banana",
                "It is one of the oldest food crops, and for tropical countries it is the most important food plant and the main export item."
            ),
            Product(
                10,
                R.drawable.ic_banana,
                "Banana",
                "It is one of the oldest food crops, and for tropical countries it is the most important food plant and the main export item."
            ),
            Product(
                11,
                R.drawable.ic_lemon,
                "Lemon",
                "Lemons are eaten fresh, and are also used in the manufacture of confectionery and soft drinks, in the liquor and perfume industry."
            ),
            Product(
                12,
                R.drawable.ic_lemon,
                "Lemon",
                "Lemons are eaten fresh, and are also used in the manufacture of confectionery and soft drinks, in the liquor and perfume industry."
            ),
            Product(
                13,
                R.drawable.ic_lemon,
                "Lemon",
                "Lemons are eaten fresh, and are also used in the manufacture of confectionery and soft drinks, in the liquor and perfume industry."
            )
        )
        recyclerView.adapter = adapter

        // Инициализация кнопок
        val up = view.findViewById<ImageView>(R.id.up)
        val save = view.findViewById<ImageView>(R.id.save)
        val down = view.findViewById<ImageView>(R.id.down)

        // Обработчики нажатий
        up.setOnClickListener {
            if (layoutManager.findFirstVisibleItemPosition() == 0) {
                scrollToSavedLastPosition()
            } else {
                scrollToStart()
            }
        }

        save.setOnClickListener {
            savePosition()
        }

        down.setOnClickListener {
            if (layoutManager.findLastCompletelyVisibleItemPosition() == adapter.itemCount - 1) {
                scrollToSavedFirstPosition()
            } else {
                scrollToEnd()
            }
        }
    }

    private fun savePosition() {
        savedPositionFirst = layoutManager.findFirstCompletelyVisibleItemPosition()
        savedPositionLast = layoutManager.findLastCompletelyVisibleItemPosition()
    }

    private fun scrollToStart() {
        recyclerView.smoothScrollToPosition(0)
    }

    private fun scrollToEnd() {
        recyclerView.smoothScrollToPosition(adapter.itemCount - 1)
    }

    private fun scrollToSavedFirstPosition() {
        if (savedPositionFirst in 0 until adapter.itemCount) {
            recyclerView.smoothScrollToPosition(savedPositionFirst)
        }
    }

    private fun scrollToSavedLastPosition() {
        if (savedPositionLast in 0 until adapter.itemCount) {
            recyclerView.smoothScrollToPosition(savedPositionLast)
        }
    }
}

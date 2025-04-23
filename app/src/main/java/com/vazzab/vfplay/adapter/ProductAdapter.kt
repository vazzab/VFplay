package com.vazzab.vfplay.adapter

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.vazzab.vfplay.model.Item

class ProductAdapter() : ListDelegationAdapter<List<Item>>() {

    init {
        delegatesManager.addDelegate(ProductDelegateAdapter())
        delegatesManager.addDelegate(AdDelegateAdapter())
    }

    override fun setItems(items: List<Item>?) {
        super.setItems(items)
        notifyDataSetChanged()
    }
}
package com.vazzab.vfplay.model

import androidx.annotation.DrawableRes

class Product(val id: Int, @DrawableRes val idIcon: Int, val name: String, val desc: String) : Item
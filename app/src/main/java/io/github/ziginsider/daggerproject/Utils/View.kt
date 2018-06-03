package io.github.ziginsider.daggerproject.Utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Extension for ViewGroup.
 *
 * @return Inflated View
 */
infix fun ViewGroup.inflate(layoutResId: Int): View =
        LayoutInflater.from(context).inflate(layoutResId, this, false)
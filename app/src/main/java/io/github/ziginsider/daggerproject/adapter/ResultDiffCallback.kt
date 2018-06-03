package io.github.ziginsider.daggerproject.adapter

import android.support.v7.util.DiffUtil
import io.github.ziginsider.daggerproject.model.Result

/**
 * Implements class that can calculate the difference between two lists and output a list of
 * update operations that converts the first list into the second one. It can be used to calculate
 * updates for a [RecyclerViewAdapter].
 *
 * @author Alex Kisel
 * @since 2018-05-15
 */
class ResultDiffCallback : DiffUtil.ItemCallback<Result>() {

    override fun areItemsTheSame(oldItem: Result?, newItem: Result?) = oldItem?.id == newItem?.id

    override fun areContentsTheSame(oldItem: Result?, newItem: Result?) = oldItem == newItem
}
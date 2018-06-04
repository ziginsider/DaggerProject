package io.github.ziginsider.daggerproject.adapter

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import io.github.ziginsider.daggerproject.Utils.inflate
import io.github.ziginsider.daggerproject.model.Result
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_item.*

/**
 * Adapter for list of users [Result]
 *
 * Uses [ResultDiffCallback] for renew list of items
 *
 * @author Alex Kisel
 * @since 2018-05-15
 */
class RecyclerViewAdapter(private val layoutResId: Int, private val picasso: Picasso,
                          private val clickListener: (Result) -> Unit)
    : ListAdapter<Result, RecyclerViewAdapter.ViewHolder>(ResultDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent inflate layoutResId
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), picasso, clickListener)
    }

    class ViewHolder(override val containerView: View?)
        : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(result: Result, picasso: Picasso, clickListener: (Result) -> Unit) {
            with(result) {
                nameView.text = "${result.name.first} ${result.name.last}"

                picasso.load(result.picture.large)
                        .into(imageView)

                itemView.setOnClickListener { clickListener(this) }
            }
        }
    }
}
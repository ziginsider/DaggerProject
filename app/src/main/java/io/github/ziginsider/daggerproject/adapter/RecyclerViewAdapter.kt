package io.github.ziginsider.daggerproject.adapter

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import io.github.ziginsider.daggerproject.Utils.inflate
import io.github.ziginsider.daggerproject.model.Result

/**
 * Adapter for list of photos [Photo]
 *
 * Uses [PhotoDiffCallback] for renew list of items
 *
 * @author Alex Kisel
 * @since 2018-05-15
 */
class RecyclerViewAdapter(private val layoutResId: Int, private val clickListener: (Result) -> Unit)
    : ListAdapter<Result, RecyclerViewAdapter.ViewHolder>(PhotoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent inflate layoutResId
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    class ViewHolder(override val containerView: View?)
        : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(photo: Result, clickListener: (Result) -> Unit) {
            with(photo) {
                nameVie
                if (urlSmall != null) {
                    ImageLoader.displayImage(imagePhoto, urlSmall)
                } else if (urlOriginal != null) {
                    ImageLoader.displayImage(imagePhoto, urlOriginal)
                }
                itemView.setOnClickListener { clickListener(this) }
            }
        }
    }
}
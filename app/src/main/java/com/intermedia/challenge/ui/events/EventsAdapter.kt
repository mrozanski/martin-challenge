package com.intermedia.challenge.ui.events

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.intermedia.challenge.R
import com.intermedia.challenge.data.models.Appearance
import com.intermedia.challenge.data.models.Event
import com.intermedia.challenge.databinding.ViewApparitionItemBinding
import com.intermedia.challenge.databinding.ViewEventsItemBinding
import com.intermedia.challenge.ui.base.BaseAdapter
import com.intermedia.challenge.utils.binding.setIsVisible

class EventsAdapter : BaseAdapter<Event, EventsAdapter.EventsAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsAdapterViewHolder =
        EventsAdapterViewHolder(
            ViewEventsItemBinding.bind(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.view_events_item,
                    parent,
                    false
                )
            ), parent.context
        )

    override fun onBindViewHolder(holder: EventsAdapterViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class EventsAdapterViewHolder(
        val binding: ViewEventsItemBinding,
        val context: Context
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Event) = with(itemView) {
            binding.event = item
            item.comics.appearances.forEach { appearance ->
                addComic(appearance)
            }
            binding.buttonExpand.setOnClickListener {
                binding.layoutComics.setIsVisible(binding.buttonExpand.isChecked)
            }
        }

        private fun addComic(appearance: Appearance) {

            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

            val itemBinding: ViewApparitionItemBinding = ViewApparitionItemBinding.inflate(inflater)
            itemBinding.appearance = appearance

            binding.layoutComics.addView(itemBinding.root)

        }

    }
}
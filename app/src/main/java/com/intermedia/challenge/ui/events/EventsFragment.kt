package com.intermedia.challenge.ui.events

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.intermedia.challenge.R
import com.intermedia.challenge.databinding.FragmentEventsBinding
import com.intermedia.challenge.utils.extensions.toast
import org.koin.android.viewmodel.ext.android.sharedViewModel

class EventsFragment : Fragment() {

    private lateinit var binding: FragmentEventsBinding
    private val viewModel: EventsViewmodel by sharedViewModel()
    private val adapter = EventsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEventsBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupPagination()
        setUpEventsList()

    }


    private fun setupPagination() {
        binding.listEvents.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (!recyclerView.canScrollVertically(1)) {
                    viewModel.loadMoreEvents()
                }
                super.onScrollStateChanged(recyclerView, newState)
            }
        })
    }

    private fun setUpEventsList() {
        binding.listEvents.adapter = adapter
        viewModel.event.observe(viewLifecycleOwner, { events ->
            adapter.apply {
                addMissingItems(events, true)
            }

        })
        viewModel.error.observe(viewLifecycleOwner, {
            context?.toast(getString(R.string.unable_to_load))
        })
    }
}
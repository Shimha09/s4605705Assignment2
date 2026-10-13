package com.example.s4605705assignment2.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import com.example.s4605705assignment2.R
import com.example.s4605705assignment2.adapter.ArchitectureAdapter
import com.example.s4605705assignment2.viewmodel.DashboardViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private lateinit var viewModel: DashboardViewModel
    private lateinit var adapter: ArchitectureAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(
            R.layout.fragment_dashboard,
            container,
            false
        )
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView =
            view.findViewById<RecyclerView>(R.id.recyclerView)

        adapter = ArchitectureAdapter { _ ->
            // Details screen navigation will be added next.
        }

        recyclerView.adapter = adapter

        viewModel =
            ViewModelProvider(this)[DashboardViewModel::class.java]

        val keypass =
            arguments?.getString("keypass")

        if (keypass.isNullOrBlank()) {

            Toast.makeText(
                requireContext(),
                "Keypass not found",
                Toast.LENGTH_SHORT
            ).show()

            return
        }

        viewLifecycleOwner.lifecycleScope.launch {

            viewLifecycleOwner.repeatOnLifecycle(
                Lifecycle.State.STARTED
            ) {

                launch {

                    viewModel.entities.collect { entities ->

                        adapter.updateData(entities)
                    }
                }

                launch {

                    viewModel.errorState.collect { error ->

                        if (!error.isNullOrBlank()) {

                            Toast.makeText(
                                requireContext(),
                                error,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }

        viewModel.loadDashboard(keypass)
    }
}
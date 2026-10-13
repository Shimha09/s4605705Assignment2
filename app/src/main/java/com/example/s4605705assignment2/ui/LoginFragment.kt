package com.example.s4605705assignment2.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.s4605705assignment2.R
import com.example.s4605705assignment2.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(
            R.layout.fragment_login,
            container,
            false
        )
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        viewModel =
            ViewModelProvider(this)[LoginViewModel::class.java]

        val etUsername =
            view.findViewById<EditText>(R.id.etUsername)

        val etPassword =
            view.findViewById<EditText>(R.id.etPassword)

        val btnLogin =
            view.findViewById<Button>(R.id.btnLogin)

        val tvError =
            view.findViewById<TextView>(R.id.tvError)

        btnLogin.setOnClickListener {

            val username =
                etUsername.text.toString().trim()

            val password =
                etPassword.text.toString().trim()

            if (username.isBlank() || password.isBlank()) {

                tvError.text =
                    "Please enter Student ID and First Name."

                tvError.visibility = View.VISIBLE

            } else {

                tvError.visibility = View.GONE

                viewModel.login(
                    username,
                    password
                )
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {

            viewLifecycleOwner.repeatOnLifecycle(
                Lifecycle.State.STARTED
            ) {

                launch {

                    viewModel.keypass.collect { keypass ->

                        if (!keypass.isNullOrBlank()) {

                            val bundle = Bundle().apply {
                                putString(
                                    "keypass",
                                    keypass
                                )
                            }

                            viewModel.clearKeypass()

                            findNavController().navigate(
                                R.id.action_loginFragment_to_dashboardFragment,
                                bundle
                            )
                        }
                    }
                }

                launch {

                    viewModel.errorState.collect { error ->

                        if (error.isNullOrBlank()) {

                            tvError.visibility =
                                View.GONE

                        } else {

                            tvError.text = error
                            tvError.visibility =
                                View.VISIBLE
                        }
                    }
                }
            }
        }
    }
}
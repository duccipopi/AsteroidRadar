package com.udacity.asteroidradar.main

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        val adapter = MainViewModel.AsteroidsAdapter(MainViewModel.AsteroidClickListener {
            Toast.makeText(requireContext(), "Clicked in ${it.codename}", Toast.LENGTH_SHORT).show()

            findNavController().navigate(MainFragmentDirections.actionShowDetail(it))
        })
        binding.asteroidRecycler.adapter = adapter

        viewModel.asteroids.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
                binding.notFound.visibility =
                    if (it.isEmpty()) View.VISIBLE else View.GONE
            }
        })

        viewModel.repoStatus.observe(viewLifecycleOwner, Observer {
            it?.let {
                when (it) {
                    Status.LOADING -> {
                        binding.statusLoadingWheel.visibility = View.VISIBLE
                        binding.asteroidRecycler.visibility = View.INVISIBLE
                        binding.notFound.visibility = View.GONE
                    }
                    else -> {
                        binding.statusLoadingWheel.visibility = View.GONE
                        binding.asteroidRecycler.visibility = View.VISIBLE
                        binding.notFound.visibility =
                            if (viewModel.asteroids.value.isNullOrEmpty()) View.VISIBLE else View.GONE
                    }
                }
            }
        })

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return true
    }
}

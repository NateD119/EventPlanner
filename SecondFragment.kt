package com.studentnate.week13demo

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import com.studentnate.week13demo.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

    companion object {
        fun newInstance() = SecondFragment()
    }

    private lateinit var viewModel: SecondViewModel
    private lateinit var binding: FragmentSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SecondViewModel::class.java)
        viewModel.setList(this.requireContext())
        val listObserver = Observer<ArrayList<String>> { list ->
            var adapter = ArrayAdapter(this.requireContext(), android.R.layout.simple_list_item_1, list)
            binding.listView.adapter = adapter

        }

        viewModel.getList().observe(viewLifecycleOwner, listObserver)
    }

}
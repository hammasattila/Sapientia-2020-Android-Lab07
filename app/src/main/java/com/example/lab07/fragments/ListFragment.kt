package com.example.lab07.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab07.R
import com.example.lab07.adapters.MainRecyclerViewAdapter
import com.example.lab07.data.Food
import com.example.lab07.data.FoodViewModel
import com.example.lab07.data.FoodViewModelFactory

class ListFragment : Fragment(), MainRecyclerViewAdapter.OnItemClickListener {

    private lateinit var mFoodViewModel: FoodViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_main)
        val adapter = MainRecyclerViewAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        activity?.let { activity ->
            mFoodViewModel = ViewModelProvider(this, FoodViewModelFactory(activity.application)).get(
                FoodViewModel::class.java)
        }
        mFoodViewModel.allFoods.observe(viewLifecycleOwner, Observer { food ->
            adapter.setData(food)
        })

        return view
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(context, "Item ${position} was clicked.", Toast.LENGTH_SHORT).show()
        findNavController().navigate(ListFragmentDirections.showDetails())
    }
}
package com.example.lab07.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab07.R
import com.example.lab07.adapters.MainRecyclerViewAdapter
import com.example.lab07.data.Food

class ListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_main)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = MainRecyclerViewAdapter(arrayOf(
            Food("Keto Taco Cups", "Cheese shells are all the rage"),
            Food("Cheesy Mexican Cauliflower Rice", "This is a perfect low-carb option that doesn't sacrifice any of the flavor."),
            Food("Turkey Taco Lettuce Wraps", "Who needs tortillas?"),
            Food("Best-Ever Migas", "Breakfast doesn't get much better than this."),
            Food("Spicy Chicken Taquitos", "The avocado cream is a non-negotiable. ")
        ))

        return view
    }
}
package com.example.lab07.fragments

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionInflater
import com.example.lab07.R
import com.example.lab07.adapters.MainRecyclerViewAdapter
import com.example.lab07.data.Food
import com.example.lab07.data.FoodViewModel
import com.example.lab07.data.FoodViewModelFactory

class ListFragment : Fragment(), MainRecyclerViewAdapter.OnItemClickListener {

    private lateinit var mFoodViewModel: FoodViewModel
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mRecyclerViewAdapter: MainRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        // Recycler View
        mRecyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_main)
        mRecyclerViewAdapter = MainRecyclerViewAdapter(this)
        mRecyclerView.layoutManager = LinearLayoutManager(context)
        mRecyclerView.adapter = mRecyclerViewAdapter

        // Animation
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        postponeEnterTransition()
        mRecyclerView.viewTreeObserver.addOnPreDrawListener {
            startPostponedEnterTransition()
            true
        }

        // ViewModel
        activity?.let { activity ->
            mFoodViewModel =
                ViewModelProvider(this, FoodViewModelFactory(activity.application)).get(
                    FoodViewModel::class.java
                )
        }
        mFoodViewModel.allFoods.observe(viewLifecycleOwner, Observer { food ->
            mRecyclerViewAdapter.setData(food)
        })

        return view
    }

    override fun onItemClick(position: Int) {
        val id = mFoodViewModel.allFoods.value?.get(position)?.id
        id?.let { foodId ->
            val holder = mRecyclerView.findViewHolderForAdapterPosition(position)
            val action = ListFragmentDirections.showDetails(foodId)
            val extras = holder?.let {
                FragmentNavigatorExtras(
                    it.itemView.findViewById<ImageView>(R.id.item_food_image) to "food_image_$id",
                    it.itemView.findViewById<TextView>(R.id.item_food_text_description) to "food_description_$id"
                )
            }

            if (extras != null) {
                findNavController().navigate(action, extras)
            } else {
                findNavController().navigate(action)
            }
        }
    }

    override fun onItemLongClick(position: Int) {
        Toast.makeText(context, "Item $position was long clicked.", Toast.LENGTH_SHORT).show()
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Are you sure you want to vaporize this food?")
            .setMessage("Blabla is going to be deleted from the local database. If you want to continue click VAPORIZE")
            .setPositiveButton("Vaporize", DialogInterface.OnClickListener { dialog, which ->

            })
            .setNegativeButton("Cancel", null)
            .show()
    }
}
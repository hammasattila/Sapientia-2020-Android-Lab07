package com.example.lab07.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.example.lab07.R
import com.example.lab07.data.FoodViewModel
import com.example.lab07.data.FoodViewModelFactory

class DetailsFragment : Fragment() {

    private val args: DetailsFragmentArgs by navArgs()
    private lateinit var mFoodViewModel: FoodViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_details, container, false)

        // Navigation animation
        val foodImage = view.findViewById<ImageView>(R.id.details_food_image)
        foodImage.transitionName = "food_image_${args.foodId}"
        val desc = view.findViewById<TextView>(R.id.details_food_text_description)
        desc.transitionName = "food_description_${args.foodId}"
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)

        activity?.let { activity ->
            mFoodViewModel = ViewModelProvider(this, FoodViewModelFactory(activity.application)).get(
                FoodViewModel::class.java)
        }
        mFoodViewModel.getFood(args.foodId).observe(viewLifecycleOwner, Observer { food -> desc.text = food.description })

        return view
    }
}
package com.example.lab07.fragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.lab07.R
import com.example.lab07.data.Food
import com.example.lab07.data.FoodViewModel
import com.example.lab07.data.FoodViewModelFactory

class AddFoodFragment : Fragment() {

    private lateinit var mFoodViewModel: FoodViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_food, container, false)


        activity?.let { activity ->
            mFoodViewModel = ViewModelProvider(this, FoodViewModelFactory(activity.application)).get(FoodViewModel::class.java)
        }

        view.findViewById<Button>(R.id.button_new_food_add).setOnClickListener {
            insertFoodToDatabase()
        }

        return view
    }

    private fun insertFoodToDatabase() {
        val name = view?.findViewById<TextView>(R.id.text_edit_new_food_name)?.text.toString()
        val desc = view?.findViewById<TextView>(R.id.text_edit_new_food_description)?.text.toString()

        if(inputCheck(name, desc)) {
            val food = Food(0, name, desc)
            mFoodViewModel.addFood(food)
            Toast.makeText(context, "$name successfully added.", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Fill out all fields!", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(name: String, desc: String): Boolean {
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(desc))
    }
}
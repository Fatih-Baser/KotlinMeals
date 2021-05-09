package com.fatihbaser.kotlinmeals.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.fatihbaser.kotlinmeals.R
import com.fatihbaser.kotlinmeals.ViewModel.MealViewModel
import com.fatihbaser.kotlinmeals.util.downloadFromUrl
import com.fatihbaser.kotlinmeals.util.placeholderProgressBar
import kotlinx.android.synthetic.main.fragment_meals_detail.*
import kotlinx.android.synthetic.main.item_meal.*


class MealsDetailFragment : Fragment() {

    private lateinit var viewModel:MealViewModel
    private var mealUuid=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meals_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            mealUuid=MealsDetailFragmentArgs.fromBundle(it ).mealsUiuid
        }
        viewModel=ViewModelProviders.of(this).get(MealViewModel::class.java)
        viewModel.getDataFromRoom(mealUuid)

        observeLiveData()
    }

    private fun observeLiveData(){

        viewModel.mealLiveData.observe(viewLifecycleOwner, Observer { meal->
            meal?.let {
                mealName.text=meal.mealName
                malzemelerTextView.text=meal.malzemelerName
                tarifiTextView.text=meal.tarifi
                context?.let {
                    mealImage.downloadFromUrl(meal.resim, placeholderProgressBar(it))

                }
            }
        })
    }

    }

package com.fatihbaser.kotlinmeals.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.fatihbaser.kotlinmeals.R
import com.fatihbaser.kotlinmeals.ViewModel.FeedViewModel
import com.fatihbaser.kotlinmeals.adapter.MealAdapter
import kotlinx.android.synthetic.main.fragment_feed.*

class FeedFragment : Fragment() {

    private lateinit var viewModel:FeedViewModel
    private val  mealAdapter=MealAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel=ViewModelProviders.of(this).get(FeedViewModel::class.java)
        viewModel.refleshData()

        mealsList.layoutManager=LinearLayoutManager(context)
        mealsList.adapter=mealAdapter

        observeLiveData()
    }
    private fun observeLiveData(){
        viewModel.meals.observe(viewLifecycleOwner, Observer { meals->
            meals?.let {
                mealsList.visibility=View.VISIBLE
              mealAdapter.updateMealList(meals)
            }

        })
        viewModel.mealError.observe(viewLifecycleOwner, Observer {meal->
            meal?.let {
                if(it){
                    mealError.visibility=View.VISIBLE
                }else  {
                    mealError.visibility=View.GONE
                }
            }

        })
        viewModel.mealLoading.observe(viewLifecycleOwner, Observer { loading->
            loading?.let {
                if(it){
                    mealsList.visibility=View.GONE
                    mealLoading.visibility=View.VISIBLE
                    mealError.visibility=View.GONE
                }else  {
                    mealLoading.visibility=View.GONE
                }

            }
        })
    }


    }
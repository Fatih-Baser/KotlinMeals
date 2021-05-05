package com.fatihbaser.kotlinmeals.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.fatihbaser.kotlinmeals.model.Meal
import com.fatihbaser.kotlinmeals.R
import com.fatihbaser.kotlinmeals.View.FeedFragmentDirections
import com.fatihbaser.kotlinmeals.databinding.ItemMealBinding
import kotlinx.android.synthetic.main.item_meal.view.*

class MealAdapter(val mealList: ArrayList<Meal> ): RecyclerView.Adapter<MealAdapter.MealViewHolder>(),MealClickListener{

    class MealViewHolder(var view: ItemMealBinding) : RecyclerView.ViewHolder(view.root){


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {//

        val inflater=LayoutInflater.from(parent.context)
        //val view =inflater.inflate(R.layout.item_meal,parent,false)
        val view=DataBindingUtil.inflate<ItemMealBinding>(inflater,R.layout.item_meal,parent,false)
        return MealViewHolder(view)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {

        holder.view.meals=mealList[position]
        holder.view.listener=this
        /*holder.view.name.text=mealList[position].mealName

        holder.view.setOnClickListener{
            val action=FeedFragmentDirections.actionFeedFragmentToMealsDetailFragment(mealList[position].uuid)

            Navigation.findNavController(it).navigate(action)
        }
        holder.view.imageView.downloadFromUrl(mealList[position].resim, placeholderProgressBar(holder.view.context))*/


    }

    override fun getItemCount(): Int {
       return mealList.size
    }

    fun updateMealList(newMealList:List<Meal>){
        mealList.clear()
        mealList.addAll(newMealList )
        notifyDataSetChanged()
    }

    override fun onMelaClicked(v: View) {
        val uuid = v.mealUuidText.text.toString().toInt()
        val action= FeedFragmentDirections.actionFeedFragmentToMealsDetailFragment(uuid)

        Navigation.findNavController(v).navigate(action)
    }
}
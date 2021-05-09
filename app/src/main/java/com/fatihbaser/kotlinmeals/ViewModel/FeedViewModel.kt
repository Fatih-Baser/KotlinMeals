package com.fatihbaser.kotlinmeals.ViewModel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fatihbaser.kotlinmeals.Model.Meal
import com.fatihbaser.kotlinmeals.service.MealAPI
import com.fatihbaser.kotlinmeals.service.MealAPIService
import com.fatihbaser.kotlinmeals.service.MealDatabase
import com.fatihbaser.kotlinmeals.util.CustomSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

//observable =gozlemlenebilir
//observer =gozlemci
//Mutable =degistirilebilir
class FeedViewModel(application: Application) :BaseViewModel(application){

    private val mealApiService=MealAPIService()
    private val disposable=CompositeDisposable()//veri indridikdikce disposible atiyoruz(Hafizayi verimli kullanmak icin)
    private var customPreferences=CustomSharedPreferences(getApplication())
    private var refreshTime = 10 * 60 * 1000 * 1000 * 1000L


    val meals= MutableLiveData<List<Meal>>()
    val mealError=MutableLiveData<Boolean>()
    val mealLoading=MutableLiveData<Boolean>()

    fun refleshData(){
        val updateTime = customPreferences.getTime()
        if (updateTime != null && updateTime != 0L && System.nanoTime() - updateTime < refreshTime) {
            getDataFromSQLite()
        } else {
            getDataFromAPI()
        }

    }
    fun refreshFromAPI() {
        getDataFromAPI()
    }
    private fun getDataFromSQLite() {
        mealLoading.value = true
        launch {
            val countries = MealDatabase(getApplication()).mealDao().getAllCountries()
            showMeals(countries)
            Toast.makeText(getApplication(),"Countries From SQLite",Toast.LENGTH_LONG).show()
        }
    }
    private fun getDataFromAPI(){

        mealLoading.value = true

        disposable.add(
                mealApiService.getData()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object : DisposableSingleObserver<List<Meal>>(){
                            override fun onSuccess(t: List<Meal>) {

                                storeInSqLite(t)
                                Toast.makeText(getApplication(),"Countries From API",Toast.LENGTH_LONG).show()

                            }

                            override fun onError(e: Throwable) {
                                mealLoading.value = false
                                mealError.value = true
                                e.printStackTrace()
                            }

                        })
        )
    }

    private fun  showMeals(mealList: List<Meal>){
        meals.value=mealList
        mealLoading.value = false
        mealError.value = false
    }
    private fun  storeInSqLite(list: List<Meal>){

        launch {
            val dao=MealDatabase(getApplication()).mealDao()
            dao.deleteAllCountries()
           val listLong= dao.insertAll(*list.toTypedArray())
            var i=0
             while (i<list.size){
                 list[i].uuid=listLong[i].toInt()
                 i=i+1
             }
            showMeals(list)

        }
        customPreferences.saveTime(System.nanoTime())

    }
    override fun onCleared() {
        super.onCleared()

        disposable.clear()
    }
}
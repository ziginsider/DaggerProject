package io.github.ziginsider.daggerproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import io.github.ziginsider.daggerproject.Utils.toast
import io.github.ziginsider.daggerproject.adapter.RecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_main.*
//import com.squareup.picasso.Picasso
import io.github.ziginsider.daggerproject.application.RandomUserApplication
//import io.github.ziginsider.daggerproject.dagger.ContextModule
import io.github.ziginsider.daggerproject.dagger.DaggerMainActivityComponent
//import io.github.ziginsider.daggerproject.dagger.DaggerRandomUserComponent
import io.github.ziginsider.daggerproject.dagger.MainActivityModule
import timber.log.Timber
import io.github.ziginsider.daggerproject.model.RandomUsers
import io.github.ziginsider.daggerproject.service.RandomUserApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var recyclerAdapter: RecyclerViewAdapter

    //private lateinit var picasso: Picasso

    @Inject
    lateinit var randomUserApi: RandomUserApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //afterDagger()
        afterActivityLevelComponent()
    }

    private fun afterActivityLevelComponent() {
        val mainActivityComponent = DaggerMainActivityComponent.builder()
                .mainActivityModule(MainActivityModule(R.layout.list_item,
                        { toast("I'm ${it.name.first} ${it.name.last}") }))
                .randomUserComponent(RandomUserApplication.get(this).randomUserApplicationComponent)
                .build()
        mainActivityComponent.injectMainActivity(this)
        initViews()
        populateUsers()

    }

//    private fun afterDagger() {
//        val daggerRandomUserComponent = DaggerRandomUserComponent.builder()
//                .contextModule(ContextModule(this))
//                .build()
//        picasso = daggerRandomUserComponent.getPicasso()
//        initViews()
//        randomUserApi = daggerRandomUserComponent.getRandomUserService()
//        populateUsers()
//    }


    private fun initViews() {
        with(recyclerView) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = recyclerAdapter
        }
    }

    private fun populateUsers() {
        val randomUsersCall = getRandomUserService().getRandomUsers(20)
        randomUsersCall.enqueue(object : Callback<RandomUsers> {
            override fun onFailure(call: Call<RandomUsers>?, t: Throwable?) {
                Timber.i(t!!.message)
            }

            override fun onResponse(call: Call<RandomUsers>?, response: Response<RandomUsers>?) {
                if (response!!.isSuccessful) {
                    recyclerAdapter.submitList(response.body()?.results)
                }
            }
        })
    }

    private fun getRandomUserService() = randomUserApi
}

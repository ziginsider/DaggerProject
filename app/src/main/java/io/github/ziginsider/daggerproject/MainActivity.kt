package io.github.ziginsider.daggerproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import io.github.ziginsider.daggerproject.Utils.toast
import io.github.ziginsider.daggerproject.adapter.RecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_main.*
import com.google.gson.GsonBuilder
import com.jakewharton.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import io.github.ziginsider.daggerproject.dagger.ContextModule
import io.github.ziginsider.daggerproject.dagger.DaggerRandomUserComponent
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import io.github.ziginsider.daggerproject.model.RandomUsers
import io.github.ziginsider.daggerproject.service.RandomUserApi
import okhttp3.Cache
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class MainActivity : AppCompatActivity() {

    private var recyclerAdapter: RecyclerViewAdapter? = null
    private var retrofit: Retrofit? = null
    private var picasso: Picasso? = null
    private var randomUserApi: RandomUserApi? = null

//    @Inject lateinit var randomUserApi: RandomUserApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        afterDagger()




        //witoutDagger()
    }

    private fun afterDagger() {
        val daggerRandomUserComponent = DaggerRandomUserComponent.builder()
                .contextModule(ContextModule(this))
                .build()
        picasso = daggerRandomUserComponent.getPicasso()
        initViews()
        randomUserApi = daggerRandomUserComponent.getRandomUserService()
        populateUsers()
    }

    private fun witoutDagger() {
        val gsonBuilder = GsonBuilder()
        val gson = gsonBuilder.create()

        Timber.plant(Timber.DebugTree())

        val cacheFile = File(this.cacheDir, "HttpCache")
        cacheFile.mkdirs()

        val cache = Cache(cacheFile, 10 * 1000 * 1000)

        val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { msg ->
            Timber.i(msg)
        })

        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient()
                .newBuilder()
                .cache(cache)
                .addInterceptor(httpLoggingInterceptor)
                .build()

        val okHttpDownloader = OkHttp3Downloader(okHttpClient)

        picasso = Picasso.Builder(this).downloader(okHttpDownloader).build()

        initViews()

        retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://randomuser.me/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        populateUsers()
    }

    private fun initViews() {
        recyclerAdapter = RecyclerViewAdapter(R.layout.list_item, picasso!!,
                { toast("I'm ${it.name.first} ${it.name.last}") })
        with(recyclerView) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = recyclerAdapter
        }
    }

    private fun populateUsers() {
        val randomUsersCall = getRandomUserService()?.getRandomUsers(20)
        randomUsersCall?.enqueue(object: Callback<RandomUsers> {
            override fun onFailure(call: Call<RandomUsers>?, t: Throwable?) {
                Timber.i(t!!.message)
            }

            override fun onResponse(call: Call<RandomUsers>?, response: Response<RandomUsers>?) {
                if (response!!.isSuccessful) {
                    recyclerAdapter?.submitList(response.body()?.results)
                }
            }
        })
    }

//    private fun getRandomUserService(): RandomUserApi {
//        return retrofit!!.create(RandomUserApi::class.java)
//    }

    fun getRandomUserService() = randomUserApi
}

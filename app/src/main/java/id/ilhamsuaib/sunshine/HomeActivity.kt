package id.ilhamsuaib.sunshine

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log.*
import com.google.gson.Gson
import id.ilhamsuaib.sunshine.model.Forcast
import id.ilhamsuaib.sunshine.model.ForcastResponse
import id.ilhamsuaib.sunshine.model.Weather
import kotlinx.android.synthetic.main.activity_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {

    private val tag = this::class.java.simpleName

    //https://api.openweathermap.org/data/2.5/forecast?id=1621177&appid=91ffcf633b4369135adf753d37304ba6
    //91ffcf633b4369135adf753d37304ba6

    private lateinit var adapter: WeatherAdapter
    private val forcastList = mutableListOf<Forcast>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setupView()
        getData()
    }

    private fun setupView() {
        adapter = WeatherAdapter(forcastList)
        recCuaca.layoutManager = LinearLayoutManager(this)
        recCuaca.adapter = adapter
    }

    private fun getData() {
        App.api.getForcast().enqueue(object : Callback<ForcastResponse> {
            override fun onFailure(call: Call<ForcastResponse>?, t: Throwable?) {
                e(tag, t?.message)
            }

            override fun onResponse(call: Call<ForcastResponse>?, response: Response<ForcastResponse>?) {
                i(tag, "data : ${Gson().toJsonTree(response?.body())}")
                val nWeatherList = response?.body()?.forcastList
                nWeatherList?.let {
                    forcastList.addAll(it)
                    adapter.notifyDataSetChanged()
                }
            }
        })
    }
}

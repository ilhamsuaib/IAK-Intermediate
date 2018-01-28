package id.ilhamsuaib.sunshine

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log.*
import com.google.gson.Gson
import id.ilhamsuaib.sunshine.model.Forcast
import id.ilhamsuaib.sunshine.model.ForcastResponse
import kotlinx.android.synthetic.main.activity_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {

    private val tag = this::class.java.simpleName

    private lateinit var adapter: ForcastAdapter
    private val forcastList = mutableListOf<Forcast>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setupView()
        getData()
    }

    private fun setupView() {
        adapter = ForcastAdapter(forcastList)
        recForcast.layoutManager = LinearLayoutManager(this)
        recForcast.adapter = adapter
    }

    private fun getData() {
        App.api.getForcast().enqueue(object : Callback<ForcastResponse> {
            override fun onFailure(call: Call<ForcastResponse>?, t: Throwable?) {
                e(tag, t?.message)
            }

            override fun onResponse(call: Call<ForcastResponse>?, response: Response<ForcastResponse>?) {
                i(tag, "data : ${Gson().toJsonTree(response?.body())}")
                val nForcastList = response?.body()?.forcastList?
                nForcastList?.let {
                    forcastList.addAll(it)
                    adapter.notifyDataSetChanged()
                }
            }
        })
    }
}

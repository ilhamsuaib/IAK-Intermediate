package id.ilhamsuaib.sunshine

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.ilhamsuaib.sunshine.model.Forcast

/**
 * Created by ilham on 1/27/18.
 */

class WeatherAdapter(private val weatherList: List<Forcast>) : RecyclerView.Adapter<WeatherAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): Holder {
        val view: View? = LayoutInflater.from(parent?.context)
                .inflate(R.layout.item_weather, parent,false)
        return Holder(view)
    }

    override fun getItemCount(): Int = weatherList.size

    override fun onBindViewHolder(holder: Holder?, position: Int) {
        holder?.bind(position)
    }

    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {
            val forcast = weatherList[position]
            TODO("tampilkan data ke dalam UI list")
        }
    }
}
package id.ilhamsuaib.sunshine

import android.annotation.SuppressLint
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.util.Log.i
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import id.ilhamsuaib.sunshine.model.Forcast
import id.ilhamsuaib.sunshine.model.Weather
import kotlinx.android.synthetic.main.item_forcast.view.*

/**
 * Created by ilham on 1/27/18.
 */

class ForcastAdapter(val forcastList: List<Forcast>) : RecyclerView.Adapter<ForcastAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): Holder {
        val view: View? = LayoutInflater.from(parent?.context)
                .inflate(R.layout.item_forcast, parent,false)
        return Holder(view)
    }

    override fun getItemCount(): Int = forcastList.size

    override fun onBindViewHolder(holder: Holder?, position: Int) {
        holder?.bind(position)
    }

    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(position: Int) {
            val forcast: Forcast = forcastList[position]
            i("adapter", "forcast at $position : "+ Gson().toJsonTree(forcast))
            if (forcast.weather?.isNotEmpty() == true) {
                val weather: Weather = forcast.weather[0]
                itemView?.tvWaktu?.text = forcast.dtTxt
                val degreeInCel = forcast.main?.temp?.toDouble()?.minus(273)
                itemView?.tvDerajatSuhu?.text = "${degreeInCel?.toInt()}\u00B0"
                itemView?.tvStatus?.text = weather.description
                //mengambil icon berdasarkan id weather
                val icon: Int  = getIcon(weather.id?.toInt()?:0)
                println("weather id : ${weather.id}")
                itemView?.imgStatus?.setImageDrawable(
                        ContextCompat.getDrawable(itemView.context, icon)
                )
            }
        }
    }
}
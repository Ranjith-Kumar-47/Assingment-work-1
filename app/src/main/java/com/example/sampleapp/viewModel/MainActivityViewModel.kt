package com.example.sampleapp.viewModel

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.sampleapp.R
import com.example.sampleapp.instance.Instance
import com.example.sampleapp.model.Country
import com.example.sampleapp.model.CountryModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityViewModel : ViewModel() {
    var userName: String? = null
    private var countryModel: CountryModel? = null
    private var country: List<Country>? = null
    private val apiCall = Instance.getInstance()
    fun getData(progressBar: ProgressBar, linearLayout: LinearLayout, context: Context) {
        if (userName!=null) {
            progressBar.visibility = View.VISIBLE
            GlobalScope.launch(Dispatchers.IO) {
                if (apiCall.getCountryData(userName!!).isSuccessful) {
                    countryModel = apiCall.getCountryData(userName!!).body()
                    country = countryModel?.country
                    withContext(Dispatchers.Main) {
                        progressBar.visibility = View.GONE
                        var view: View?
                        linearLayout.removeAllViews()
                        for (i in 0 until country!!.size) {
                            view = LayoutInflater.from(context)
                                .inflate(R.layout.single_card_design, null, false)
                            val tvCountryId = view.findViewById<TextView>(R.id.tvCountryId)
                            val tvProbability = view.findViewById<TextView>(R.id.tvProbability)
                            tvCountryId.text = country!![i].country_id.toString()
                            tvProbability.text = country!![i].probability.toString()
                            linearLayout.addView(view)

                        }
                        if (country!!.isEmpty()) Toast.makeText(
                            context, "no data is present", Toast.LENGTH_SHORT
                        ).show()

                    }
                }
            }
        } else Toast.makeText(context, "Enter the User Name!", Toast.LENGTH_SHORT).show()
    }

}

package com.test_app.open_weater.ui.main

import android.app.AlertDialog
import android.content.Context
import android.opengl.Visibility
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.test_app.open_weater.R
import com.test_app.open_weater.databinding.MainFragmentBinding
import com.test_app.open_weater.models.rest.WeatherDTO
import com.test_app.open_weater.viewmodels.AppState
import com.test_app.open_weater.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*

class MainFragment : Fragment() {
    private lateinit var binding: MainFragmentBinding
    private var data: WeatherDTO ?= null
    private val viewModel: MainViewModel by  viewModel()

//    private val viewModel by lazy {
//        ViewModelProvider(this).get(MainViewModel::class.java)
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(viewLifecycleOwner, { render(it as AppState)})
        getDataFromDisc()
        binding.sendBtn.setOnClickListener{
            if(!binding.cityNameEditText.text.isNullOrBlank()){
                viewModel.getDataFromApi(binding.cityNameEditText.text.toString())
                binding.cityNameEditText.text = null
            } else{
                createAlertDialog(R.string.title_failed_to_download_dialog,R.string.message_city_is_empty)
            }
        }
    }

    private fun getDataFromDisc() {
       activity?.let {
           val json = it.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)
               .getString(namePropertyInstantWeatherDTO, "")
           data = Gson().fromJson(json, WeatherDTO ::class.java)
       }
        if (data != null){
            initView()
        }
    }

    private fun render(appState: AppState) {
        when(appState){
            is AppState.Loading -> binding.loadingPage.visibility = View.VISIBLE
            is AppState.Success -> {
                data = appState.data
                saveDataToSharedPreference(data)
                initView()
            }
            is AppState.Error -> {
                binding.loadingPage.visibility = View.GONE
                createAlertDialog(R.string.title_failed_to_download_dialog, R.string.message_failed_to_download_dialog)
            }
        }

    }


    private fun initView() {
        binding.apply {
            loadingPage.visibility = View.GONE
            locationInput.text = data?.location
            temperatureInput.text = "${data?.main?.temp} C"
            windSpeed.text = "${data?.windSpeed?.speed} m/sec"
            humidity.text = "${data?.main?.humidity} %"
            visibility.text = data?.visibility
            sunrise.text = timeFormat(data?.sunset?.sunrise)
            sunset.text = timeFormat(data?.sunset?.sunset)
        }
    }

    private fun saveDataToSharedPreference(data : WeatherDTO?) {
        activity?.let{
           val preference = it.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)
           val editor = preference.edit()
           val gson = Gson().toJson(data)
           editor.putString(namePropertyInstantWeatherDTO, gson)
           editor.apply()
        }
    }
    private fun createAlertDialog(title: Int, message: Int){
        val builder = AlertDialog.Builder(context)
        builder.setTitle(getString(title))
            .setMessage(getString(message))
            .setPositiveButton("Ok") { dialog, id ->
                dialog.cancel()
            }
        builder.show()
    }


    companion object {
        fun newInstance() = MainFragment()
        fun timeFormat(date : String?): String {
            val time = date?.toLong()?.let { Date(it * 1000)}
            return SimpleDateFormat("M/dd/yyyy h:mm : a").format(time)
        }
        const val preferenceName = "Change Adult Content"
        const val namePropertyInstantWeatherDTO = "Location_Data"
    }
}

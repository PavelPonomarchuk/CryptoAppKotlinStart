package ru.ponomarchukpn.cryptoapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.loadData()
        viewModel.priceList.observe(this) {
            Log.d("TEST_OF_LOADING_DATA", "Success in activity: $it")
        }
    }
}
package ru.ponomarchukpn.cryptoapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import ru.ponomarchukpn.cryptoapp.adapters.CoinInfoAdapter
import ru.ponomarchukpn.cryptoapp.pojo.CoinPriceInfo

class CoinPriceListActivity : AppCompatActivity() {
    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_price_list)

        val rvCoinPriceList: RecyclerView = findViewById(R.id.rvCoinPriceList)
        val adapter = CoinInfoAdapter(this)
        rvCoinPriceList.adapter = adapter

        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinPriceInfo: CoinPriceInfo) {
                Log.d("ON_CLICK_TEST", coinPriceInfo.fromSymbol)
            }
        }

        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.priceList.observe(this) {
            adapter.coinInfoList = it
        }
    }
}
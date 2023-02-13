package ru.ponomarchukpn.cryptoapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso

class CoinDetailActivity : AppCompatActivity() {
    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_detail)

        if (!intent.hasExtra(EXTRA_FROM_SYMBOL)) {
            finish()
            return
        }

        val ivLogoCoin: ImageView = findViewById(R.id.ivLogoCoinDetails)
        val tvFromSymbol: TextView = findViewById(R.id.tvFromSymbol)
        val tvToSymbol: TextView = findViewById(R.id.tvToSymbol)
        val tvPrice: TextView = findViewById(R.id.tvPriceDetail)
        val tvDayMin: TextView = findViewById(R.id.tvDayMin)
        val tvDayMax: TextView = findViewById(R.id.tvDayMax)
        val tvLastContract: TextView = findViewById(R.id.tvLastContract)
        val tvUpdated: TextView = findViewById(R.id.tvUpdated)

        val priceTemplate = getString(R.string.price_template)
        val dayMinTemplate = getString(R.string.day_min_template)
        val dayMaxTemplate = getString(R.string.day_max_template)
        val lastContractTemplate = getString(R.string.last_contract_template)
        val updatedTemplate = getString(R.string.updated_template)

        val fromSymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL).toString()
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.getDetailInfo(fromSymbol).observe(this) {
            Picasso.get().load(it.getFullImageUrl()).into(ivLogoCoin)
            tvFromSymbol.text = it.fromSymbol
            tvToSymbol.text = it.toSymbol
            tvPrice.text = String.format(priceTemplate, it.price)
            tvDayMin.text = String.format(dayMinTemplate, it.lowDay)
            tvDayMax.text = String.format(dayMaxTemplate, it.highDay)
            tvLastContract.text = String.format(lastContractTemplate, it.lastMarket)
            tvUpdated.text = String.format(updatedTemplate, it.getFormattedTime())
        }
    }

    companion object {
        private const val EXTRA_FROM_SYMBOL = "fSym"

        fun newIntent(context: Context, fromSymbol: String): Intent {
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_FROM_SYMBOL, fromSymbol)
            return intent
        }
    }
}
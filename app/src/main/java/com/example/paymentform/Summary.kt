package com.example.paymentform

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.paymentform.databinding.ActivitySummaryBinding
import java.text.NumberFormat
import kotlin.random.Random

class Summary : AppCompatActivity() {

    private lateinit var binding: ActivitySummaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivitySummaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mail = intent.extras?.getString("mail")
        val owner = intent.extras?.getString("owner")
        val paymentMethod = intent.extras?.getString("paymentMethod")
        val cardNumber = intent.extras?.getString("cardNumber")
        val price = intent.extras?.getInt("price")

        binding.textViewMail.text = mail
        binding.textViewCardNumber.text = cardNumber
        binding.textViewPaymentMethod.text = paymentMethod
        binding.textViewOwner.text = owner

        val priceString = NumberFormat.getCurrencyInstance().format(price)
        binding.textViewTotalPrice.text = priceString

        if (Random.nextInt(1, 4) == 1) {
            binding.imageView.setImageResource(R.mipmap.ic_wrong)
        } else {
            binding.imageView.setImageResource(R.mipmap.ic_success)
        }

    }
}
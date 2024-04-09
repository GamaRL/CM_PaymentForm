package com.example.paymentform

import android.graphics.Color
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
        val lastCardNumbers = intent.extras?.getString("cardNumber")?.substring(12)
        val price = intent.extras?.getInt("price")

        val cardNumber = "************$lastCardNumbers"

        binding.textViewMail.text = mail
        binding.textViewCardNumber.text = cardNumber
        binding.textViewPaymentMethod.text = paymentMethod
        binding.textViewOwner.text = owner

        val priceString = NumberFormat.getCurrencyInstance().format(price)
        binding.textViewTotalPrice.text = priceString

        if (Random.nextInt(1, 4) == 1) {
            binding.imageView.setImageResource(R.mipmap.ic_wrong)
            binding.textViewStatus.text = getString(R.string.fail)
            binding.textViewStatus.setTextColor(Color.RED)
        } else {
            binding.imageView.setImageResource(R.mipmap.ic_success)
            binding.textViewStatus.text = getString(R.string.success)
            binding.textViewStatus.setTextColor(Color.GREEN)
        }

        binding.imageViewBack.setOnClickListener{
            finish()
        }
    }


}
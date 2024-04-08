package com.example.paymentform

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.paymentform.databinding.ActivityMainBinding
import java.text.NumberFormat
import java.time.LocalDate
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var price: Int = Random.nextInt(100, 5000)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val priceString = NumberFormat.getCurrencyInstance().format(price)
        binding.textViewTotalPrice.text = priceString

        binding.buttonSend.setOnClickListener {
            onSubmit()
        }

    }

    private fun validateCvv(cvv : String) : Boolean {
        return "^\\d{3,4}\$".toRegex().matches(cvv)
    }

    private fun validateCardNumber(cardNumber: String): Boolean {
        return "^\\d{16}\$".toRegex().matches(cardNumber)
    }

    private fun validateValidUntil(validUntil: String): Boolean {
        if ("\\d{2}/\\d{2}".toRegex().matches(validUntil)) {
            val split = validUntil.split("/")
            val month = split[0].toInt()
            val year = split[1].toInt() + 2000

            if (month in 1 .. 12)
            {
                if (if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        LocalDate.of(year, month, 1).plusMonths(1).isAfter(LocalDate.now())
                    } else {
                        TODO("VERSION.SDK_INT < O")
                    }
                ) {
                    return true;
                }
            }
        }
        return false
    }

    private fun validateMail(mail: String) : Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(mail).matches()
    }

    private fun validateOwner(name: String): Boolean {
        return "^[A-Z][A-Za-z]+(\\s[A-Z][A-Za-z]+)+\$".toRegex().matches(name)
    }

    private fun onSubmit() {
        val cvv = binding.editCvv.text.toString()
        val cardNumber = binding.editTextCardNumber.text.toString()
        val validUntil = binding.editValidUntil.text.toString();
        val mail = binding.editTextMail.text.toString();
        val paymentMethod = binding.spinnerPayment.selectedItem.toString()
        val owner = binding.editTextOwner.text.toString()

        var hasErrors = false

        if (!validateCardNumber(cardNumber)) {
            binding.editTextCardNumber.error = getString(R.string.invalid_card)
            hasErrors = true
        }
        if (!validateValidUntil(validUntil)) {
            binding.editValidUntil.error = getString(R.string.invalid_until)
            hasErrors = hasErrors.or(true)
        }
        if (!validateCvv(cvv)) {
            binding.editCvv.error = getString(R.string.invalid_cvv)
            hasErrors = hasErrors.or(true)
        }
        if (!validateMail(mail)) {
            binding.editTextMail.error = getString(R.string.invalid_mail)
            hasErrors = hasErrors.or(true)
        }
        if(!validateOwner(owner)) {
            binding.editTextMail.error = getString(R.string.invalid_name)
            hasErrors = hasErrors.or(true)
        }

        if (!hasErrors) {

            val intent = Intent(this, Summary::class.java)

            intent.putExtra("cardNumber", cardNumber)
            intent.putExtra("mail", mail)
            intent.putExtra("paymentMethod", paymentMethod)
            intent.putExtra("owner", owner)
            intent.putExtra("price", price)

            startActivity(intent)
        }

    }
}
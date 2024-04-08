package com.example.paymentform

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.paymentform.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var datasource: ArrayList<String>
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var paymentMethodAdapter: PaymentMethodAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val price = Random.nextInt(100, 5000)
        val priceString = NumberFormat.getCurrencyInstance().format(price)
        binding.textViewTotalPrice.text = priceString

        datasource = ArrayList()
        datasource.add("Visa")
        datasource.add("Mastercard")
        datasource.add("American Express")

        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        paymentMethodAdapter = PaymentMethodAdapter(datasource)


    }

    internal class PaymentMethodAdapter(private val data: List<String>) :
        RecyclerView.Adapter<PaymentMethodAdapter.PaymentMethodHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentMethodHolder {
            val itemBinding = LayoutInflater.from(parent.context).inflate(R.layout.payment_method_item, parent, false)
            return PaymentMethodHolder(itemBinding)
        }

        override fun onBindViewHolder(holder: PaymentMethodHolder, position: Int) {
            holder.textViewTitle.text = data[position]
        }

        override fun getItemCount(): Int {
            return data.size
        }

        internal class PaymentMethodHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var textViewTitle: TextView

            init {
                textViewTitle = itemView.findViewById(R.id.textViewTitle)
            }
        }
    }}
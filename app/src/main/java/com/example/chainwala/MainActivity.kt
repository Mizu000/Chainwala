package com.example.chainwala

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.chainwala.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var arrChainList = arrayOf("Kamal","Sehensha","Ball Chain","Box Chain","Rodking",
        "Press Chain","Crop Chain","Noli","Chaki Pech","Noli Pech")

    private var chainMelting = 75.0
     var chainType = "Kamal"
    //
    private var goldMeltingPayment = "99.5"
    //
    companion object{

    }

    private lateinit var bind:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        //
        //
        bind.rgMelting.setOnCheckedChangeListener { _, i ->

            when(i)
            {

                R.id.rbtnMelting75 -> chainMelting = 75.0
                R.id.rbtnMelting83 -> chainMelting = 83.5
                R.id.rbtnMelting92 -> chainMelting = 91.7

            }
            showChainTypeAndMelting()
        }

        //
        bind.spinnerChainType.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,arrChainList)
        bind.spinnerChainType.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                chainType = arrChainList[position]
                showChainTypeAndMelting()


            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

                chainType = "Kamal"
                showChainTypeAndMelting()

            }

        }
        //

        bind.rgGoldMelting.setOnCheckedChangeListener { _, i ->

            when(i)
            {

                R.id.rbtnGoldMelting99_5   -> goldMeltingPayment = "99.5"
                R.id.rbtnGoldMelting100    -> goldMeltingPayment = "100"
                R.id.rbtnGoldMeltingCustom -> goldMeltingPayment = "Tunch"
                R.id.rbtnGoldMeltingCash   -> goldMeltingPayment = "Cash"

            }
        }
        //

    }
    ///
    fun showChainTypeAndMelting()
    {

        if(chainMelting==91.7)
        {
            bind.txtChainName.text = "$chainType (${chainMelting+0.3})"
        }
        else
        {
            bind.txtChainName.text = "$chainType ($chainMelting)"
        }

    }
    //



}
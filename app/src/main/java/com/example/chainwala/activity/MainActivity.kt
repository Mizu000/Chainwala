package com.example.chainwala.activity

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.chainwala.R
import com.example.chainwala.databinding.ActivityMainBinding
import com.example.chainwala.modal.BillDetails

class MainActivity : AppCompatActivity() {

    var arrChainList = arrayOf("Kamal","Sehensha","Ball Chain","Crop Chain","Box Chain","Rodking",
        "Press Chain","Noli","Chaki Pech","Noli Pech")

    private var chainMelting = 75.0
    var chainType = "Kamal"
    var itemWeight = 0.0
    var wastagePercent =0.0
    var meltPlusWastage=0.0

    var chainCategory = 1   /// 1 = kamal wastage, 2 = box chain wasatage, 3 -> pech
    ////
    private var fineGold = 0.0
    private var _9950Gold = 0.0
    private var tunchGold = 0.0
    private var amountToPay = 0
    //

    //

    companion object{

        var arrayPerItemBill = arrayListOf<BillDetails>()
        var goldPricePerGram = 6280
        var extraGoldPricePerGram = 6200

    }
    //
    private lateinit var sharedPreferences: SharedPreferences
    val GOLD_PRICE_FILE = "goldPriceFile"
    val GOLD_PER_GRAM = "goldPerGram"
    val EXTRA_GOLD_PER_GRAM = "extraGoldPerGram"
    //
    

    private lateinit var bind:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        //
        arrayPerItemBill.clear()  /// clearing the previous bill
        //
        sharedPreferences = getSharedPreferences(GOLD_PRICE_FILE, Context.MODE_PRIVATE)
        goldPricePerGram = sharedPreferences.getInt(GOLD_PER_GRAM, goldPricePerGram)
        extraGoldPricePerGram = sharedPreferences.getInt(EXTRA_GOLD_PER_GRAM, extraGoldPricePerGram)
        //

        bind.imgChainCalculator.setOnClickListener {

            val ins = Intent(this@MainActivity,ChainCalculator::class.java)
            startActivity(ins)

        }
        //
        bind.imgChainBasket.setOnClickListener {

            val ins = Intent(this@MainActivity,ChainBasket::class.java)
            startActivity(ins)

        }
        //
        bind.imgSetting.setOnClickListener {
            val ins = Intent(this@MainActivity,Setting::class.java)
            startActivity(ins)

        }
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
        bind.spinnerChainType.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,arrChainList)
        bind.spinnerChainType.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                chainType = arrChainList[position]
                when
                {
                    position<=3 -> {
                        chainCategory = 1
                    }
                    //
                    position in 4..6 -> {
                        chainCategory = 2
                    }
                    //
                    position in 7..9 -> {
                        chainCategory = 3
                    }
                }
                showChainTypeAndMelting()


            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

                chainType = "Kamal"
                chainCategory = 1
                showChainTypeAndMelting()

            }

        }

        //
        bind.btnAddToCart.setOnClickListener {

            if(bind.etChainWeight.text.toString().isNotEmpty())
            {
                itemWeight = bind.etChainWeight.text.toString().toDouble()
                wastageIdentify()
                addMeltAndWastage()
                fineCaluclator()
                _9950Calculator()
                amountCalculator()
                //
                arrayPerItemBill.add(BillDetails(chainType, itemWeight,
                    meltPlusWastage,fineGold,_9950Gold,amountToPay))
                //
                bind.etChainWeight.text.clear()
                    //
                Toast.makeText(this, "Chain Added", Toast.LENGTH_SHORT).show()


            }
            else
            {
                Toast.makeText(this, "Please Enter weight", Toast.LENGTH_SHORT).show()
            }

        }
        //
        //

    }
    ///
//    var arrChainList = arrayOf("Kamal","Sehensha","Ball Chain","Box Chain","Rodking",
//        "Press Chain","Crop Chain","Noli","Chaki Pech","Noli Pech")

    private fun wastageIdentify()
    {
        when(chainCategory)
        {
            //Kamal chain wastage
            1 -> {

                if(itemWeight<3){

                    wastagePercent = if(chainMelting==91.7) {
                        1.3
                    } else {
                        1.5
                    }

                }
                else if(itemWeight>=3){

                    wastagePercent = 1.0

                }
            }
            //
            //Box chain wastage
            2 -> {

                if(itemWeight<3){

                    wastagePercent = if(chainMelting==91.7) {
                        2.55
                    } else {
                        2.25
                    }

                }
                else if(itemWeight>=3){

                    wastagePercent = if(chainMelting==91.7) {
                        2.3
                    } else {
                        2.0
                    }

                }
            }
            //
            //Pech wastage
            3 -> {

                if(itemWeight<1){

                    itemWeight += when(chainType) {
                        "Noli" -> {
                            0.010
                        }
                        //
                        else -> {
                            0.030
                        }
                    }

                    wastagePercent = 0.0

                }
                else if(itemWeight>=1){

                    wastagePercent = if(chainMelting==91.7) {
                        2.55
                    } else {
                        2.25
                    }

                }

            }
        }
    }
    //
    private fun addMeltAndWastage()
    {
        meltPlusWastage = wastagePercent+chainMelting
    }
    //
    private fun fineCaluclator()
    {
        fineGold = itemWeight*meltPlusWastage/100
        fineGold = String.format("%.3f",fineGold).toDouble()
    }
    //
    private fun _9950Calculator()
    {
        _9950Gold = itemWeight*meltPlusWastage/99.5
        _9950Gold = String.format("%.3f",_9950Gold).toDouble()
    }

    //
    private fun amountCalculator()
    {
        amountToPay = (fineGold* goldPricePerGram).toInt()
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
    //



}
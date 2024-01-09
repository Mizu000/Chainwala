package com.example.chainwala.activity

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.chainwala.R
import com.example.chainwala.databinding.ActivitySettingBinding

class Setting : AppCompatActivity() {


    private lateinit var sharedPreferences: SharedPreferences
    val GOLD_PRICE_FILE = "goldPriceFile"
    val GOLD_PER_GRAM = "goldPerGram"
    val EXTRA_GOLD_PER_GRAM = "extraGoldPerGram"


    //
    private lateinit var bind: ActivitySettingBinding
    //

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(bind.root)

        sharedPreferences = getSharedPreferences(GOLD_PRICE_FILE, Context.MODE_PRIVATE)

        bind.btnSaveSetting.setOnClickListener {

            val flag = (bind.etGoldPer10Gram.text.toString().isNotEmpty()
                    && bind.etExtraGoldPer10Gram.text.toString().isNotEmpty())

            if(flag)
            {
                MainActivity.goldPricePerGram = bind.etGoldPer10Gram.text.toString().toInt()/10
                MainActivity.extraGoldPricePerGram = bind.etExtraGoldPer10Gram.text.toString().toInt()/10

                sharedPreferences.edit().putInt(GOLD_PER_GRAM, MainActivity.goldPricePerGram).apply()
                sharedPreferences.edit().putInt(EXTRA_GOLD_PER_GRAM, MainActivity.extraGoldPricePerGram).apply()
                //
                Toast.makeText(this,"Saved",Toast.LENGTH_SHORT).show()
                Toast.makeText(this,"${MainActivity.goldPricePerGram}," +
                        " ${MainActivity.extraGoldPricePerGram}",Toast.LENGTH_SHORT).show()

            }
            else
            {
                Toast.makeText(this,"Please Enter value",Toast.LENGTH_SHORT).show()
            }


        }

    }
}
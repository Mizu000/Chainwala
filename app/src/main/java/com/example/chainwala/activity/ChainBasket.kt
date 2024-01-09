package com.example.chainwala.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chainwala.R
import com.example.chainwala.adapter.ItemListBillAdapter

class ChainBasket : AppCompatActivity() {

    //recycler varibale
    lateinit var RcvLayoutManager: RecyclerView.LayoutManager
    lateinit var rcvAdapter:ItemListBillAdapter
    lateinit var rcvView:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chain_basket)

        //recyoler initialize
        rcvView = findViewById(R.id.rcvBillDetails)
        rcvAdapter = ItemListBillAdapter(MainActivity.arrayPerItemBill)
        RcvLayoutManager = LinearLayoutManager(this@ChainBasket, LinearLayoutManager.VERTICAL, false)
        rcvView.adapter = rcvAdapter
        rcvView.layoutManager = RcvLayoutManager



    }
}
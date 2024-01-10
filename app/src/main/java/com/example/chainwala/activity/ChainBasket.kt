package com.example.chainwala.activity

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chainwala.R
import com.example.chainwala.adapter.ItemListBillAdapter
import com.example.chainwala.databinding.ActivityChainBasketBinding
import com.example.chainwala.databinding.ActivityMainBinding

class ChainBasket : AppCompatActivity() {

    //recycler varibale
    lateinit var RcvLayoutManager: RecyclerView.LayoutManager
    lateinit var rcvAdapter:ItemListBillAdapter
    lateinit var rcvView:RecyclerView
    //
    private lateinit var bind: ActivityChainBasketBinding
    //
    private var goldMeltingPayment = "99.50"
    var tunchValue = 0.0
    //
    var totalWeight = 0.0
    var totalFine = 0.0
    var total9950 = 0.0
    var totalAmount = 0
    var tunchGold = 0.0
    //
    var totalFineGiven = 0.0
    var total9950Given = 0.0
    var totalAmountGiven = 0
    var totalTunchGoldGiven = 0.0
    ///

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityChainBasketBinding.inflate(layoutInflater)
        setContentView(bind.root)

        //recyoler initialize
        rcvView = findViewById(R.id.rcvBillDetails)
        rcvAdapter = ItemListBillAdapter(MainActivity.arrayPerItemBill)
        RcvLayoutManager = LinearLayoutManager(this@ChainBasket, LinearLayoutManager.VERTICAL, false)
        rcvView.adapter = rcvAdapter
        rcvView.layoutManager = RcvLayoutManager
        ///

        totalWeightCalculator()
        totalFineCalculator()
        total9950Calculator()
        totalAmountCalculator()
        //
        bind.txtPaymentShow.text = total9950.toString()
        //

        bind.rgGoldMelting.setOnCheckedChangeListener { _, i ->

            when(i)
            {

                R.id.rbtnGoldMelting99_5 -> {


                    tunchValue = 0.0
                    bind.rbtnGoldMeltingCustom.text = "Tunch"
                    showBillToCustomer("99.50")
                }
                R.id.rbtnGoldMelting100 -> {

                    tunchValue = 0.0
                    bind.rbtnGoldMeltingCustom.text = "Tunch"
                    showBillToCustomer("Fine")
                }
                R.id.rbtnGoldMeltingCustom -> {

                    goldMeltingPayment = "Tunch"
                    openTunchInput()

                }
                R.id.rbtnGoldMeltingCash -> {

                    tunchValue = 0.0
                    bind.rbtnGoldMeltingCustom.text = "Tunch"
                    showBillToCustomer("Cash")
                }

            }




        }
        ///
        bind.etCustomerPaying.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
               showPaymentOfCustomer()
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
        ///
        
        bind.btnPrintBill.setOnClickListener { 
            
            MainActivity.arrayPerItemBill.clear()
            
        }



    }
    //
    fun showBillToCustomer(temp:String)
    {

        //
        goldMeltingPayment =  temp
        //
        showPaymentOfCustomer()
        //
        when(goldMeltingPayment)
        {
            "99.50" -> {

                bind.txtPaymentShow.text = "Gold: ${total9950}, Melt%: 99.50"
            }
            //
            "Fine" -> {

                bind.txtPaymentShow.text = "Gold: ${totalFine}, Melt%: Fine"

            }
            //
            "Tunch" -> {

                bind.txtPaymentShow.text = "Gold: ${tunchGold}, Melt%: $tunchValue"

            }
            //
            "Cash" -> {

                bind.txtPaymentShow.text = "Cash: ${totalAmount} Rs"

            }
        }

      
    }
    //
    fun showPaymentOfCustomer()
    {
        val temp = bind.etCustomerPaying.text.toString()

        when(goldMeltingPayment)
        {
            "99.50" -> {

                bind.txtPaidShow.text = "Gold: ${temp}, Melt%: 99.50"
            }
            //
            "Fine" -> {

                bind.txtPaidShow.text = "Gold: ${temp}, Melt%: Fine"

            }
            //
            "Tunch" -> {

                bind.txtPaidShow.text = "Gold: ${temp}, Melt%: $tunchValue"

            }
            //
            "Cash" -> {

                bind.txtPaidShow.text = "Cash: ${temp} Rs"

            }
        }
    }

//

    fun totalWeightCalculator()
    {
        totalWeight = 0.0
        for(n in MainActivity.arrayPerItemBill)
        {
            totalWeight += n.itemWeight
        }

        totalWeight = String.format("%.3f",totalWeight).toDouble()

        bind.txtItemTotalWeight.text = totalWeight.toString()
        ///
    }
    //
    fun totalFineCalculator(){

        totalFine = 0.0
        for(n in MainActivity.arrayPerItemBill)
        {
            totalFine += n.fine
        }

        totalFine = String.format("%.3f",totalFine).toDouble()

        bind.txtItemTotalFineGold.text = totalFine.toString()
        ///

    }
    //
    fun total9950Calculator()
    {
        total9950 = 0.0
        for(n in MainActivity.arrayPerItemBill)
        {
            total9950 += n._9950
        }

        total9950 = String.format("%.3f",total9950).toDouble()
        //
        bind.txtItemTotal9950Gold.text = total9950.toString()
        //
    }
    //
    fun totalAmountCalculator()
    {
            totalAmount = 0
        for(n in MainActivity.arrayPerItemBill)
        {
            totalAmount += n.amount
        }
        //
        bind.txtItemTotalPayAmount.text = totalAmount.toString()
        //
    }
//
private fun tunchCalculator()
{
    tunchGold = if(tunchValue==0.0) {
        0.0
    } else {
        totalFine*100/tunchValue
    }
    tunchGold = String.format("%.3f",tunchGold).toDouble()

}
    //
    private fun openTunchInput()
    {

        val dialog = Dialog(this)
        dialog.setContentView(R.layout.tunch_input)
        dialog.setCancelable(false)


        // Adjust the dialog's layout parameters
        val layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.window?.setLayout(layoutParams.width, layoutParams.height)
        //
        val etTunchInput: EditText = dialog.findViewById(R.id.etTunchMelting)
        val btnTunchMelting: Button = dialog.findViewById(R.id.btnTunchMelting)
        dialog.show()
        //

        btnTunchMelting.setOnClickListener {

            if (etTunchInput.text.toString().isNotEmpty()) {

                val tunchShow = etTunchInput.text.toString().toDouble()
                tunchValue = tunchShow
                tunchCalculator()
                bind.rbtnGoldMeltingCustom.text = tunchShow.toString()
                showBillToCustomer("Tunch")
                dialog.dismiss()

            } else {
                Toast.makeText(this, "Please Enter something", Toast.LENGTH_SHORT).show()
            }
        }


        ///

    }
    
    ///
}
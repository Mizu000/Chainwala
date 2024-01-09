package com.example.chainwala.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chainwala.R
import com.example.chainwala.modal.BillDetails

class ItemListBillAdapter(private val perItemBill:ArrayList<BillDetails>):
    RecyclerView.Adapter<ItemListBillAdapter.ItemListBillHolder>() {


    class ItemListBillHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val itemName:TextView = itemView.findViewById(R.id.txtItemDesc)
        val itemWeight:TextView = itemView.findViewById(R.id.txtItemWeight)
        val itemMelt:TextView = itemView.findViewById(R.id.txtItemMelt)
        val itemWastage:TextView = itemView.findViewById(R.id.txtItemWastage)
        val meltPlusWastage:TextView = itemView.findViewById(R.id.txtItemMeltPlusWastage)
        val fineGold:TextView = itemView.findViewById(R.id.txtItemFineGold)
        val _9950Gold:TextView = itemView.findViewById(R.id.txtItem9950Gold)
        val tunchGold:TextView = itemView.findViewById(R.id.txtItemTucnhGold)
        val amountToPay:TextView = itemView.findViewById(R.id.txtItemPayAmount)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemListBillHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.per_item_bill_layout, parent, false)
        return ItemListBillHolder(view)
    }

    override fun getItemCount(): Int {

        return perItemBill.size
    }

    override fun onBindViewHolder(holder: ItemListBillHolder, position: Int) {

        val fullBill = perItemBill[position]
        //
        holder.itemName.text = "${position+1}. ${fullBill.itemName}"
        holder.itemWeight.text = fullBill.itemWeight.toString()
        holder.itemMelt.text = fullBill.meltPercent.toString()
        holder.itemWastage.text = fullBill.wastagePercent.toString()
        holder.meltPlusWastage.text = fullBill.meltPlusWastage.toString()
        holder.fineGold.text = fullBill.fine.toString()
        holder._9950Gold.text = fullBill._9950.toString()
        holder.tunchGold.text = fullBill.tunch.toString()
        holder.amountToPay.text = fullBill.amount.toString()

    }

}
package com.example.hostelmanageapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Rector_Adapter(private val rectorList: ArrayList<Rector>):RecyclerView.Adapter<Rector_Adapter.myViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.rector_item,parent,false)
        return myViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val currentitem = rectorList[position]

        holder.Enrollment.text = currentitem.enrollment
        holder.Name.text = currentitem.name
        holder.HostelName.text = currentitem.hostelname
    }

    override fun getItemCount(): Int {
        return rectorList.size
    }

    class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val Enrollment: TextView = itemView.findViewById(R.id.r_enrollno)
        val Name: TextView = itemView.findViewById(R.id.r_name)
        val HostelName: TextView = itemView.findViewById(R.id.r_hostelname)

    }
}
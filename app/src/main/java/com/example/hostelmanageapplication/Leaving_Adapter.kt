package com.example.hostelmanageapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Leaving_Adapter(private val LeavingList:ArrayList<Leaving>):RecyclerView.Adapter<Leaving_Adapter.myViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.leave_item,parent,false)
        return myViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val currentitem = LeavingList[position]

        holder.enrollment.text = currentitem.enroll
        holder.Name.text = currentitem.name
        holder.Hostelname.text = currentitem.hostelname
        holder.sem.text = currentitem.sem
        holder.College.text = currentitem.college
        holder.roomno.text = currentitem.roomno
        holder.date.text = currentitem.date
        holder.address.text = currentitem.address
        holder.time.text = currentitem.time
        holder.purpose.text = currentitem.purpose
        holder.return_date.text = currentitem.return_date
    }

    override fun getItemCount(): Int {
        return LeavingList.size
    }

    class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val enrollment: TextView = itemView.findViewById(R.id.l_enrollno)
        val Name: TextView = itemView.findViewById(R.id.l_name)
        val Hostelname: TextView = itemView.findViewById(R.id.l_hostelname)
        val sem: TextView = itemView.findViewById(R.id.l_semester)
        val College: TextView = itemView.findViewById(R.id.l_college)
        val roomno: TextView = itemView.findViewById(R.id.l_roomno)
        val date: TextView = itemView.findViewById(R.id.l_date)
        val address: TextView = itemView.findViewById(R.id.l_address)
        val time: TextView = itemView.findViewById(R.id.l_Time)
        val purpose: TextView = itemView.findViewById(R.id.l_purpose)
        val return_date: TextView = itemView.findViewById(R.id.l_return_date)

    }
}
package com.example.hostelmanageapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Student_Adapter(private val studentlist: ArrayList<Student>):RecyclerView.Adapter<Student_Adapter.myViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.student_item,parent,false)
        return myViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val currentitem = studentlist[position]

        holder.Enrollment.text = currentitem.enrollment
        holder.Name.text = currentitem.name
        holder.HostelStatus.text = currentitem.hostelstatus
    }

    override fun getItemCount(): Int {
        return studentlist.size
    }


    class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val Enrollment: TextView = itemView.findViewById(R.id.s_enrollno)
        val Name: TextView = itemView.findViewById(R.id.s_name)
        val HostelStatus: TextView = itemView.findViewById(R.id.s_hostelstatus)

    }

}
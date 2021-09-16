package com.ubaya.advweek4.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.ubaya.advweek4.R
import com.ubaya.advweek4.model.Student
import kotlinx.android.synthetic.main.student_list_item.view.*
import com.ubaya.advweek4.util.loadImage

class StudentListAdapter(val studenList:ArrayList<Student>):RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>()
{
    class StudentViewHolder(var view: View) : RecyclerView.ViewHolder(view)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):StudentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.student_list_item, parent, false)
        return StudentViewHolder(view)
    }
    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.view.txtID.text = studenList[position].id
        holder.view.txtName.text = studenList[position].name
        holder.view.btnDetail.setOnClickListener {
            val action = studenList[position].id?.let { it1 ->
                StudentListFragmentDirections.actionStudentDetail(
                    it1,studenList[position].name,studenList[position].bod,studenList[position].phone,studenList[position].photoUrl)
            }
            if (action != null) {
                Navigation.findNavController(it).navigate(action)
            }
        }
        holder.view.imageView.loadImage(studenList[position].photoUrl,
            holder.view.progressBar)
    }
    override fun getItemCount(): Int {
        return studenList.size
    }
    fun updateStudentList(newStudentList: List<Student>) {
        studenList.clear()
        studenList.addAll(newStudentList)
        notifyDataSetChanged()
    }
}
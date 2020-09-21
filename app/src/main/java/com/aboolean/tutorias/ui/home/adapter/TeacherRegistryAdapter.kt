package com.aboolean.tutorias.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.aboolean.tutorias.R
import com.aboolean.tutorias.domain.model.TeacherRegistry
import kotlinx.android.synthetic.main.item_teacher_add.view.*

class TeacherRegistryAdapter(private val teacherRegistries: MutableList<TeacherRegistry>) :

    RecyclerView.Adapter<TeacherRegistryAdapter.ViewHolder>() {

    private var itemClickListener: ((TeacherRegistry) -> Unit?)? = null

    private var isIconAdd: Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_teacher_add,
                parent, false
            )
        )
    }

    override fun getItemCount(): Int = teacherRegistries.size

    fun setItemClickListener(listener: (TeacherRegistry) -> Unit) {
        this.itemClickListener = listener
    }

    fun addNewTeachers(newTeachers: List<TeacherRegistry>) {
        teacherRegistries.addAll(newTeachers)
        notifyDataSetChanged()
    }


    fun deleteTeachers(teacherRegistry: TeacherRegistry) {
        teacherRegistries.remove(teacherRegistry)
        notifyDataSetChanged()
    }

    fun deleteIconAddd() {
        isIconAdd = true
    }

    fun updateTeachers(newTeachers: List<TeacherRegistry>) {
        teacherRegistries.apply {
            clear()
            addAll(newTeachers)
            notifyDataSetChanged()
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(teacherRegistries[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(teacherRegistry: TeacherRegistry) {
            itemView.apply {
                with(teacherRegistry) {
                    tv_item_teacher_name.text = nombre
                    tv_item_teacher_matter.text = cat_tiempo
                    if (isIconAdd) {
                        iv_item_teacher_add.visibility = View.GONE
                    }
                }
                setOnClickListener { itemClickListener?.invoke(teacherRegistry) }
            }
        }
    }


}
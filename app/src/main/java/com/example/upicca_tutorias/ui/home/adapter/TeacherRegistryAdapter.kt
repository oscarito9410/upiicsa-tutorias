package com.example.upicca_tutorias.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide
import com.example.upicca_tutorias.R
import com.example.upicca_tutorias.domain.model.TeacherRegistry
import kotlinx.android.synthetic.main.item_teacher_add.view.*

class TeacherRegistryAdapter(private val teacherRegistries: MutableList<TeacherRegistry>) :

    RecyclerView.Adapter<TeacherRegistryAdapter.ViewHolder>() {

    private var itemClickListener: ((TeacherRegistry) -> Unit?)? = null

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

    fun addNewTeachers(newMovies: List<TeacherRegistry>) {
        teacherRegistries.addAll(newMovies)
        notifyDataSetChanged()
    }

    fun updateTeachers(newMovies: List<TeacherRegistry>) {
        teacherRegistries.apply {
            clear()
            addAll(newMovies)
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
                    tv_item_teacher_name.text = fullName
                    tv_item_teacher_matter.text = matter

                    Glide.with(context).asDrawable().load(posterPath)
                        .placeholder(R.drawable.ic_item_teacher_icon)
                        .into(iv_item_teacher_icon)
                }
                setOnClickListener { itemClickListener?.invoke(teacherRegistry) }
            }
        }
    }
}
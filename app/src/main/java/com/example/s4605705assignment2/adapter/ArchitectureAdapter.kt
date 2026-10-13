package com.example.s4605705assignment2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.s4605705assignment2.R
import com.example.s4605705assignment2.model.ArchitectureEntity

class ArchitectureAdapter(
    private var entities: List<ArchitectureEntity> = emptyList(),
    private val onItemClick: (ArchitectureEntity) -> Unit
) : RecyclerView.Adapter<ArchitectureAdapter.ArchitectureViewHolder>() {

    class ArchitectureViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private val tvName: TextView =
            itemView.findViewById(R.id.tvName)

        private val tvArchitect: TextView =
            itemView.findViewById(R.id.tvArchitect)

        private val tvLocation: TextView =
            itemView.findViewById(R.id.tvLocation)

        private val tvYear: TextView =
            itemView.findViewById(R.id.tvYear)

        private val tvStyle: TextView =
            itemView.findViewById(R.id.tvStyle)

        private val tvHeight: TextView =
            itemView.findViewById(R.id.tvHeight)

        fun bind(
            entity: ArchitectureEntity,
            onItemClick: (ArchitectureEntity) -> Unit
        ) {

            tvName.text = entity.name
            tvArchitect.text = "Architect: ${entity.architect}"
            tvLocation.text = "Location: ${entity.location}"
            tvYear.text = "Year Completed: ${entity.yearCompleted}"
            tvStyle.text = "Style: ${entity.style}"
            tvHeight.text = "Height: ${entity.height} m"

            itemView.setOnClickListener {
                onItemClick(entity)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArchitectureViewHolder {

        val view = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.item_architecture,
                parent,
                false
            )

        return ArchitectureViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ArchitectureViewHolder,
        position: Int
    ) {
        holder.bind(
            entities[position],
            onItemClick
        )
    }

    override fun getItemCount(): Int {
        return entities.size
    }

    fun updateData(
        newEntities: List<ArchitectureEntity>
    ) {
        entities = newEntities
        notifyDataSetChanged()
    }
}
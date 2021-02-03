package com.sin.sunflowerp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sin.sunflowerp.data.Plant
import com.sin.sunflowerp.databinding.ListItemPlantBinding
import com.sin.sunflowerp.ui.fragment.PlantListFragmentDirections

class PlantAdapter : ListAdapter<Plant, PlantAdapter.ViewHolder>(PlantDiffCallback()) {

    class ViewHolder(val binding:ListItemPlantBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemPlantBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val plant = getItem(position)
        with(holder){
            binding.plant = plant
            binding.onclickListener = createClickListener(plant.plantId)
            binding.executePendingBindings()
        }
    }

    private fun createClickListener(plantId:String): View.OnClickListener{
        return View.OnClickListener {
            //跳转
            val navDirections = PlantListFragmentDirections.actionPlantListFragmentToPlantDetailFragment(plantId)
            it.findNavController().navigate(navDirections)
        }
    }
}

private class PlantDiffCallback: DiffUtil.ItemCallback<Plant>(){
    override fun areItemsTheSame(oldItem: Plant, newItem: Plant): Boolean {
        return oldItem.plantId == newItem.plantId
    }

    override fun areContentsTheSame(oldItem: Plant, newItem: Plant): Boolean {
        return oldItem == newItem
    }

}
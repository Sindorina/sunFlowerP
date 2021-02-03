package com.sin.sunflowerp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sin.sunflowerp.data.PlantAndGardenPlantings
import com.sin.sunflowerp.databinding.ListItemGardenPlantingBinding
import com.sin.sunflowerp.ui.fragment.GardenFragmentDirections
import com.sin.sunflowerp.viewmodels.PlantAndGardenPlantingsViewModel

class GardenPlantingAdapter : ListAdapter<PlantAndGardenPlantings, GardenPlantingAdapter.ViewHolder>(GardenPlantDiffCallback()) {

    class ViewHolder(val binding:ListItemGardenPlantingBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemGardenPlantingBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val plantAndGardenPlantings = getItem(position)
        with(holder){
            binding.viewModel = PlantAndGardenPlantingsViewModel(plantAndGardenPlantings)
            binding.onclickListener = createClickListener(plantAndGardenPlantings.plant.plantId)
            binding.executePendingBindings()
        }
    }

    private fun createClickListener(plantId:String): View.OnClickListener{
        return View.OnClickListener {
            //跳转
            val navDirections = GardenFragmentDirections.actionGardenFragmentToPlantDetailFragment(plantId)
            it.findNavController().navigate(navDirections)
        }
    }
}

private class GardenPlantDiffCallback: DiffUtil.ItemCallback<PlantAndGardenPlantings>(){
    override fun areItemsTheSame(oldItem: PlantAndGardenPlantings, newItem: PlantAndGardenPlantings): Boolean {
        return oldItem.plant.plantId == newItem.plant.plantId
    }

    override fun areContentsTheSame(oldItem: PlantAndGardenPlantings, newItem: PlantAndGardenPlantings): Boolean {
        return oldItem.plant == newItem.plant
    }

}
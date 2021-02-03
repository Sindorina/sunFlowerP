package com.sin.sunflowerp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sin.sunflowerp.R
import com.sin.sunflowerp.adapter.PlantAdapter
import com.sin.sunflowerp.databinding.FragmentPlantListBinding
import com.sin.sunflowerp.utils.InjectorUtils
import com.sin.sunflowerp.viewmodels.PlantListViewModel

class PlantListFragment :Fragment(){

    //扩展函数
    private val viewModel :PlantListViewModel by viewModels {
        InjectorUtils.providePlantListViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPlantListBinding.inflate(inflater)
        val adapter = PlantAdapter()
        binding.plantList.adapter = adapter
        subscribeUi(adapter)
        setHasOptionsMenu(true)
        return binding.root
    }

    private fun subscribeUi(adapter: PlantAdapter){
        viewModel.plants.observe(requireActivity(),{
            adapter.submitList(it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_plant_list,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.filter_zone ->{
                updateData()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    //切换数据源
    private fun updateData(){
        with(viewModel){
            if (isFiltered()){
                clearGrowZoneNumber()
            }else{
                setGrowZoneNumber(7)
            }
        }
    }
}
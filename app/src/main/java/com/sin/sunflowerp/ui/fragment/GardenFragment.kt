package com.sin.sunflowerp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sin.sunflowerp.adapter.GardenPlantingAdapter
import com.sin.sunflowerp.databinding.FragmentGardenBinding
import com.sin.sunflowerp.utils.InjectorUtils
import com.sin.sunflowerp.viewmodels.GardenPlantingListViewModel

class GardenFragment :Fragment(){


    private val gardenPlantingListViewModel by viewModels<GardenPlantingListViewModel> {
        InjectorUtils.provideGardenPlantingListViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentGardenBinding.inflate(inflater)
        val adapter = GardenPlantingAdapter()
        binding.gardenList.adapter = adapter

        subscribeUi(adapter,binding)
        return binding.root
    }


    private fun subscribeUi(adapter:GardenPlantingAdapter,binding:FragmentGardenBinding){
        gardenPlantingListViewModel.plantAndGardenPlantings.observe(viewLifecycleOwner,{
            Log.e("tag","花园数据size: ${it.size}")
            adapter.submitList(it)
        })
        gardenPlantingListViewModel.gardenPlantings.observe(viewLifecycleOwner,{
            binding.hasPlantings = it.isNotEmpty()
        })
    }
}
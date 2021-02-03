package com.sin.sunflowerp.ui.fragment

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.sin.sunflowerp.R
import com.sin.sunflowerp.databinding.FragmentPlantDetailBinding
import com.sin.sunflowerp.utils.InjectorUtils
import com.sin.sunflowerp.viewmodels.PlantDetailViewModel
import com.sin.sunflowerp.viewmodels.PlantDetailViewModelFactory

class PlantDetailFragment :Fragment(){
    private val args: PlantDetailFragmentArgs by navArgs()
    private lateinit var shareText:String
    private val plantDetailViewModel by viewModels<PlantDetailViewModel> {
        InjectorUtils.providePlantDetailViewModelFactory(requireActivity(),args.plantId)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPlantDetailBinding.inflate(inflater)
        subscribeUi(binding)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_plant_detail,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_share ->{
                val shareIntent = ShareCompat.IntentBuilder.from(requireActivity())
                    .setText(shareText)
                    .setType("text/plain")
                    .createChooserIntent()
                    .apply {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            // If we're on Lollipop, we can open the intent as a document
                            addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT or Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
                        } else {
                            // Else, we will use the old CLEAR_WHEN_TASK_RESET flag
                            addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET)
                        }
                    }
                startActivity(shareIntent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun subscribeUi(binding:FragmentPlantDetailBinding){
        with(binding){
            model = plantDetailViewModel
            lifecycleOwner = viewLifecycleOwner
            fab.setOnClickListener {
                plantDetailViewModel.addPlantToGarden()
                Snackbar.make(it, R.string.added_plant_to_garden, Snackbar.LENGTH_LONG).show()
            }
        }
        plantDetailViewModel.plant.observe(viewLifecycleOwner,{
            shareText = getString(R.string.share_text_plant, it.name)
        })
    }
}
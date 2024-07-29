package com.petopedia.app.ui.adapter

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.petopedia.app.domain.model.PetDetails
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("setHorizontalPager")
    fun ViewPager2.setHorizontalPager(petList: StateFlow<PetDetails>?) {
        val lifecycleOwner = context as? LifecycleOwner
        lifecycleOwner?.lifecycleScope?.launch {
            petList?.collect { list ->
                if (list.speciesList.isNotEmpty()) {
                    val adapter = this@setHorizontalPager.adapter as? PetSliderAdapter
                    if (adapter == null) {
                        this@setHorizontalPager.adapter = PetSliderAdapter(list.speciesList.map { pets -> pets.image })
                    } else {
                        adapter.updateData(list.speciesList.map { pets -> pets.image })
                    }
                }
            }
        }
    }

    @JvmStatic
    @BindingAdapter("index", "setPetList")
    fun RecyclerView.setPetList(
        index: Int,
        petList: StateFlow<PetDetails>?,
    ) {
        val lifecycleOwner = context as? LifecycleOwner
        lifecycleOwner?.lifecycleScope?.launch {
            petList?.collect { list ->
                if (list.speciesList.isNotEmpty()) {
                    val adapter = this@setPetList.adapter as? PetBreedAdapter
                    if (adapter == null) {
                        this@setPetList.adapter = PetBreedAdapter(list.speciesList[index].breedsList)
                    } else {
                        adapter.updateData(list.speciesList[index].breedsList)
                    }
                }
            }
        }
    }
}

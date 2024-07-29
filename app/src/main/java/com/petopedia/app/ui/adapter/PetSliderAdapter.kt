package com.petopedia.app.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.petopedia.app.R
import com.petopedia.app.databinding.SliderItemBinding

class PetSliderAdapter(
    private var destinationDetails: List<Int>,
) : RecyclerView.Adapter<PetSliderAdapter.PagerViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): PagerViewHolder {
        val mBinding =
            DataBindingUtil.inflate<SliderItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.slider_item,
                parent,
                false,
            )
        return PagerViewHolder(mBinding)
    }

    override fun onBindViewHolder(
        holder: PagerViewHolder,
        position: Int,
    ) {
        holder.setData(destinationDetails[position])
    }

    override fun getItemCount(): Int = destinationDetails.size

    inner class PagerViewHolder(
        private val mBinding: SliderItemBinding,
    ) : RecyclerView.ViewHolder(mBinding.root) {
        fun setData(sliderImage: Int) {
            mBinding.imageView.setBackgroundResource(sliderImage)
        }
    }

    fun updateData(images: List<Int>) {
        destinationDetails = images
        notifyDataSetChanged()
    }
}

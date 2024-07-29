package com.petopedia.app.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.petopedia.app.R
import com.petopedia.app.databinding.CardItemBinding
import com.petopedia.app.domain.model.PetDetails

class PetBreedAdapter(
    private var speciesList: List<PetDetails.Species.Breeds>,
) : RecyclerView.Adapter<PetBreedAdapter.PetViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): PetViewHolder {
        val mBinding =
            DataBindingUtil.inflate<CardItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.card_item,
                parent,
                false,
            )
        return PetViewHolder(mBinding)
    }

    override fun onBindViewHolder(
        holder: PetViewHolder,
        position: Int,
    ) {
        holder.setItemData(speciesList[position])
    }

    override fun getItemCount(): Int = speciesList.size

    fun updateData(list: List<PetDetails.Species.Breeds>) {
        speciesList = list
        notifyDataSetChanged()
    }

    inner class PetViewHolder(
        private var mBinding: CardItemBinding,
    ) : RecyclerView.ViewHolder(mBinding.root) {
        fun setItemData(pet: PetDetails.Species.Breeds) {
            mBinding.petName.text = pet.title
            mBinding.petDetail.text = mBinding.root.context.getString(R.string.animal_species, pet.subTitle)
            mBinding.petImage.setBackgroundResource(pet.icon)
        }
    }
}

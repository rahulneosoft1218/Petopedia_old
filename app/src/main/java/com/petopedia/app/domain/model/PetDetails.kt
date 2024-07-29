package com.petopedia.app.domain.model

import androidx.annotation.DrawableRes

data class PetDetails(
    val speciesList: List<Species>,
) {
    data class Species(
        val id: Animal,
        var breedsList: List<Breeds>,
        val petName: Int,
        @DrawableRes val image: Int,
    ) {
        data class Breeds(
            val subTitle: String,
            val title: String,
            @DrawableRes val icon: Int,
        )
    }
}

enum class Animal(
    val species: Int,
) {
    Cat(1),
    Dog(2),
    Sheep(3),
    Rabbit(4),
    Parrot(5),
}

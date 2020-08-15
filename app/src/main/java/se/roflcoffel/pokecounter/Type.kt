package se.roflcoffel.pokecounter

import java.util.*

data class Type(var Element : TypeElement, var DamageRelation : DamageRelation) {
    override fun toString(): String {
        return "${Element.name.toLowerCase(Locale.ROOT).capitalize()}\nRelation: \n$DamageRelation"
    }
}
package se.roflcoffel.pokecounter

data class Type(var Element : TypeElement, var DamageRelation : DamageRelation) {
    override fun toString(): String {
        return "${Element.name} Damage is: \n$DamageRelation"
    }
}
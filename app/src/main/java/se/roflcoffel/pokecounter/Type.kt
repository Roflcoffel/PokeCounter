package se.roflcoffel.pokecounter

data class Type(var Type : TypeElement, var DamageRelation : DamageRelation) {
    override fun toString(): String {
        return "${Type.name} Damage is: \n$DamageRelation"
    }
}
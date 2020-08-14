package se.roflcoffel.pokecounter

data class DamageRelation(
    val doubleDamageFrom : MutableList<String?>,
    val halfDamageFrom : MutableList<String?>,
    val noDamageFrom : MutableList<String?>,
    val doubleDamageTo : MutableList<String?>,
    val halfDamageTo : MutableList<String?>,
    val noDamageTo : MutableList<String?>
) {

    override fun toString(): String {
        return "x2 Damage from $doubleDamageFrom" +
                "/2 Damage from $halfDamageFrom"
    }
}
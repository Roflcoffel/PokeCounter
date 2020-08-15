package se.roflcoffel.pokecounter

data class Pokemon(val id: Int, val name: String, val Types: MutableList<Type?>) {
    override fun toString(): String {
        return "${name.capitalize()} : ${Types.joinToString (separator = "\n"){ s -> "$s" }}"
    }
}
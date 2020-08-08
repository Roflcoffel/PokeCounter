package se.roflcoffel.pokecounter

data class Pokemon(val id: Int, val name: String, val Types: Array<Type?>) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Pokemon

        if (id != other.id) return false
        if (name != other.name) return false
        if (!Types.contentEquals(other.Types)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + Types.contentHashCode()
        return result
    }

    override fun toString(): String {
        return "$id : $name : $Types"
    }
}
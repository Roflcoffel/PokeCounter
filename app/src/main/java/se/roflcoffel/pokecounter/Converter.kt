package se.roflcoffel.pokecounter

import org.json.JSONArray
import org.json.JSONObject
import java.util.*

//Converts JSON object to a specific class
object Converter {
    fun toPokemon(obj : JSONObject, typeMap : MutableMap<String, Type>) : Pokemon {
        val JSONtypes : JSONArray = obj.getJSONArray("types")
        val name : String = obj.getString("name")
        val id : Int = obj.getInt("id")

        var types : Array<Type?> = arrayOf()

        for(i in 0 until JSONtypes.length())
        {
            val type = JSONtypes.getJSONObject(i)
                .getJSONObject("type")
                .getString("name")

            types[i] = typeMap[type]
        }

        return Pokemon(id, name, types)
    }

    fun toType(obj : JSONObject) : Type {
        val name : String = obj.getString("name")
        val jsonRelation : JSONObject = obj.getJSONObject("damage_relations")

        val dmgRelation = toDamageRelation(jsonRelation)

        return Type(TypeElement.valueOf(name.toUpperCase(Locale.ROOT)), dmgRelation)
    }

    private fun toDamageRelation(obj : JSONObject) : DamageRelation {
        var doubleDamageFrom : MutableList<String?> = mutableListOf()
        var halfDamageFrom   : MutableList<String?> = mutableListOf()
        var noDamageFrom     : MutableList<String?> = mutableListOf()
        var doubleDamageTo   : MutableList<String?> = mutableListOf()
        var halfDamageTo     : MutableList<String?> = mutableListOf()
        var noDamageTo       : MutableList<String?> = mutableListOf()

        val doubleFrom : JSONArray = obj.getJSONArray("double_damage_from")
        for(i in 0 until doubleFrom.length()) {
            doubleDamageFrom.add(doubleFrom.getJSONObject(i).getString("name"))
        }

        val halfFrom : JSONArray = obj.getJSONArray("half_damage_from")
        for(i in 0 until halfFrom.length()) {
            halfDamageFrom.add(halfFrom.getJSONObject(i).getString("name"))
        }

        val noFrom : JSONArray = obj.getJSONArray("no_damage_from")
        for(i in 0 until noFrom.length()) {
            noDamageFrom.add(noFrom.getJSONObject(i).getString("name"))
        }

        val doubleTo : JSONArray = obj.getJSONArray("double_damage_to")
        for(i in 0 until doubleTo.length()) {
            doubleDamageTo.add(doubleTo.getJSONObject(i).getString("name"))
        }

        val halfTo : JSONArray = obj.getJSONArray("half_damage_to")
        for(i in 0 until halfTo.length()) {
            halfDamageTo.add(halfTo.getJSONObject(i).getString("name"))
        }

        val noTo : JSONArray = obj.getJSONArray("no_damage_to")
        for(i in 0 until noTo.length()) {
            noDamageTo.add(noTo.getJSONObject(i).getString("name"))
        }

        return DamageRelation(doubleDamageFrom, halfDamageFrom, noDamageFrom, doubleDamageTo, halfDamageTo, noDamageTo)
    }
}
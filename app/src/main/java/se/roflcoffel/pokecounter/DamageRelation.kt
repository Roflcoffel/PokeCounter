package se.roflcoffel.pokecounter

import org.json.JSONArray
import org.json.JSONObject

class DamageRelation() {
    private var doubleDamageFrom : MutableList<String?> = mutableListOf()
    private var halfDamageFrom : MutableList<String?> = mutableListOf()
    private var noDamageFrom : MutableList<String?> = mutableListOf()
    private var doubleDamageTo : MutableList<String?> = mutableListOf()
    private var halfDamageTo : MutableList<String?> = mutableListOf()
    private var noDamageTo : MutableList<String?> = mutableListOf()

    override fun toString(): String {
        return "x2 Damage from $doubleDamageFrom" +
                "/2 Damage from $halfDamageFrom"
    }

    fun MapJSON(obj: JSONObject) {
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
    }
}
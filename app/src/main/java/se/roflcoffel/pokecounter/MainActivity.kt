package se.roflcoffel.pokecounter

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.textfield.TextInputEditText
import org.json.JSONArray
import org.json.JSONObject

//Download all the Types
//Get all types, and cache them locally
//also save them in a Map, where the key is the name of the type.
//so we can easily assign it to a pokemon later.

//The Type Screen
//create a new activity page for selecting types with icons
//either have tabs or a swipe right, to view it.

//Cache Everything
//to cache we can try to use SharedPreferences, there do not seem to be
//a limit, but if we end up storing more than 1MB, then create a file instead.
class MainActivity : AppCompatActivity() {
    private lateinit var mTextViewResult : TextView;
    private lateinit var mQueue : RequestQueue;
    private var typeMap : MutableMap<String, Type> = mutableMapOf();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mTextViewResult = findViewById(R.id.text_view)

        var textInput : TextInputEditText = findViewById(R.id.input_text)
        var buttonGo : Button = findViewById(R.id.button_go)

        mQueue = Volley.newRequestQueue(this)

        var allTypes : Array<TypeElement> = TypeElement.values()

        allTypes.forEach { type -> getTypes("${type.ordinal+1}") }

        buttonGo.setOnClickListener {
            getPokemon("${textInput.text}")
        }
    }

    private fun getPokemon(endpoint: String) {
        val url = "https://pokeapi.co/api/v2/pokemon/$endpoint"

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                val JSONtypes : JSONArray = response.getJSONArray("types")
                val name : String = response.getString("name")
                val id : Int = response.getInt("id")

                var types : Array<Type?> = arrayOfNulls(2)

                for(i in 0 until JSONtypes.length())
                {
                    val type = JSONtypes.getJSONObject(i)
                                        .getJSONObject("type")
                                        .getString("name")

                    //types[i] = typeMap[type]
                }

                val testPoke : Pokemon = Pokemon(id, name, types)

                mTextViewResult.append(testPoke.toString())
            },
            Response.ErrorListener { error ->
                error.printStackTrace()
            }
        )

        mQueue.add(jsonObjectRequest);
    }

    private fun getTypes(endpoint: String) {
        val url = "https://pokeapi.co/api/v2/type/$endpoint"

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                val name : String = response.getString("name")
                val jsonRelation : JSONObject = response.getJSONObject("damage_relations")

                var dmgRelation = DamageRelation()
                dmgRelation.MapJSON(jsonRelation);

                //typeMap[name] = Type(TypeElement.valueOf(name), dmgRelation)


            },
            Response.ErrorListener { error ->
                error.printStackTrace()
            }
        )

        mQueue.add(jsonObjectRequest);
    }
}
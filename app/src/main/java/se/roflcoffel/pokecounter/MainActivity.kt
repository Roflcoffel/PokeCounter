package se.roflcoffel.pokecounter

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.textfield.TextInputEditText
import org.json.JSONObject
import java.util.*

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
    private val url_type    : String = "https://pokeapi.co/api/v2/type/"
    private val url_pokemon : String = "https://pokeapi.co/api/v2/pokemon/"

    private lateinit var mTextViewResult : TextView;
    private lateinit var mQueue          : RequestQueue;
    private lateinit var storage         : Storage;

    private var typeMap : MutableMap<String, Type> = mutableMapOf();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        storage = Storage(getSharedPreferences("pokemon", MODE_PRIVATE))
        //storage.Clear()

        mTextViewResult = findViewById(R.id.text_view)

        var textInput : TextInputEditText = findViewById(R.id.input_text)
        var buttonGo : Button = findViewById(R.id.button_go)

        mQueue = Volley.newRequestQueue(this)

        var allTypes : Array<TypeElement> = TypeElement.values()

        allTypes.forEach { type ->
            if(storage.KeyExist(type.name)) {

                val pokeType = storage.Retrieve(type.name, Type::class.java)
                val name = pokeType.Element.name

                typeMap[name] = pokeType
                Log.wtf("LOCAL", pokeType.toString())
            }
            else {
                getJSON(url_type + type.name.toLowerCase(Locale.ROOT),
                    Response.Listener { response ->
                        val respType = Converter.toType(response)
                        typeMap[respType.Element.name] = respType
                        storage.Submit(respType.Element.name, respType)

                        Log.wtf("GET", respType.toString())
                    },
                    Response.ErrorListener { error ->
                        error.printStackTrace()
                    }
                )
            }
        }

        buttonGo.setOnClickListener {
            getJSON(url_pokemon + textInput.text.toString().toLowerCase(Locale.ROOT),
                Response.Listener { response ->
                    val pokemon = Converter.toPokemon(response, typeMap)

                    mTextViewResult.append(pokemon.toString())
                },
                Response.ErrorListener { error ->
                    error.printStackTrace()
                }
            )
        }
    }

    private fun getJSON(url: String, resp: Response.Listener<JSONObject>, error : Response.ErrorListener) {
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null, resp, error)
        mQueue.add(jsonObjectRequest);
    }
}
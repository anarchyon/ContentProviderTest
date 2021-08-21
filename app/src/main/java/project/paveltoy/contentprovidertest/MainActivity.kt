package project.paveltoy.contentprovidertest

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import project.paveltoy.contentprovidertest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private val adapter = MovieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        binding.btnReadMovies.setOnClickListener {
            CoroutineScope(Dispatchers.Default).launch {
                getMovies()
            }
        }
    }

    private fun getMovies() {
        val contentResolver = this.contentResolver
        val favoriteMoviesUri = Uri.parse("content://paveltoy.provider/FavoriteMovies")
        var moviesName = arrayListOf<String>()

        val cursor = contentResolver.query(
            favoriteMoviesUri,
            null, null, null, null
        )
        cursor?.let {
            for (i in 0..cursor.count) {
                if (cursor.moveToPosition(i)) {
                    val name = cursor.getString(cursor.getColumnIndex("title"))
                    moviesName.add(name)
                }
            }
        }
        cursor?.close()
        runOnUiThread {
            adapter.dataSet = moviesName
            adapter.notifyDataSetChanged()
        }
    }
}
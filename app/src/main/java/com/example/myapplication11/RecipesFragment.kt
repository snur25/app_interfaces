package com.example.myapplication11

import RecipeAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecipesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recipes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)

        val recipes = listOf(
            Recipe(1, "Mercimek Koftesi", R.drawable.mercimekkofte),
            Recipe(2, "Sarma", R.drawable.sarma),
            Recipe(3, "Pide", R.drawable.pide)
        )

        val adapter = RecipeAdapter(
            recipes,
            onItemClicked = { recipe ->
                Toast.makeText(context, "Clicked: ${recipe.title}", Toast.LENGTH_SHORT).show()
                Log.d("RecipeClick", "Clicked on recipe with ID: ${recipe.id}")
            },
            onButtonClicked = { recipe, action ->
                Toast.makeText(context, "$action clicked for ${recipe.title}", Toast.LENGTH_SHORT).show()
                Log.d("RecipeClick", "$action clicked for recipe with ID: ${recipe.id}")
            }
        )

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }
}

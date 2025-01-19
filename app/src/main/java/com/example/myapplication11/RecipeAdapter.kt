import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication11.R
import com.example.myapplication11.Recipe

class RecipeAdapter(
    private val recipes: List<Recipe>,
    private val onItemClicked: (Recipe) -> Unit,
    private val onButtonClicked: (Recipe, String) -> Unit
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    class RecipeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageView)
        val titleTextView: TextView = view.findViewById(R.id.titleTextView)
        val likeButton: Button = view.findViewById(R.id.likeButton)
        val shareButton: Button = view.findViewById(R.id.shareButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_res, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.imageView.setImageResource(recipe.imageResId)
        holder.titleTextView.text = recipe.title

        holder.itemView.setOnClickListener {
            onItemClicked(recipe)
        }

        holder.likeButton.setOnClickListener {
            onButtonClicked(recipe, "like")
        }

        holder.shareButton.setOnClickListener {
            onButtonClicked(recipe, "share")
        }
    }

    override fun getItemCount() = recipes.size
}

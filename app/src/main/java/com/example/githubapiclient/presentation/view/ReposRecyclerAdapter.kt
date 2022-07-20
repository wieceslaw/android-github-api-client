package com.example.githubapiclient.presentation.view


import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapiclient.R
import com.example.githubapiclient.domain.model.Repo
import com.example.githubapiclient.util.Colors


class ReposRecyclerAdapter : RecyclerView.Adapter<ReposRecyclerAdapter.ViewHolder>() {
    private val colors = Colors()
    private var reposList = ArrayList<Repo>()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView
        val descriptionTextView: TextView
        val languageTextView: TextView
        val forksTextView: TextView
        val starsTextView: TextView
        val imageView: ImageView

        init {
            nameTextView = view.findViewById(R.id.name_text_view)
            descriptionTextView = view.findViewById(R.id.description_text_view)
            languageTextView = view.findViewById(R.id.language_text_view)
            forksTextView = view.findViewById(R.id.forks_text_view)
            starsTextView = view.findViewById(R.id.stars_text_view)
            imageView = view.findViewById(R.id.image_view)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.repo_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameTextView.text = reposList[position]?.name
        holder.descriptionTextView.text = reposList[position]?.description
        holder.languageTextView.text = reposList[position]?.language
        holder.forksTextView.text = reposList[position]?.forksCount.toString()
        holder.starsTextView.text = reposList[position]?.starsCount.toString()
        if (reposList[position].language == null) {
            holder.imageView.visibility = View.INVISIBLE
        } else {
            holder.imageView.visibility = View.VISIBLE
        }
        reposList[position].language?.let {
            colors.map[it]?.let { color ->
                (holder.imageView.background.mutate() as GradientDrawable).setColor(Color.parseColor(color))
            }
        }
    }

    override fun getItemCount(): Int = reposList.size

    fun updateReposList(reposList: List<Repo>) {
        this.reposList.clear()
        this.reposList.addAll(reposList)
        notifyDataSetChanged()
    }
}
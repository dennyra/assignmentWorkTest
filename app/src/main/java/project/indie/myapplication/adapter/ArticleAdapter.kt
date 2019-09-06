package project.indie.myapplication.adapter

import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_widget_article.view.*
import project.indie.myapplication.R
import project.indie.myapplication.model.Items

class ArticleAdapter(private val articles: List<Items>) : RecyclerView.Adapter<ArticleHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ArticleHolder {
        return ArticleHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.activity_widget_article, viewGroup, false))
    }

    override fun getItemCount(): Int = articles.size

    override fun onBindViewHolder(holder: ArticleHolder, position: Int) {
        holder.bindArticle(articles[position])
    }
}

class ArticleHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
    private val tvTitleArticle = view.tvTitleArticle
    private val ivImageArticle = view.ivImgArticle
    private var link = ""
    init {
        view.setOnClickListener(this)
    }
    override fun onClick(v: View) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        v.context.startActivities(arrayOf(browserIntent))
    }

    fun bindArticle(article: Items) {
        tvTitleArticle.setText(article.article_title)
        link = article.link
        Picasso.get() // give it the context
            .load(article.article_image) // load the image
            .into(ivImageArticle)
    }
}
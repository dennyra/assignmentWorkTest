package project.indie.myapplication.adapter

import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_widget_product.view.*
import project.indie.myapplication.R
import project.indie.myapplication.model.Items


class ProductAdapter(private val products: List<Items>) : RecyclerView.Adapter<ProductHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ProductHolder {
        return ProductHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.activity_widget_product, viewGroup, false))
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        holder.bindArticle(products[position])
    }
}

class ProductHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
    private val ivImgProduct = view.ivImgProduct
    private val tvTitleProduct = view.tvTitleProduct
    private var link = ""
    init {
        view.setOnClickListener(this)
    }
    override fun onClick(v: View) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        v.context.startActivities(arrayOf(browserIntent))
    }
    fun bindArticle(product: Items) {
        tvTitleProduct.setText(product.product_name)
        link = product.link
        Picasso.get()
            .load(product.product_image)
            .into(ivImgProduct)
    }
}
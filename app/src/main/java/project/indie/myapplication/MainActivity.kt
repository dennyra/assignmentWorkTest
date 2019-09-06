package project.indie.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import project.indie.myapplication.adapter.ArticleAdapter
import project.indie.myapplication.adapter.ProductAdapter
import project.indie.myapplication.model.Items
import project.indie.myapplication.model.Serialize
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    val articles = mutableListOf<Items>()
    val product = mutableListOf<Items>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val postServices = DataRepository.create()
        val progressBar: ProgressBar = this.progress_circular
        val rlProgressBar : RelativeLayout = this.rlProgress
        val ivError : ImageView = this.ivError
        val rvList : RecyclerView = this.rvList
        val rvGrid : RecyclerView = this.rvGrid
        postServices.getData().enqueue(object : Callback<Serialize> {

            override fun onResponse(call: Call<Serialize>, response: Response<Serialize>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    for (i in 0..data?.dataModel?.count()!!-1) {
                        for(y in 0..data?.dataModel?.get(i)?.items?.count()!!-1){
                            if(data?.dataModel?.get(i).section == "articles"){
                                var articleImage = data?.dataModel?.get(i)?.items?.get(y)?.article_image;
                                var article_title = data?.dataModel?.get(i)?.items?.get(y)?.article_title;
                                var article_link = data?.dataModel?.get(i)?.items?.get(y)?.link;
                                var obj = Items(article_image = articleImage, article_title = article_title, link = article_link, product_image = "", product_name = "")
                                articles.add(obj)
                            }else{
                                var product_image = data?.dataModel?.get(i)?.items?.get(y)?.product_image;
                                var product_name = data?.dataModel?.get(i)?.items?.get(y)?.product_name;
                                var prodcut_link = data?.dataModel?.get(i)?.items?.get(y)?.link;
                                var obj = Items(product_image = product_image, product_name = product_name, link = prodcut_link, article_title = "", article_image = "")
                                product.add(obj)
                            }
                        }
                    }
                }
                val articleAdapter = ArticleAdapter(articles)
                val productAdapter = ProductAdapter(product)
                rvGrid.apply {
                    layoutManager = GridLayoutManager(this@MainActivity,3)
                    adapter = productAdapter
                }
                rvList.apply {
                    layoutManager = LinearLayoutManager(this@MainActivity)
                    adapter = articleAdapter
                }
                rlProgressBar.setVisibility(View.GONE);
            }

            override fun onFailure(call: Call<Serialize>, error: Throwable) {
                progressBar.setVisibility(View.GONE);
                ivError.setVisibility(View.VISIBLE);
                Toast.makeText(this@MainActivity, "Connection Error", Toast.LENGTH_LONG).show()
            }
        })
    }
}

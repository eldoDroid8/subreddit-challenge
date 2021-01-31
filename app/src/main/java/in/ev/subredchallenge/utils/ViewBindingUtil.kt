package `in`.ev.subredchallenge.utils

import `in`.ev.subredchallenge.ui.home.HomeItemViewModel
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


/*@BindingAdapter("homeAdapter","itemClickListener", requireAll = false)
fun addDataToCharacterList(
    recyclerView: RecyclerView,
    homeData: MutableList<HomeItemViewModel>?
) {
    *//*try {
        homeData?.let {
            val adapter =
                    RedditPostAdapter(R.layout.list_item_home ,homeData, POST_DIFF_UTIL )
            recyclerView.adapter = adapter
            //adapter.setItemClickListener(clickListener)
            //adapter.addRecylerViewListData(it)
        }
    } catch (e: ClassCastException) {
        e.printStackTrace()
    }*//*
}*/

@BindingAdapter("imageUrl", "placeholderData", requireAll = false)
fun setImageUrl(imageView: ImageView, url: String?, placeholderIcon: Drawable) {
    val context = imageView.context
    if(url?.startsWith("http") == true) {
        Glide.with(context).load(url)
            .centerInside()
            .placeholder(placeholderIcon)
            .into(imageView)
    } else {
        imageView.visibility = View.INVISIBLE
    }
}


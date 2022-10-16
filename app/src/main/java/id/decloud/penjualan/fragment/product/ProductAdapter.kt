package id.decloud.penjualan.fragment.product

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.decloud.common.entity.api.product.Content
import id.decloud.penjualan.databinding.ProductListLayoutItemBinding

class ProductAdapter(
    val toDetail: (Content) -> Unit,
    val addToCart: (Content) -> Unit
) : PagingDataAdapter<Content, ProductViewHolder>(differProduct) {


    companion object {
        val differProduct = object : DiffUtil.ItemCallback<Content>() {
            override fun areItemsTheSame(oldItem: Content, newItem: Content): Boolean {
                return oldItem.productCode == newItem.productCode
            }

            override fun areContentsTheSame(
                oldItem: Content,
                newItem: Content
            ): Boolean {
                return oldItem.productCode == newItem.productCode
            }

        }
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val data = getItem(position)
        holder.binding.data = data
        holder.binding.data = data
        holder.binding.price.text = data?.currency + ". " + data?.price + ",-"
        if (data?.discount?.toInt() != 0) {
            holder.binding.priceDiscounted.text = "${data?.currency}. ${
                (data?.let {
                    (it.price) - (it.price * it.discount / 100)
                })
            },-"
            holder.binding.priceDiscounted.visibility = View.VISIBLE
            holder.binding.price.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            holder.binding.priceDiscounted.visibility = View.GONE
        }

        holder.binding.card.setOnClickListener {
            data?.let { it -> toDetail(it) }
        }
        holder.binding.buyButton.setOnClickListener {
            try {
                data?.let { it -> addToCart(it) }
                Toast.makeText(
                    holder.binding.root.context,
                    "telah di tambah ke cart",
                    Toast.LENGTH_SHORT
                ).show()

            } catch (e:Exception){
                Toast.makeText(
                    holder.binding.root.context,
                    "gagal di tambahkan ke cart",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder =
        ProductViewHolder(
            ProductListLayoutItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

}

class ProductViewHolder(
    val binding: ProductListLayoutItemBinding
) :
    RecyclerView.ViewHolder(binding.root)

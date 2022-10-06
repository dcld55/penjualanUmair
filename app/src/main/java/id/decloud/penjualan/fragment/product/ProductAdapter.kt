package id.decloud.penjualan.fragment.product

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.decloud.common.entity.ProductEntity
import id.decloud.penjualan.databinding.ProductListLayoutItemBinding

class ProductAdapter(
//    val toDetail: (ProductEntity) -> Unit
) : RecyclerView.Adapter<ProductViewHolder>() {

    val differ = AsyncListDiffer(this, differProduct)

    companion object {
        val differProduct = object : DiffUtil.ItemCallback<ProductEntity>() {
            override fun areItemsTheSame(oldItem: ProductEntity, newItem: ProductEntity): Boolean {
                return oldItem.productCode == newItem.productCode
            }

            override fun areContentsTheSame(
                oldItem: ProductEntity,
                newItem: ProductEntity
            ): Boolean {
                return oldItem.productCode == newItem.productCode
            }

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder =
        ProductViewHolder(
            ProductListLayoutItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), differ
        )

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val data = differ.currentList[position]
        holder.binding.data = data
        holder.binding.price.text = data.currency + ". " + data.price + ",-"
        if(data.discount.toInt() != 0){
            holder.binding.priceDiscounted.text = "${data.currency}. ${(data.price)-(data.price*data.discount/100)},-"
            holder.binding.priceDiscounted.visibility = View.VISIBLE
            holder.binding.price.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            holder.binding.priceDiscounted.visibility = View.GONE
        }

    }

    override fun getItemCount(): Int = differ.currentList.size

}

class ProductViewHolder(
    val binding: ProductListLayoutItemBinding,
    val differ: AsyncListDiffer<ProductEntity>
) :
    RecyclerView.ViewHolder(binding.root)

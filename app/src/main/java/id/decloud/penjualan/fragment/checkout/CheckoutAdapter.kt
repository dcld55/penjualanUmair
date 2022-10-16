package id.decloud.penjualan.fragment.checkout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.decloud.common.Count
import id.decloud.common.entity.room_table.CartEntity
import id.decloud.penjualan.databinding.CheckoutLayoutItemBinding


class CheckoutAdapter(val setTotal: (CartEntity) -> Unit) :
    RecyclerView.Adapter<CheckoutViewHolder>() {


    val differ = AsyncListDiffer(this, cartDiffer)


    companion object {
        val cartDiffer = object : DiffUtil.ItemCallback<CartEntity>() {
            override fun areItemsTheSame(oldItem: CartEntity, newItem: CartEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: CartEntity, newItem: CartEntity): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckoutViewHolder =
        CheckoutViewHolder(
            CheckoutLayoutItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CheckoutViewHolder, position: Int) {
        val data = differ.currentList[position]
        holder.binding.data = data


        holder.binding.quantity.setText("1")

        holder.binding.subtotalprice.text = Count.getSubTotal(data).toString()
        holder.binding.quantity.addTextChangedListener {
            val totalPrice = if (!it.isNullOrBlank()) {
                data.quantity = it.toString().toInt()
                Count.getSubTotal(data)
            } else {
                0.0
            }
            holder.binding.minus.isEnabled = it.toString().toInt() > 1
            holder.binding.subtotalprice.text = totalPrice.toString()
            setTotal(data)
        }

        holder.binding.plus.setOnClickListener {
            data.quantity += 1
            holder.binding.quantity.setText(data.quantity.toString())
        }
        holder.binding.minus.setOnClickListener {
            data.quantity -= 1
            holder.binding.quantity.setText(data.quantity.toString())

        }
    }

    override fun getItemCount(): Int = differ.currentList.size
}

class CheckoutViewHolder(
    val binding: CheckoutLayoutItemBinding
) : RecyclerView.ViewHolder(binding.root)

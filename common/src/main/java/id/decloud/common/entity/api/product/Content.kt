package id.decloud.common.entity.api.product

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Content(
    val currency: String,
    val dimension: String,
    val discount: Double,
    val name: String,
    val price: Double,
    val productCode: String,
    val unit: String
) : Parcelable
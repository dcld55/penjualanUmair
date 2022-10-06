package id.decloud.api_service.service.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.decloud.common.entity.ProductEntity

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertProduct(productEntity: ProductEntity)

    @Query("SELECT * FROM product")
    fun getAllProduct() : LiveData<List<ProductEntity>>

    @Query("SELECT * FROM product WHERE product_code=:code")
    fun getOneProduct(code: String) : LiveData<ProductEntity>
}
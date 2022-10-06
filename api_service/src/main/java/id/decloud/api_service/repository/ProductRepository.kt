package id.decloud.api_service.repository

import id.decloud.api_service.service.local.ProductDao
import id.decloud.common.ext.AppExecutors
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import id.decloud.common.entity.ProductEntity
import id.decloud.common.ui.AppResponse

class ProductRepository(
    private val productDao: ProductDao,
    private val appExecutors: AppExecutors
) {

    private val hasilProduk = MediatorLiveData<List<ProductEntity>>()

    fun getAllProduct():LiveData<List<ProductEntity>> {
        val localdata = productDao.getAllProduct()
        val localdata2 = productDao.getAllProduct()

        hasilProduk.addSource(localdata){
            if (it.isEmpty()){
                appExecutors.diskIO.execute {
                    productDao.insertProduct(ProductEntity("SKLNPMT","So Klin Pemutih",15000,"IDR",10,"13 cm x 10 cm","Pcs"))
                    productDao.insertProduct(ProductEntity("MIEGORG","Mie Sedap Goreng Original",3000,"IDR",0,"15 cm x 10 cm","Pcs"))

                }
            }
        }

        hasilProduk.addSource(localdata2){
            hasilProduk.value =it
        }

        return hasilProduk
    }


}
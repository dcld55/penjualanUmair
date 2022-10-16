package id.decloud.api_service.data_source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import id.decloud.api_service.service.api.ProductService
import id.decloud.common.entity.api.product.Content

class ProductPagingDataSource(
    private val productService: ProductService,
    private val token: String
) : PagingSource<Int, Content>() {

    override fun getRefreshKey(state: PagingState<Int, Content>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Content> {
        return try {
            val content = productService.getProduct(
                token = token,
                page = params.key ?: 1
            )
            if (content.isSuccessful) {
                content.body()?.let {
                    LoadResult.Page(
                        data = it.content,
                        prevKey = if (it.pageable.pageNumber + 1 == 1) null else it.pageable.pageNumber - 1,
                        nextKey = if (it.pageable.pageNumber + 1 == it.totalPages) null else it.pageable.pageNumber + 2
                    )
                } ?: LoadResult.Error(Exception("Error Backend: ${content.code()}"))
            } else {
                LoadResult.Error(Exception("Error Backend: ${content.code()}"))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}

object ProductPager {
    fun createPager(
        pageSize: Int,
        productService: ProductService,
        token: String
    ): Pager<Int, Content> = Pager(
        config = PagingConfig(pageSize),
        pagingSourceFactory = { ProductPagingDataSource(productService,token) }
    )
}
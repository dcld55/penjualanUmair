package id.decloud.api_service.usecase

import id.decloud.api_service.data_source.ProductPager
import id.decloud.api_service.service.api.ProductService
import id.decloud.common.Constant

class ProductUseCase(
    private val productService: ProductService
) {
    operator fun invoke(token: String) =
        ProductPager.createPager(Constant.BASE_PAGE_SIZE, productService, token).flow
}
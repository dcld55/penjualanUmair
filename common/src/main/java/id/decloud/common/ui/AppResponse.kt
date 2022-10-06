package id.decloud.common.ui

open class AppResponse<T> {
    companion object {
        fun <T> success(value: T): AppResponse<T> =
            Success(value)

        fun <T> failure(exception: Throwable?): AppResponse<T> =
            Error(exception)

        fun <T> loading() = AppResponseLoading<T>()
    }

    class Error<T>(val e: Throwable?) : AppResponse<T>()

    class Success<T>(val data: T) : AppResponse<T>()

    class AppResponseLoading<T> : AppResponse<T>()
}
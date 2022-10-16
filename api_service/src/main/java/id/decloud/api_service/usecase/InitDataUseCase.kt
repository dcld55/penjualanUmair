package id.decloud.api_service.usecase

import id.decloud.api_service.data_source.DataStorePreference
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class InitDataUseCase @Inject constructor(
    val dataStorePreference: DataStorePreference

) {
    operator fun invoke() = flow {
        val touple = dataStorePreference.getUser() to
        dataStorePreference.getToken()
        emit(touple)
    }
}
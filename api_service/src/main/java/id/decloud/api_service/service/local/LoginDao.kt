package id.decloud.api_service.service.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.decloud.common.entity.LoginEntity

@Dao
interface LoginDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertLogin(loginEntity: LoginEntity)

    @Query("SELECT * FROM login WHERE user=:user")
    fun getLogin(user: String) : LiveData<LoginEntity>
}
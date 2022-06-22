package me.dio.sqlite_com_room

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.dio.sqlite_com_room.data.UserDatabase
import me.dio.sqlite_com_room.data.UserRepository
import me.dio.sqlite_com_room.data.Usuario

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val selectUsers: LiveData<List<Usuario>>
    val repository: UserRepository

    init {

        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        selectUsers = repository.selectUsers
    }

    fun addUser(usuario: Usuario){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(usuario)

        }
    }
}
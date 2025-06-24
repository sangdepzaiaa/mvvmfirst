package com.example.myapplication.room.data

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.room.data.local.MyRoomDatabase
import com.example.myapplication.room.data.local.entity.noteEntity
import com.example.myapplication.room.data.local.entity.userAndnote
import com.example.myapplication.room.data.local.entity.userEntity
import kotlinx.coroutines.launch

private const val USER_ID = 1

class DemoRoomViewModel(application: Application) : AndroidViewModel(application) {
    private val appDb = MyRoomDatabase.getInstance(application)

    val userAndNotesLiveData: LiveData<userAndnote?> =
        appDb.noteD()
            .observeuserAndnoteByuserId(userid = USER_ID)

    init {
        viewModelScope.launch {
            // handle error => try/catch

            val found = appDb.userD().findByid(userid = USER_ID)

            if (found == null) {
                appDb.userD().insert(
                    userEntity(
                        id = USER_ID,
                        name = "Percy Woodard"
                    )
                )

                // main thread
                Toast.makeText(
                    getApplication(),
                    "Inserted user with id $USER_ID",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    fun addNewData() {
        viewModelScope.launch {
            val notes = List(10) {
                noteEntity(
                    id = null, // auto-generate
                    userid = USER_ID,
                    title = "Title #$it"
                )
            }

            // should handle error => try/catch
            appDb.noteD().insertAll(notes)

            // main thread
            Toast.makeText(
                getApplication(),
                "Inserted 10 notes for user with id $USER_ID",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun delete() {
        viewModelScope.launch {
            // should handle error => try/catch
            //appDb.noteDao().deleteNotesByUserIds(userId = USER_ID)

            // delete based on user id
            appDb.userD().delete(userEntity(id = USER_ID, name = "") )

            // main thread
            Toast.makeText(
                getApplication(),
                "Deleted all notes for user with id $USER_ID",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
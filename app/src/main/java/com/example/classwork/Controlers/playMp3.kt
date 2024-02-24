package com.example.classwork.Controlers

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.classwork.CommonTB.USERMUSIC
import com.example.classwork.CommonTB.USERS
import com.example.classwork.data.UserMusic
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.storage.FirebaseStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class playMp3 @Inject constructor(
    val auth: FirebaseAuth,
    val db: FirebaseFirestore,
    val storage: FirebaseStorage
):ViewModel() {
    val state = mutableStateOf<List<UserMusic>>(emptyList())
    val inProgress = mutableStateOf(false)

    val musicImg = mutableStateOf("")
    val musicUrl = mutableStateOf("")
    val name = mutableStateOf("")
    val description = mutableStateOf("")
    val uploader = mutableStateOf("")
    val userId = mutableStateOf("")

    fun getData(mp3Id: String){
        viewModelScope.launch {
            state.value= getDataFromFireStore(mp3Id = mp3Id)
        }
    }
   private fun getDataFromFireStore(
        mp3Id:String
    ): List<UserMusic> {
        val userMusicList = mutableListOf<UserMusic>()
       inProgress.value=true
        try {
            db.collection(USERMUSIC).document(mp3Id).get().addOnSuccessListener {
                val dbOutcome = it.data

                musicImg.value = dbOutcome?.get("musicImg") as? String ?:""
                musicUrl.value = dbOutcome?.get("musicUrl") as? String ?:""
                name.value = dbOutcome?.get("name") as? String ?:""
                description.value = dbOutcome?.get("description") as? String ?:""
                userId.value=dbOutcome?.get("uid") as String?:""

                db.collection(USERS).document(userId.value).get().addOnSuccessListener{user->
                    val dbUserOutcome = user.data
                    uploader.value = dbUserOutcome?.get("username") as? String?:""
                }

                Log.d("outcome","message:${musicImg.value},${musicUrl.value},${name.value},${description.value}")
            }
            inProgress.value=false
        } catch (e: FirebaseFirestoreException) {
            inProgress.value=false
            Log.d("error", "getDataFromFireStore: $e")
        }
        return userMusicList
    }
}
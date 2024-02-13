package com.example.classwork.Controlers

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.classwork.CommonTB.USERMUSIC
import com.example.classwork.data.Event
import com.example.classwork.data.MusicItems
import com.example.classwork.data.UserMusic
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.toObject
import com.google.firebase.firestore.toObjects
import com.google.firebase.storage.FirebaseStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlin.math.exp

@HiltViewModel
class GetUserMusicViewModel @Inject constructor(
    val auth: FirebaseAuth,
    val db: FirebaseFirestore,
    val storage: FirebaseStorage
) : ViewModel() {
        val state = mutableStateOf<List<UserMusic>>(emptyList())
        val inProgress = mutableStateOf(false)
    init{
        getData()
    }
    private fun getData(){
        viewModelScope.launch {
            state.value= getDataFromFireStore()
        }
    }
    suspend fun getDataFromFireStore(): List<UserMusic> {
        val userMusicList = mutableListOf<UserMusic>()
        try {
            val querySnapshot = db.collection("userMusic").get().await()
            for (document in querySnapshot) {
                val result = document.toObject(UserMusic::class.java)
                userMusicList.add(result)
            }
        } catch (e: FirebaseFirestoreException) {
            Log.d("error", "getDataFromFireStore: $e")
        }
        return userMusicList
    }
}
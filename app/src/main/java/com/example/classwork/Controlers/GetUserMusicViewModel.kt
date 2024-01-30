package com.example.classwork.Controlers

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.classwork.CommonTB.USERMUSIC
import com.example.classwork.data.Event
import com.example.classwork.data.MusicItems
import com.example.classwork.data.UserMusic
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObjects
import com.google.firebase.storage.FirebaseStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.math.exp

@HiltViewModel
class GetUserMusicViewModel @Inject constructor(
    val auth: FirebaseAuth,
    val db: FirebaseFirestore,
    val storage: FirebaseStorage
) : ViewModel() {
        val musicFeed = mutableStateOf<List<UserMusic>>(listOf())
        val inProgress = mutableStateOf(false)
        val popupNotification = mutableStateOf<Event<String>?>(null)

    fun getUserMusic() {
        inProgress.value = true
        db.collection(USERMUSIC).get().addOnSuccessListener {
            val music = it.toObjects<UserMusic>()
            musicFeed.value = music
            inProgress.value = false

            Log.d("TAG","music:$musicFeed")
        }.addOnFailureListener { exception ->
            handleException(exception, "Cannot get user music")
            inProgress.value = false
        }
    }

    fun handleException(exception: Exception? = null, customMessage: String = "") {
        exception?.printStackTrace()
        val errorMsg = exception?.localizedMessage ?: ""
        val message = if (customMessage.isEmpty()) errorMsg else "$customMessage: $errorMsg"
        popupNotification.value = Event(message)
    }
}
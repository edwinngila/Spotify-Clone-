package com.example.classwork.Controlers

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.classwork.CommonTB.USERMUSIC
import com.example.classwork.data.Event
import com.example.classwork.data.UserMusic
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class UploadMusicViewModel @Inject constructor(
    val auth: FirebaseAuth,
    val db : FirebaseFirestore,
    val storage: FirebaseStorage
):ViewModel(){
    val popupNotification = mutableStateOf<Event<String>?>(null)
    val inProgress = mutableStateOf(false)
    val userMusic = mutableStateOf<UserMusic?>(null)

    var userMusicUri = mutableStateOf<String?>(null)
    var userMusicImg = mutableStateOf<String?>(null)

   private fun uploadMusic(uri :Uri,onSuccess: (Uri) -> Unit) {
       inProgress.value=true
       val storageRef = storage.reference
       val uuid = UUID.randomUUID()
       val audioRef = storageRef.child("audio/$uuid")
       val uploadTask = audioRef.putFile(uri)

       uploadTask
           .addOnSuccessListener {
               val result = it.metadata?.reference?.downloadUrl
               result?.addOnSuccessListener(onSuccess)
               inProgress.value=false
           }
           .addOnFailureListener {
               notification(message = "Something went wrong")
           }

   }

    private fun uploadMusicImage(uri :Uri,onSuccess: (Uri) -> Unit) {
        inProgress.value=true
       val storageRef = storage.reference
       val uuid = UUID.randomUUID()
       val audioRef = storageRef.child("image/$uuid")
       val uploadTask = audioRef.putFile(uri)

       uploadTask
           .addOnSuccessListener {
               viewModelScope.launch (Dispatchers.IO){
                   try {
                       val downloadUrl = getDownloadUrl(it.metadata?.reference!!)
                       onSuccess(downloadUrl)
                   } catch (e: Exception) {
                       notification(message = "Error getting download URL")
                   } finally {
                       inProgress.value = false
                   }
               }
           }
           .addOnFailureListener {
               notification(message = "Something went wrong")
           }
   }
    private fun notification(message:String){
        popupNotification.value=Event(message)
    }
    private suspend fun getDownloadUrl(reference: StorageReference): Uri {
        return reference.downloadUrl.await()
    }
    private fun createAudio(
        uid: String?=null,
        name: String?=null,
        description: String?=null,
        musicUrl:  MutableState<String?>,
        musicImg:  MutableState<String?>
    ){
        val uid = auth.currentUser?.uid
        val audioFile = UserMusic(
            uid = uid,
            name = name,
            description = description,
            musicUrl = musicUrl.value,
            musicImg = musicImg.value
        )
        uid?.let { uid ->
            inProgress.value = true
            db.collection(USERMUSIC).add(audioFile.toMap()).addOnSuccessListener{
                inProgress.value = false
                popupNotification.value = Event("Audio uploaded successfully")
            }.addOnFailureListener {
                inProgress.value = false
                popupNotification.value = Event("Something went wrong")
            }
        }

    }
    fun saveAudioFiles(uri: Uri){
        uploadMusic(uri){ audioUrl->
            userMusicUri.value = audioUrl.toString()
            Log.d("Tag","SaveAudioFile: ${userMusicUri.value}")
        }
    }
    fun saveAudioImg(uri: Uri){
        uploadMusicImage(uri){audioImage->
            userMusicImg.value = audioImage.toString()
            Log.d("TAG", "saveAudioImg: ${userMusicImg.value}")
        }
    }
    fun saveAudioInfo(MusicName:String,Description:String,){
        Log.d("TAG", "MusicName: ${MusicName}")
        Log.d("TAG", "Description: ${Description}")
        createAudio(
            name = MusicName,
            description = Description,
            musicUrl=userMusicUri,
            musicImg=userMusicImg
        )
    }
    fun handleException(exception: Exception? = null, customMessage: String = "") {
        exception?.printStackTrace()
        val errorMsg = exception?.localizedMessage ?: ""
        val message = if (customMessage.isEmpty()) errorMsg else "$customMessage: $errorMsg"
        popupNotification.value = Event(message)
    }
}
package com.heathkev.quizapp.firebase

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.heathkev.quizapp.data.QuizListModel

private const val TAG = "FirebaseRepository"

class FirebaseRepository {

    private val firebaseFireStore = FirebaseFirestore.getInstance()
    private val quizRef = firebaseFireStore.collection("QuizList")

    fun getQuizList(): LiveData<List<QuizListModel>> {
        val quizListModelListData = MutableLiveData<List<QuizListModel>>()
        quizRef.addSnapshotListener(EventListener { value, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                quizListModelListData.value = null
                return@EventListener
            }

            val quizListModelList: MutableList<QuizListModel> = mutableListOf()
            for (doc in value!!) {
                val quizItem = doc.toObject(QuizListModel::class.java)
                quizListModelList.add(quizItem)
            }
            quizListModelListData.value = quizListModelList
        })

        return quizListModelListData
    }


    fun getQuestion(quizId: String): CollectionReference {
        return quizRef.document(quizId).collection("Questions")
    }
}
package com.heathkev.quizapp.ui.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.heathkev.quizapp.data.QuizListModel

class QuizViewModelFactory(
    private val quizListModel: QuizListModel
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuizViewModel::class.java)) {
            return QuizViewModel(quizListModel) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


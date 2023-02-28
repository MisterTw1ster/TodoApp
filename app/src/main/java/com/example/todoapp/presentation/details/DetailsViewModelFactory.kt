//package com.example.todoapp.presentation.details
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.example.todolistsandtasks.domain.usecase.tasks.DeleteTaskByIdUseCase
//import com.example.todolistsandtasks.domain.usecase.tasks.GetTaskByIdUseCase
//import com.example.todolistsandtasks.domain.usecase.tasks.AddNewTaskUseCase
//import com.example.todolistsandtasks.domain.usecase.tasks.EditTaskUseCase
//import com.example.todolistsandtasks.presentation.utils.LongDateToString
//import dagger.assisted.Assisted
//import dagger.assisted.AssistedFactory
//import dagger.assisted.AssistedInject
//
//class DetailsViewModelFactory @AssistedInject constructor(
//    @Assisted("taskId") private val taskId: String?,
//    @Assisted("communication") private val communicationDetails: CommunicationDetails,
//    private val addNewTaskUseCase: AddNewTaskUseCase,
//    private val editTaskUseCase: EditTaskUseCase,
//    private val getTaskById: GetTaskByIdUseCase,
//    private val deleteTaskById: DeleteTaskByIdUseCase,
//    private val longDateToString: LongDateToString
//) : ViewModelProvider.Factory {
//
//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return DetailsViewModel(
//            taskId,
//            communicationDetails,
//            addNewTaskUseCase,
//            editTaskUseCase,
//            getTaskById,
//            deleteTaskById,
//            longDateToString
//        ) as T
//    }
//
//    @AssistedFactory
//    interface Factory {
//        fun create(
//            @Assisted("taskId") taskId: String?,
//            @Assisted("communication") communicationDetails: CommunicationDetails
//        ): DetailsViewModelFactory
//    }
//
//}
package com.example.todoapp.datasource.auth.cloud

import com.example.todoapp.datasource.auth.cloud.mappers.UserCloudToDataMappers
import com.example.todoapp.datasource.auth.cloud.models.SignInRequest
import com.example.todoapp.datasource.auth.cloud.models.SignUpRequest
import com.example.todoapp.di.DataScope
import com.example.todoapp.models.UserData
import com.example.todoapp.repository.auth.AuthCloudDataSource
import org.json.JSONObject
import javax.inject.Inject

@DataScope
class AuthCloudDataSourceImpl @Inject constructor(
    private val api: AuthService,
    private val userCloudToDataMappers: UserCloudToDataMappers
) : AuthCloudDataSource {

    override suspend fun signUpWithEmail(email: String, password: String): UserData {
        val signUpRequest = SignUpRequest(email = email, password = password)
        val userCloud = api.singUpWithEmail(signUpRequest)
        return userCloudToDataMappers.transform(userCloud)
    }

    override suspend fun signInWithEmail(email: String, password: String): UserData {
        val signInRequest = SignInRequest(email = email, password = password)
        val response = api.singInWithEmail(signInRequest)
        return if (response.code() == 200 && response.body() != null) {
            val userCloud = response.body()!!
           userCloudToDataMappers.transform(userCloud)
        } else {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            val message = (errorObj["error"] as JSONObject).getString("message")
            throw IllegalArgumentException(message)
        }

//        val response = api.singInWithEmail(signInRequest)
//        if (response.isSuccessful && response.body() != null) {
//            return userCloudToDataMappers.transform(response.body()!!)
//        }
//        else if(response.errorBody()!=null){
//            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
//            _userResponseLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
//        }
//        else{
//            _userResponseLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
//        }
//        val d: String = response.errorBody()!!.charStream().readText()
//        println(d)
//        throw IllegalStateException()

    }

}


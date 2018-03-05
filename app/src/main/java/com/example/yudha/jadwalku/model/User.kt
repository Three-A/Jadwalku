package com.example.yudha.jadwalku.model

/**
 * Created by yudha on 3/4/2018.
 */

data class User(
		val status: Boolean,
		val message: String,
		val data: Data
)

data class Data(
		val _id: String,
		val email: String,
		val password: String,
		val name: String,
		val phoneNumber: String,
		val __v: Int
)
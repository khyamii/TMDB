package com.example.tmdb.networking.entity

import com.google.gson.annotations.SerializedName


class BaseErrorResponse(
    @SerializedName("message")
    val message: String
)
package com.example.blocksapplication.data.remote

import com.example.blocksapplication.utils.Constants.CODE
import com.example.blocksapplication.data.response.BlockResponse
import retrofit2.http.GET

interface Service {

    @GET(CODE)
    suspend fun getBlocks(): BlockResponse
}
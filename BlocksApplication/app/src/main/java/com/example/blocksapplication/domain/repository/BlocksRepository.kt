package com.example.blocksapplication.domain.repository

import com.example.blocksapplication.data.response.BlockResponse


interface BlocksRepository {

    suspend fun getBlocks(): BlockResponse
}
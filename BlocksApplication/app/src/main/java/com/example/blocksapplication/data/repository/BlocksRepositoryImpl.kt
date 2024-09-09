package com.example.blocksapplication.data.repository

import com.example.blocksapplication.data.remote.Service
import com.example.blocksapplication.data.response.BlockResponse
import com.example.blocksapplication.domain.repository.BlocksRepository
import javax.inject.Inject

class BlocksRepositoryImpl @Inject constructor(private val service: Service): BlocksRepository {

    override suspend fun getBlocks(): BlockResponse = service.getBlocks()
}
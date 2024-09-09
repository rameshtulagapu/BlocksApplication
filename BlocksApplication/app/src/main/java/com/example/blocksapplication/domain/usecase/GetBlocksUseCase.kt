package com.example.blocksapplication.domain.usecase

import com.example.blocksapplication.data.mapper.toBlockUiModel
import com.example.blocksapplication.utils.Resource
import com.example.blocksapplication.domain.repository.BlocksRepository
import com.example.blocksapplication.domain.uimodel.BlockUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetBlocksUseCase @Inject constructor(
    private val blocksRepository: BlocksRepository
) {
    val blocks: Flow<Resource<List<BlockUiModel>>> = flow {

        try {
            emit(Resource.Loading)
            emit(Resource.Success(blocksRepository.getBlocks().map { it.toBlockUiModel() }))
        } catch (e: Exception) {
            emit(Resource.Error("Error !!"))
        }
    }
}
package com.marcelo.core.usecase.base

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

abstract class UseCase<in Params, Result> {

    operator fun invoke(params: Params): Flow<ResultStatus<Result>> = flow {
        emit(ResultStatus.Loading)
        emit(doWork(params))
    }.catch { throwable ->
        emit (ResultStatus.Error(throwable))
    }

    protected abstract suspend fun doWork(params: Params): ResultStatus<Result>
}

abstract class PagingUseCase<in Params, Result : Any> {

    operator fun invoke(params: Params): Flow<PagingData<Result>> = createFlowObservable(params)

    protected abstract fun createFlowObservable(params: Params): Flow<PagingData<Result>>
}
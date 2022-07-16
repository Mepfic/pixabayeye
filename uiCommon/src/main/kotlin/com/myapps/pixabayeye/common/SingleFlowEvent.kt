package com.myapps.pixabayeye.common

import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.receiveAsFlow

@Suppress("FunctionName")
fun <T> MutableCachedSharedFlow(): MutableSharedFlow<T> =
    MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

/** Behaviour like in SingleLiveEvent */
class SingleFlowEvent<T> : Flow<T> {
    private val channelEvent = Channel<T>(Channel.CONFLATED)
    private val flowEvent = channelEvent.receiveAsFlow()

    suspend fun emit(event: T) {
        channelEvent.send(event)
    }

    @InternalCoroutinesApi
    override suspend fun collect(collector: FlowCollector<T>) {
        flowEvent.collect(collector)
    }
}

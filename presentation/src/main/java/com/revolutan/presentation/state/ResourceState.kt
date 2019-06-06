package com.revolutan.presentation.state


sealed class ResourceState {
    object SUCCESS : ResourceState()
    object ERROR : ResourceState()
    object LOADING : ResourceState()
}

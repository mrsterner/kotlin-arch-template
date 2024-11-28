package dev.sterner.modid.registry

import dev.architectury.platform.Platform


object ModRenderTypes {

    val BUFFER_SIZE = if (Platform.isModLoaded("sodium")) {
        262144
    } else {
        256
    }

    init {

    }
}
package dev.sterner.modid.registry

import com.google.common.base.Suppliers
import dev.architectury.registry.registries.RegistrarManager
import dev.sterner.modid.ModMain.MODID
import net.minecraft.core.registries.Registries
import java.util.function.Supplier


object ModAttributes {

    val MANAGER: Supplier<RegistrarManager> = Suppliers.memoize {
        RegistrarManager.get(
            MODID
        )
    }

    var attributes = MANAGER.get().get(Registries.ATTRIBUTE)

}
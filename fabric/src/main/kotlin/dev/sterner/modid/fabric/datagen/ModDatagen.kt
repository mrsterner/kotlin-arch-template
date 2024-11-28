package dev.sterner.modid.fabric.datagen

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.minecraft.core.RegistrySetBuilder

class ModDatagen : DataGeneratorEntrypoint {

    override fun onInitializeDataGenerator(generator: FabricDataGenerator) {
        val pack = generator.createPack()

        pack.addProvider { output, reg ->
            ModBlockTagProvider(output, reg)
        }
        pack.addProvider { output, reg ->
            ModItemTagProvider(output, reg)
        }
        pack.addProvider { output, _ ->
            ModModelProvider(output)
        }
        pack.addProvider { output, reg ->
            ModLangProvider(output, reg)
        }
        pack.addProvider { output, reg ->
            ModRecipeProvider(output, reg)
        }
        pack.addProvider { output, reg ->
            ModAdvancementProvider(output, reg)
        }
        pack.addProvider { output, reg ->
            ModBlockLootProvider(output, reg)
        }
        pack.addProvider { output, reg ->
            ModEntityLootProvider(output, reg)
        }
        pack.addProvider { output, reg ->
            ModEntityTypeTagProvider(output, reg)
        }

    }

    override fun buildRegistry(registryBuilder: RegistrySetBuilder) {
        super.buildRegistry(registryBuilder)
    }
}
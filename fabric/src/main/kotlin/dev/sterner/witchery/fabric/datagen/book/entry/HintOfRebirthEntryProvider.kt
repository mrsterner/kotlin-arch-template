package dev.sterner.witchery.fabric.datagen.book.entry

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase
import com.klikli_dev.modonomicon.api.datagen.EntryBackground
import com.klikli_dev.modonomicon.api.datagen.EntryProvider
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel
import com.klikli_dev.modonomicon.api.datagen.book.page.BookTextPageModel
import com.mojang.datafixers.util.Pair
import dev.sterner.witchery.registry.WitcheryItems

class HintOfRebirthEntryProvider (parent: CategoryProviderBase?) : EntryProvider(parent) {

    companion object {
        val ID = "hint_of_rebirth"
    }

    override fun generatePages() {
        this.page(ID) {
            BookTextPageModel.create()
                .withTitle("$ID.title")
                .withText("$ID.page1")
        }
    }

    override fun entryName(): String {
        return ID.replaceFirstChar { it.uppercaseChar() }
    }

    override fun entryDescription(): String {
        return ""
    }

    override fun entryBackground(): Pair<Int, Int> {
        return EntryBackground.DEFAULT
    }

    override fun entryIcon(): BookIconModel {
        return BookIconModel.create(WitcheryItems.HINT_OF_REBIRTH.get())
    }

    override fun entryId(): String {
        return ID
    }
}
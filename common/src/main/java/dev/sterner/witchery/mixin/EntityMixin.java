package dev.sterner.witchery.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import dev.sterner.witchery.platform.poppet.VoodooPoppetData;
import dev.sterner.witchery.platform.poppet.VoodooPoppetDataAttachment;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityMixin {

    @Inject(method = "isInWaterRainOrBubble", at = @At("RETURN"), cancellable = true)
    private void isInWaterRainOrBubble(CallbackInfoReturnable<Boolean> info) {
        if (!info.getReturnValue()) {
            Entity entity = (Entity) (Object) this;
            if (entity instanceof LivingEntity living) {
                VoodooPoppetData data = VoodooPoppetDataAttachment.getPoppetData(living);
                if (data.isUnderWater()) {
                    info.setReturnValue(true);
                }
            }
        }
    }

    @ModifyReturnValue(method = "isEyeInFluid", at = @At("RETURN"))
    private boolean isEyeInFluid(boolean original) {
        Entity entity = (Entity) (Object) this;
        if (entity instanceof LivingEntity living) {
            if (VoodooPoppetDataAttachment.getPoppetData(living).isUnderWater()) {
                return true;
            }
        }

        return original;
    }
}
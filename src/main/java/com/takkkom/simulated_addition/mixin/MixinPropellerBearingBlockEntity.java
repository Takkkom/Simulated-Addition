package com.takkkom.simulated_addition.mixin;

import com.takkkom.simulated_addition.helper.SailHelper;
import dev.eriksonn.aeronautics.content.blocks.propeller.bearing.propeller_bearing.PropellerBearingBlockEntity;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PropellerBearingBlockEntity.class)
public class MixinPropellerBearingBlockEntity {
    @Inject(method = "getSailPower", at = @At("RETURN"), cancellable = true)
    public void simulatedAddition$getSailPower(final StructureTemplate.StructureBlockInfo info, CallbackInfoReturnable<Float> cir) {
        float originalSailPower = cir.getReturnValue();
        float sailPowerMul = SailHelper.GetSailPowerMul(info.state());

        float multipliedSailPower = originalSailPower * sailPowerMul;
        cir.setReturnValue(multipliedSailPower);
    }
}

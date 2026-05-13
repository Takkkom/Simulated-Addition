package com.takkkom.simulated_addition.mixin;

import com.simibubi.create.content.contraptions.bearing.BearingContraption;
import com.takkkom.simulated_addition.helper.SailHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.apache.commons.lang3.tuple.Pair;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BearingContraption.class)
public class MixinBearingContraption {
    @Shadow
    protected int sailBlocks;

    @Unique
    private int simulatedAddition$prevSailBlocks;

    @Unique
    private float simulatedAddition$sailPower;

    @Inject(method = "addBlock", at = @At("HEAD"))
    public void simulatedAddition$addBlockHEAD(Level level, BlockPos pos, Pair<StructureTemplate.StructureBlockInfo, BlockEntity> capture, CallbackInfo ci) {
        simulatedAddition$prevSailBlocks = sailBlocks;
    }
    @Inject(method = "addBlock", at = @At("TAIL"))
    public void simulatedAddition$addBlockTAIL(Level level, BlockPos pos, Pair<StructureTemplate.StructureBlockInfo, BlockEntity> capture, CallbackInfo ci) {
        if (simulatedAddition$prevSailBlocks < sailBlocks) {
            simulatedAddition$sailPower += SailHelper.GetSailPowerMul(capture.getLeft().state());
            sailBlocks = (int)simulatedAddition$sailPower;
        }
    }
}

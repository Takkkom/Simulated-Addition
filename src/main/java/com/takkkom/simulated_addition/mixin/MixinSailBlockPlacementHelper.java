package com.takkkom.simulated_addition.mixin;

import com.takkkom.simulated_addition.index.SimAdditionBlocks;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Predicate;

@Mixin(targets = "com.simibubi.create.content.contraptions.bearing.SailBlock$PlacementHelper")
public class MixinSailBlockPlacementHelper {
    @Inject(method = "getItemPredicate", at = @At("RETURN"), cancellable = true)
    public void simulatedAddition$getItemPredicate(CallbackInfoReturnable<Predicate<ItemStack>> cir) {
        cir.setReturnValue(cir.getReturnValue()
                .or(SimAdditionBlocks.OAK_SAIL::isIn)
                .or(SimAdditionBlocks.SPRUCE_SAIL::isIn)
                .or(SimAdditionBlocks.BIRCH_SAIL::isIn)
                .or(SimAdditionBlocks.JUNGLE_SAIL::isIn)
                .or(SimAdditionBlocks.ACACIA_SAIL::isIn)
                .or(SimAdditionBlocks.CHERRY_SAIL::isIn)
                .or(SimAdditionBlocks.DARK_OAK_SAIL::isIn)
                .or(SimAdditionBlocks.MANGROVE_SAIL::isIn)
                .or(SimAdditionBlocks.BAMBOO_SAIL::isIn)
                .or(SimAdditionBlocks.CRIMSON_SAIL::isIn)
                .or(SimAdditionBlocks.WARPED_SAIL::isIn)
        );
    }
}

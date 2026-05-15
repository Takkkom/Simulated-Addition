package com.takkkom.simulated_addition.data.recipe;

import com.simibubi.create.AllTags;
import com.simibubi.create.api.data.recipe.MixingRecipeGen;
import com.simibubi.create.content.processing.recipe.HeatCondition;
import com.takkkom.simulated_addition.SimulatedAddition;
import com.takkkom.simulated_addition.index.SimAdditionBlocks;
import dev.eriksonn.aeronautics.neoforge.index.AeroFluidsNeoForge;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.material.Fluids;

import java.util.concurrent.CompletableFuture;

@SuppressWarnings("unused")
public class SimAdditionMixingRecipeGen extends MixingRecipeGen {
    GeneratedRecipe
            FLOATITE = create("floatite", b ->
            b.require(AeroFluidsNeoForge.LEVITITE_BLEND.get(), 250)
                    .require(Fluids.WATER, 250)
                    .require(AllTags.AllItemTags.CASING.tag)
                    .output(SimAdditionBlocks.FLOATITE, 1)
                    .requiresHeat(HeatCondition.HEATED));

    public SimAdditionMixingRecipeGen(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, SimulatedAddition.MODID);
    }
}

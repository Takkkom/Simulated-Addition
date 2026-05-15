package com.takkkom.simulated_addition.data.recipe;

import com.simibubi.create.api.data.recipe.CompactingRecipeGen;
import com.takkkom.simulated_addition.SimulatedAddition;
import com.takkkom.simulated_addition.index.SimAdditionBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.material.Fluids;

import java.util.concurrent.CompletableFuture;

@SuppressWarnings("unused")
public class SimAdditionCompactingRecipeGen extends CompactingRecipeGen {
    GeneratedRecipe BALLASITE = create("ballasite", b -> b.require(SimAdditionBlocks.FLOATITE)
            .require(Fluids.WATER, 250)
            .require(Fluids.LAVA, 250)
            .output(SimAdditionBlocks.BALLASITE));

    public SimAdditionCompactingRecipeGen(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, SimulatedAddition.MODID);

    }
}

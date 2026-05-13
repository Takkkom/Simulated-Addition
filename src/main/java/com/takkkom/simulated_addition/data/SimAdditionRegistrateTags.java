package com.takkkom.simulated_addition.data;

import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.TagGen;
import com.takkkom.simulated_addition.SimulatedAddition;
import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import net.minecraft.world.level.block.Block;

public class SimAdditionRegistrateTags {
    private static final CreateRegistrate REGISTRATE = SimulatedAddition.registrate();

    public static void addGenerators() {
        REGISTRATE.addDataGenerator(ProviderType.BLOCK_TAGS, SimAdditionRegistrateTags::genBlockTags);
    }

    private static void genBlockTags(RegistrateTagsProvider<Block> provIn) {
        TagGen.CreateTagsProvider<Block> prov = new TagGen.CreateTagsProvider<>(provIn, Block::builtInRegistryHolder);
    }
}

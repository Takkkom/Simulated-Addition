package com.takkkom.simulated_addition.config.server;

import com.simibubi.create.infrastructure.config.CStress;
import com.takkkom.simulated_addition.SimulatedAddition;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.util.nullness.NonNullUnaryOperator;
import it.unimi.dsi.fastutil.objects.Object2DoubleMap;
import it.unimi.dsi.fastutil.objects.Object2DoubleOpenHashMap;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.ModConfigSpec;

public class SimAdditionStress extends CStress {

    private static final Object2DoubleMap<ResourceLocation> DEFAULT_IMPACTS = new Object2DoubleOpenHashMap<>();
    private static final Object2DoubleMap<ResourceLocation> DEFAULT_CAPACITIES = new Object2DoubleOpenHashMap<>();

    @Override
    public void registerAll(ModConfigSpec.Builder builder) {
        builder.comment(".", SimAdditionStress.Comments.su, SimAdditionStress.Comments.impact).push("impact");
        DEFAULT_IMPACTS.forEach((id, value) -> this.impacts.put(id, builder.define(id.getPath(), value)));
        builder.pop();

        builder.comment(".", SimAdditionStress.Comments.su, SimAdditionStress.Comments.capacity).push("capacity");
        DEFAULT_CAPACITIES.forEach((id, value) -> this.capacities.put(id, builder.define(id.getPath(), value)));
        builder.pop();
    }

    public static <B extends Block, P> NonNullUnaryOperator<BlockBuilder<B, P>> setNoImpact() {
        return setImpact(0.0F);
    }

    public static <B extends Block, P> NonNullUnaryOperator<BlockBuilder<B, P>> setImpact(final double value) {
        return (builder) -> {
            assertFromSimExtra(builder);
            DEFAULT_IMPACTS.put(SimulatedAddition.path(builder.getName()), value);
            return builder;
        };
    }

    public static <B extends Block, P> NonNullUnaryOperator<BlockBuilder<B, P>> setCapacity(final double value) {
        return (builder) -> {
            assertFromSimExtra(builder);
            DEFAULT_CAPACITIES.put(SimulatedAddition.path(builder.getName()), value);
            return builder;
        };
    }

    private static void assertFromSimExtra(final BlockBuilder<?, ?> builder) {
        if (!builder.getOwner().getModid().equals(SimulatedAddition.MODID)) {
            throw new IllegalStateException("Non-Aeronautics blocks cannot be added to Simulated Extra's config.");
        }
    }

    private static class Comments {
        static String su = "[in Stress Units]";
        static String impact = "Configure the individual stress impact of mechanical blocks. Note that this cost is doubled for every speed increase it receives.";
        static String capacity = "Configure how much stress a source can accommodate for.";
    }
}

package com.takkkom.simulated_addition.index;

import com.takkkom.simulated_addition.ponder.scenes.*;
import com.tterrag.registrate.util.entry.ItemProviderEntry;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.minecraft.resources.ResourceLocation;

public class SimAdditionPonderScenes {
    public static void register(PonderSceneRegistrationHelper<ResourceLocation> helper) {
        PonderSceneRegistrationHelper<ItemProviderEntry<?, ?>> HELPER = helper.withKeyFunction(RegistryEntry::getId);

        HELPER.forComponents(SimAdditionBlocks.DIRECTIONAL_PROPELLER_BEARING)
                .addStoryBoard("directional_propeller_bearing/thrust_vectoring", DirectionalPropellerBearingScenes::thrustVectoring);
        HELPER.forComponents(SimAdditionBlocks.DIRECTIONAL_GYROSCOPIC_PROPELLER_BEARING)
                .addStoryBoard("directional_gyroscopic_propeller_bearing/thrust_vectoring_for_helicopter", DirectionalGyroscopicPropellerBearingScenes::thrustVectoring)
                .addStoryBoard("directional_gyroscopic_propeller_bearing/suppress_thrust_leakage", DirectionalGyroscopicPropellerBearingScenes::suppressThrustLeakage);

        HELPER.forComponents(SimAdditionBlocks.FLOATITE)
                .addStoryBoard("floatite/very_strong_buoyancy", FloatiteScenes::strongBuoyancy)
                .addStoryBoard("floatite/low_friction", FloatiteScenes::lowFriction);
        HELPER.forComponents(SimAdditionBlocks.BALLASITE)
                .addStoryBoard("ballasite/very_heavy", BallasiteScenes::veryHeavy);

        HELPER.forComponents(SimAdditionBlocks.OAK_SAIL).addStoryBoard("wooden_sail/low_air_resistance_and_low_lift", WoodenSailScenes::lowAirResistanceAndLowLift);
        HELPER.forComponents(SimAdditionBlocks.SPRUCE_SAIL).addStoryBoard("wooden_sail/low_air_resistance_and_low_lift", WoodenSailScenes::lowAirResistanceAndLowLift);
        HELPER.forComponents(SimAdditionBlocks.BIRCH_SAIL).addStoryBoard("wooden_sail/low_air_resistance_and_low_lift", WoodenSailScenes::lowAirResistanceAndLowLift);
        HELPER.forComponents(SimAdditionBlocks.JUNGLE_SAIL).addStoryBoard("wooden_sail/low_air_resistance_and_low_lift", WoodenSailScenes::lowAirResistanceAndLowLift);
        HELPER.forComponents(SimAdditionBlocks.ACACIA_SAIL).addStoryBoard("wooden_sail/low_air_resistance_and_low_lift", WoodenSailScenes::lowAirResistanceAndLowLift);
        HELPER.forComponents(SimAdditionBlocks.CHERRY_SAIL).addStoryBoard("wooden_sail/low_air_resistance_and_low_lift", WoodenSailScenes::lowAirResistanceAndLowLift);
        HELPER.forComponents(SimAdditionBlocks.DARK_OAK_SAIL).addStoryBoard("wooden_sail/low_air_resistance_and_low_lift", WoodenSailScenes::lowAirResistanceAndLowLift);
        HELPER.forComponents(SimAdditionBlocks.MANGROVE_SAIL).addStoryBoard("wooden_sail/low_air_resistance_and_low_lift", WoodenSailScenes::lowAirResistanceAndLowLift);
        HELPER.forComponents(SimAdditionBlocks.BAMBOO_SAIL).addStoryBoard("wooden_sail/low_air_resistance_and_low_lift", WoodenSailScenes::lowAirResistanceAndLowLift);
        HELPER.forComponents(SimAdditionBlocks.CRIMSON_SAIL).addStoryBoard("wooden_sail/low_air_resistance_and_low_lift", WoodenSailScenes::lowAirResistanceAndLowLift);
        HELPER.forComponents(SimAdditionBlocks.CRIMSON_SAIL).addStoryBoard("wooden_sail/low_air_resistance_and_low_lift", WoodenSailScenes::lowAirResistanceAndLowLift);
        HELPER.forComponents(SimAdditionBlocks.WARPED_SAIL).addStoryBoard("wooden_sail/low_air_resistance_and_low_lift", WoodenSailScenes::lowAirResistanceAndLowLift);

        HELPER.forComponents(SimAdditionBlocks.OAK_SYMMETRIC_SAIL).addStoryBoard("wooden_sail/low_air_resistance_and_low_lift", WoodenSailScenes::lowAirResistanceAndLowLift);
        HELPER.forComponents(SimAdditionBlocks.SPRUCE_SYMMETRIC_SAIL).addStoryBoard("wooden_sail/low_air_resistance_and_low_lift", WoodenSailScenes::lowAirResistanceAndLowLift);
        HELPER.forComponents(SimAdditionBlocks.BIRCH_SYMMETRIC_SAIL).addStoryBoard("wooden_sail/low_air_resistance_and_low_lift", WoodenSailScenes::lowAirResistanceAndLowLift);
        HELPER.forComponents(SimAdditionBlocks.JUNGLE_SYMMETRIC_SAIL).addStoryBoard("wooden_sail/low_air_resistance_and_low_lift", WoodenSailScenes::lowAirResistanceAndLowLift);
        HELPER.forComponents(SimAdditionBlocks.ACACIA_SYMMETRIC_SAIL).addStoryBoard("wooden_sail/low_air_resistance_and_low_lift", WoodenSailScenes::lowAirResistanceAndLowLift);
        HELPER.forComponents(SimAdditionBlocks.CHERRY_SYMMETRIC_SAIL).addStoryBoard("wooden_sail/low_air_resistance_and_low_lift", WoodenSailScenes::lowAirResistanceAndLowLift);
        HELPER.forComponents(SimAdditionBlocks.DARK_OAK_SYMMETRIC_SAIL).addStoryBoard("wooden_sail/low_air_resistance_and_low_lift", WoodenSailScenes::lowAirResistanceAndLowLift);
        HELPER.forComponents(SimAdditionBlocks.MANGROVE_SYMMETRIC_SAIL).addStoryBoard("wooden_sail/low_air_resistance_and_low_lift", WoodenSailScenes::lowAirResistanceAndLowLift);
        HELPER.forComponents(SimAdditionBlocks.BAMBOO_SYMMETRIC_SAIL).addStoryBoard("wooden_sail/low_air_resistance_and_low_lift", WoodenSailScenes::lowAirResistanceAndLowLift);
        HELPER.forComponents(SimAdditionBlocks.CRIMSON_SYMMETRIC_SAIL).addStoryBoard("wooden_sail/low_air_resistance_and_low_lift", WoodenSailScenes::lowAirResistanceAndLowLift);
        HELPER.forComponents(SimAdditionBlocks.CRIMSON_SYMMETRIC_SAIL).addStoryBoard("wooden_sail/low_air_resistance_and_low_lift", WoodenSailScenes::lowAirResistanceAndLowLift);
        HELPER.forComponents(SimAdditionBlocks.WARPED_SYMMETRIC_SAIL).addStoryBoard("wooden_sail/low_air_resistance_and_low_lift", WoodenSailScenes::lowAirResistanceAndLowLift);
    }
}

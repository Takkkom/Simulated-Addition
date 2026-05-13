package com.takkkom.simulated_addition.helper;

import com.takkkom.simulated_addition.index.SimAdditionTags;
import net.minecraft.world.level.block.state.BlockState;

public class SailHelper {
    private SailHelper() {}

    public static final float LOW_POWER_MULTIPLIER = 0.5f;
    public static final float HIGH_POWER_MULTIPLIER = 2.0f;

    public static float GetSailPowerMul(BlockState blockState) {
        if (SimAdditionTags.AllBlockTags.LOW_POWER_SAIL.matches(blockState)) {
            return LOW_POWER_MULTIPLIER;
        }
        else if (SimAdditionTags.AllBlockTags.HIGH_POWER_SAIL.matches(blockState)) {
            return HIGH_POWER_MULTIPLIER;
        }
        return 1.0f;
    }
}

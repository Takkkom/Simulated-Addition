package com.takkkom.simulated_addition.index;

import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import com.simibubi.create.foundation.block.connected.CTSpriteShifter;
import com.takkkom.simulated_addition.SimulatedAddition;

import static com.simibubi.create.foundation.block.connected.AllCTTypes.OMNIDIRECTIONAL;

public class SimAdditionSpriteShift {
    public static final CTSpriteShiftEntry FLOATITE = omni("block/floatite");
    public static final CTSpriteShiftEntry BALLASITE = omni("block/ballasite");

    static CTSpriteShiftEntry omni(String name) {
        return CTSpriteShifter.getCT(OMNIDIRECTIONAL,
                SimulatedAddition.path(name),
                SimulatedAddition.path(name + "_connected"));
    }
}

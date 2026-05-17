package com.takkkom.simulated_addition.ponder;

import com.takkkom.simulated_addition.SimulatedAddition;
import com.takkkom.simulated_addition.index.SimAdditionPonderScenes;
import com.takkkom.simulated_addition.index.SimAdditionPonderTags;
import net.createmod.ponder.api.registration.PonderPlugin;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.createmod.ponder.api.registration.PonderTagRegistrationHelper;
import net.createmod.ponder.api.registration.SharedTextRegistrationHelper;
import net.minecraft.resources.ResourceLocation;

public class SimAdditionPonderPlugin implements PonderPlugin {
    @Override
    public String getModId() {
        return SimulatedAddition.MODID;
    }

    @Override
    public void registerScenes(PonderSceneRegistrationHelper<ResourceLocation> helper) {
        SimAdditionPonderScenes.register(helper);
    }

    @Override
    public void registerTags(PonderTagRegistrationHelper<ResourceLocation> helper) {
        SimAdditionPonderTags.register(helper);
    }

    @Override
    public void registerSharedText(SharedTextRegistrationHelper helper) {
    }
}

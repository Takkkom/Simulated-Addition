package com.takkkom.simulated_addition.index;

import com.takkkom.simulated_addition.SimulatedAddition;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class SimAdditionCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SimulatedAddition.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MAIN_TAB = CREATIVE_MODE_TABS.register("main_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.simulated_addition.main"))
                    .icon(() -> SimAdditionBlocks.DIRECTIONAL_GYROSCOPIC_PROPELLER_BEARING.asItem().getDefaultInstance())
                    .build());

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> BUILDING_BLOCKS_TAB = CREATIVE_MODE_TABS.register("building_blocks_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.simulated_addition.building_blocks"))
                    .icon(() -> SimAdditionBlocks.OAK_SAIL.asItem().getDefaultInstance())
                    .build());

    public static void register(IEventBus modEventBus) {
        CREATIVE_MODE_TABS.register(modEventBus);
    }
}

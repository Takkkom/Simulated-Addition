package com.takkkom.simulated_addition;

import com.mojang.logging.LogUtils;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.item.ItemDescription;
import com.simibubi.create.foundation.item.KineticStats;
import com.simibubi.create.foundation.item.TooltipModifier;
import com.takkkom.simulated_addition.config.SimAdditionConfigs;
import com.takkkom.simulated_addition.data.SimAdditionDatagen;
import com.takkkom.simulated_addition.index.*;
import net.createmod.catnip.config.ui.BaseConfigScreen;
import net.createmod.catnip.lang.FontHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(SimulatedAddition.MODID)
public class SimulatedAddition {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "simulated_addition";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();


    private static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MODID)
            .defaultCreativeTab((ResourceKey<CreativeModeTab>) null)
            .setTooltipModifierFactory(item ->
                    new ItemDescription.Modifier(item, FontHelper.Palette.STANDARD_CREATE)
                            .andThen(TooltipModifier.mapNull(KineticStats.create(item)))
            );

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public SimulatedAddition(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        REGISTRATE.registerEventListeners(modEventBus);

        SimAdditionBlocks.register();
        SimAdditionBlockEntityTypes.register();
        SimAdditionItems.register();
        SimAdditionCreativeModeTabs.register(modEventBus);
        SimAdditionTags.register();

        SimAdditionConfigs.register(ModLoadingContext.get(), modContainer);

        modEventBus.addListener(this::commonSetup);


        modEventBus.addListener(EventPriority.HIGHEST, SimAdditionDatagen::gatherDataHighPriority);
        modEventBus.addListener(EventPriority.LOWEST, SimAdditionDatagen::gatherData);



        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (Sinulated_extra) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        //modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code

        /*
        LOGGER.info("HELLO FROM COMMON SETUP");

        if (Config.logDirtBlock) LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));

        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
         */
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        //if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) event.accept(EXAMPLE_BLOCK_ITEM);
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        //LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            // Some client setup code
            //LOGGER.info("HELLO FROM CLIENT SETUP");
            //LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());

            BaseConfigScreen.setDefaultActionFor(MODID, base -> base
                    .withButtonLabels("Client Settings", "World Generation Settings", "Gameplay Settings")
                    .withSpecs(SimAdditionConfigs.client().specification, SimAdditionConfigs.common().specification, SimAdditionConfigs.server().specification)
            );
        }
    }

    public static CreateRegistrate registrate() {
        return REGISTRATE;
    }

    public static ResourceLocation path(final String path) {
        return ResourceLocation.tryBuild(MODID, path);
    }
}

package com.takkkom.simulated_addition.index;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import com.simibubi.create.AllTags;
import com.simibubi.create.foundation.block.connected.SimpleCTBehaviour;
import com.simibubi.create.foundation.data.BlockStateGen;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.SharedProperties;
import com.takkkom.simulated_addition.SimulatedAddition;
import com.takkkom.simulated_addition.config.server.SimAdditionStress;
import com.takkkom.simulated_addition.content.blocks.analog_torsion_spring.AnalogTorsionSpringBlock;
import com.takkkom.simulated_addition.content.blocks.propeller.bearing.directional_gyroscopic_propeller_bearing.DirectionalGyroscopicPropellerBearingBlock;
import com.takkkom.simulated_addition.content.blocks.propeller.bearing.directional_propeller_bearing.DirectionalPropellerBearingBlock;
import com.takkkom.simulated_addition.content.blocks.sail.WoodenSailBlock;
import com.takkkom.simulated_addition.content.blocks.sail.WoodenSymmetricSailBlock;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.util.entry.BlockEntry;
import dev.eriksonn.aeronautics.index.AeroSoundEvents;
import dev.eriksonn.aeronautics.index.AeroTags;
import dev.ryanhcode.sable.index.SableTags;
import dev.simulated_team.simulated.config.server.blocks.SimStress;
import dev.simulated_team.simulated.content.blocks.torsion_spring.TorsionSpringBlock;
import dev.simulated_team.simulated.data.SimBlockStateGen;
import dev.simulated_team.simulated.index.SimItems;
import dev.simulated_team.simulated.index.sounds.SimLazySoundType;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.common.Tags;

import static com.simibubi.create.foundation.data.CreateRegistrate.connectedTextures;
import static com.simibubi.create.foundation.data.ModelGen.customItemModel;
import static com.simibubi.create.foundation.data.TagGen.*;

public class SimAdditionBlocks {
    private static final CreateRegistrate REGISTRATE = SimulatedAddition.registrate();


    public static final BlockEntry<DirectionalPropellerBearingBlock> DIRECTIONAL_PROPELLER_BEARING =
            REGISTRATE.block("directional_propeller_bearing", DirectionalPropellerBearingBlock::new)
                    .initialProperties(SharedProperties::stone)
                    .properties(p -> p.sound(SoundType.COPPER))
                    .properties(BlockBehaviour.Properties::noOcclusion)
                    .transform(SimAdditionStress.setImpact(2.0))
                    .transform(axeOrPickaxe())
                    .blockstate((ctx, prov) -> SimBlockStateGen.facingBlockstate(ctx, prov, "block/directional_propeller_bearing/block"))
                    .tag(AllTags.AllBlockTags.SAFE_NBT.tag)
                    .lang("Directional Propeller Bearing")
                    .item()
                    .recipe((c, p) -> ShapedRecipeBuilder.shaped(RecipeCategory.MISC, c.get(), 1)
                            .pattern(" A ")
                            .pattern("EIE")
                            .pattern("RBR")
                            .define('A', net.minecraft.tags.ItemTags.WOODEN_SLABS)
                            .define('B', AllBlocks.BRASS_CASING.get())
                            .define('I', AllItems.IRON_SHEET.get())
                            .define('R', Items.REDSTONE)
                            .define('E', AllItems.ELECTRON_TUBE)
                            .unlockedBy("has_ingredient", RegistrateRecipeProvider.has(AllBlocks.BRASS_CASING.get()))
                            .save(p, SimulatedAddition.path("crafting/kinetics/directional_propeller_bearing")))
                    .tab(SimAdditionCreativeModeTabs.MAIN_TAB.getKey())
                    .transform(customItemModel())
                    .register();


    public static final BlockEntry<DirectionalGyroscopicPropellerBearingBlock> DIRECTIONAL_GYROSCOPIC_PROPELLER_BEARING =
            REGISTRATE.block("directional_gyroscopic_propeller_bearing", DirectionalGyroscopicPropellerBearingBlock::new)
                    .initialProperties(SharedProperties::stone)
                    .properties(p -> p.sound(SoundType.COPPER))
                    .properties(BlockBehaviour.Properties::noOcclusion)
                    .transform(SimAdditionStress.setImpact(2.0))
                    .transform(axeOrPickaxe())
                    .blockstate((ctx, prov) -> SimBlockStateGen.facingBlockstate(ctx, prov, "block/directional_gyroscopic_propeller_bearing/block"))
                    .tag(AllTags.AllBlockTags.SAFE_NBT.tag)
                    .lang("Directional Gyroscopic Propeller Bearing")
                    .item()
                    .recipe((c, p) -> ShapedRecipeBuilder.shaped(RecipeCategory.MISC, c.get(), 1)
                            .pattern(" A ")
                            .pattern("EGE")
                            .pattern("RBR")
                            .define('A', net.minecraft.tags.ItemTags.WOODEN_SLABS)
                            .define('B', AllBlocks.BRASS_CASING.get())
                            .define('G', SimItems.GYRO_MECHANISM.get())
                            .define('R', Items.REDSTONE)
                            .define('E', AllItems.ELECTRON_TUBE)
                            .unlockedBy("has_ingredient", RegistrateRecipeProvider.has(AllBlocks.BRASS_CASING.get()))
                            .save(p, SimulatedAddition.path("crafting/kinetics/directional_gyroscopic_propeller_bearing")))
                    .tab(SimAdditionCreativeModeTabs.MAIN_TAB.getKey())
                    .transform(customItemModel())
                    .register();


    public static final BlockEntry<Block> FLOATITE =
            REGISTRATE.block("floatite", Block::new)
                    .properties(p -> p.lightLevel($ -> 10))
                    .properties(p -> p.strength(7, 20))
                    .properties(p -> p.sound(new SimLazySoundType(1.0f, 1.0f,
                            AeroSoundEvents.LEVITITE_BREAK::event,
                            () -> SoundEvents.AMETHYST_BLOCK_STEP,
                            AeroSoundEvents.LEVITITE_PLACE::event,
                            () -> SoundEvents.AMETHYST_BLOCK_HIT,
                            () -> SoundEvents.AMETHYST_BLOCK_FALL)))
                    .transform(pickaxeOnly())
                    .tag(SimAdditionTags.AllBlockTags.FLOATITE.tag)
                    .onRegister(connectedTextures(() -> new SimpleCTBehaviour(SimAdditionSpriteShift.FLOATITE)))
                    .tag(SableTags.ALWAYS_CHUNK_RENDERING)
                    .lang("Floatite")
                    .item()
                    .tag(SimAdditionTags.AllItemTags.FLOATITE.tag)
                    .tab(SimAdditionCreativeModeTabs.MAIN_TAB.getKey())
                    .build()
                    .register();


    public static final BlockEntry<Block> BALLASITE =
            REGISTRATE.block("ballasite", Block::new)
                    .properties(p -> p.strength(7, 20))
                    .properties(p -> p.sound(new SimLazySoundType(1.0f, 0.9f,
                            AeroSoundEvents.LEVITITE_BREAK::event,
                            () -> SoundEvents.AMETHYST_BLOCK_STEP,
                            AeroSoundEvents.LEVITITE_PLACE::event,
                            () -> SoundEvents.AMETHYST_BLOCK_HIT,
                            () -> SoundEvents.AMETHYST_BLOCK_FALL)))
                    .transform(pickaxeOnly())
                    .tag(SimAdditionTags.AllBlockTags.BALLASITE.tag)
                    .onRegister(connectedTextures(() -> new SimpleCTBehaviour(SimAdditionSpriteShift.BALLASITE)))
                    .tag(SableTags.ALWAYS_CHUNK_RENDERING)
                    .lang("Ballasite")
                    .item()
                    .tag(SimAdditionTags.AllItemTags.BALLASITE.tag)
                    .tab(SimAdditionCreativeModeTabs.MAIN_TAB.getKey())
                    .build()
                    .register();


    public static final BlockEntry<WoodenSailBlock> OAK_SAIL =
            createWoodenSail("oak")
                    .lang("Oak Sail")
                    .item()
                    .tab(SimAdditionCreativeModeTabs.MAIN_TAB.getKey())
                    .tab(SimAdditionCreativeModeTabs.BUILDING_BLOCKS_TAB.getKey())
                    .build()
                    .register();

    public static final BlockEntry<WoodenSailBlock> SPRUCE_SAIL =
            createWoodenSail("spruce")
                    .lang("Spruce Sail")
                    .item()
                    .tab(SimAdditionCreativeModeTabs.BUILDING_BLOCKS_TAB.getKey())
                    .build()
                    .register();

    public static final BlockEntry<WoodenSailBlock> BIRCH_SAIL =
            createWoodenSail("birch")
                    .lang("Birch Sail")
                    .item()
                    .tab(SimAdditionCreativeModeTabs.BUILDING_BLOCKS_TAB.getKey())
                    .build()
                    .register();

    public static final BlockEntry<WoodenSailBlock> JUNGLE_SAIL =
            createWoodenSail("jungle")
                    .lang("Jungle Sail")
                    .item()
                    .tab(SimAdditionCreativeModeTabs.BUILDING_BLOCKS_TAB.getKey())
                    .build()
                    .register();

    public static final BlockEntry<WoodenSailBlock> ACACIA_SAIL =
            createWoodenSail("acacia")
                    .lang("Acacia Sail")
                    .item()
                    .tab(SimAdditionCreativeModeTabs.BUILDING_BLOCKS_TAB.getKey())
                    .build()
                    .register();

    public static final BlockEntry<WoodenSailBlock> CHERRY_SAIL =
            createWoodenSail("cherry")
                    .lang("Cherry Sail")
                    .item()
                    .tab(SimAdditionCreativeModeTabs.BUILDING_BLOCKS_TAB.getKey())
                    .build()
                    .register();

    public static final BlockEntry<WoodenSailBlock> DARK_OAK_SAIL =
            createWoodenSail("dark_oak")
                    .lang("Dark Oak Sail")
                    .item()
                    .tab(SimAdditionCreativeModeTabs.BUILDING_BLOCKS_TAB.getKey())
                    .build()
                    .register();

    public static final BlockEntry<WoodenSailBlock> MANGROVE_SAIL =
            createWoodenSail("mangrove")
                    .lang("Mangrove Sail")
                    .item()
                    .tab(SimAdditionCreativeModeTabs.BUILDING_BLOCKS_TAB.getKey())
                    .build()
                    .register();

    public static final BlockEntry<WoodenSailBlock> BAMBOO_SAIL =
            createWoodenSail("bamboo")
                    .lang("Bamboo Sail")
                    .item()
                    .tab(SimAdditionCreativeModeTabs.BUILDING_BLOCKS_TAB.getKey())
                    .build()
                    .register();

    public static final BlockEntry<WoodenSailBlock> CRIMSON_SAIL =
            createWoodenSail("crimson")
                    .lang("Crimson Sail")
                    .item()
                    .tab(SimAdditionCreativeModeTabs.BUILDING_BLOCKS_TAB.getKey())
                    .build()
                    .register();

    public static final BlockEntry<WoodenSailBlock> WARPED_SAIL =
            createWoodenSail("warped")
                    .lang("Warped Sail")
                    .item()
                    .tab(SimAdditionCreativeModeTabs.BUILDING_BLOCKS_TAB.getKey())
                    .build()
                    .register();


    public static final BlockEntry<WoodenSymmetricSailBlock> OAK_SYMMETRIC_SAIL =
            createWoodenSymmetricSail("oak")
                    .lang("Oak Symmetric Sail")
                    .item()
                    .tab(SimAdditionCreativeModeTabs.MAIN_TAB.getKey())
                    .tab(SimAdditionCreativeModeTabs.BUILDING_BLOCKS_TAB.getKey())
                    .build()
                    .register();

    public static final BlockEntry<WoodenSymmetricSailBlock> SPRUCE_SYMMETRIC_SAIL =
            createWoodenSymmetricSail("spruce")
                    .lang("Spruce Symmetric Sail")
                    .item()
                    .tab(SimAdditionCreativeModeTabs.BUILDING_BLOCKS_TAB.getKey())
                    .build()
                    .register();

    public static final BlockEntry<WoodenSymmetricSailBlock> BIRCH_SYMMETRIC_SAIL =
            createWoodenSymmetricSail("birch")
                    .lang("Birch Symmetric Sail")
                    .item()
                    .tab(SimAdditionCreativeModeTabs.BUILDING_BLOCKS_TAB.getKey())
                    .build()
                    .register();

    public static final BlockEntry<WoodenSymmetricSailBlock> JUNGLE_SYMMETRIC_SAIL =
            createWoodenSymmetricSail("jungle")
                    .lang("Jungle Symmetric Sail")
                    .item()
                    .tab(SimAdditionCreativeModeTabs.BUILDING_BLOCKS_TAB.getKey())
                    .build()
                    .register();

    public static final BlockEntry<WoodenSymmetricSailBlock> ACACIA_SYMMETRIC_SAIL =
            createWoodenSymmetricSail("acacia")
                    .lang("Acacia Symmetric Sail")
                    .item()
                    .tab(SimAdditionCreativeModeTabs.BUILDING_BLOCKS_TAB.getKey())
                    .build()
                    .register();

    public static final BlockEntry<WoodenSymmetricSailBlock> CHERRY_SYMMETRIC_SAIL =
            createWoodenSymmetricSail("cherry")
                    .lang("Cherry Symmetric Sail")
                    .item()
                    .tab(SimAdditionCreativeModeTabs.BUILDING_BLOCKS_TAB.getKey())
                    .build()
                    .register();

    public static final BlockEntry<WoodenSymmetricSailBlock> DARK_OAK_SYMMETRIC_SAIL =
            createWoodenSymmetricSail("dark_oak")
                    .lang("Dark Oak Symmetric Sail")
                    .item()
                    .tab(SimAdditionCreativeModeTabs.BUILDING_BLOCKS_TAB.getKey())
                    .build()
                    .register();

    public static final BlockEntry<WoodenSymmetricSailBlock> MANGROVE_SYMMETRIC_SAIL =
            createWoodenSymmetricSail("mangrove")
                    .lang("Mangrove Symmetric Sail")
                    .tag(AllTags.AllBlockTags.WINDMILL_SAILS.tag)
                    .tag(SimAdditionTags.AllBlockTags.LOW_POWER_SAIL.tag)
                    .item()
                    .tab(SimAdditionCreativeModeTabs.BUILDING_BLOCKS_TAB.getKey())
                    .build()
                    .register();

    public static final BlockEntry<WoodenSymmetricSailBlock> BAMBOO_SYMMETRIC_SAIL =
            createWoodenSymmetricSail("bamboo")
                    .lang("Bamboo Symmetric Sail")
                    .item()
                    .tab(SimAdditionCreativeModeTabs.BUILDING_BLOCKS_TAB.getKey())
                    .build()
                    .register();

    public static final BlockEntry<WoodenSymmetricSailBlock> CRIMSON_SYMMETRIC_SAIL =
            createWoodenSymmetricSail("crimson")
                    .lang("Crimson Symmetric Sail")
                    .item()
                    .tab(SimAdditionCreativeModeTabs.BUILDING_BLOCKS_TAB.getKey())
                    .build()
                    .register();

    public static final BlockEntry<WoodenSymmetricSailBlock> WARPED_SYMMETRIC_SAIL =
            createWoodenSymmetricSail("warped")
                    .lang("Warped Symmetric Sail")
                    .tag(AllTags.AllBlockTags.WINDMILL_SAILS.tag)
                    .tag(SimAdditionTags.AllBlockTags.LOW_POWER_SAIL.tag)
                    .item()
                    .tab(SimAdditionCreativeModeTabs.BUILDING_BLOCKS_TAB.getKey())
                    .build()
                    .register();


    /*
    public static final BlockEntry<AnalogTorsionSpringBlock> ANALOG_TORSION_SPRING =
            REGISTRATE.block("analog_torsion_spring", AnalogTorsionSpringBlock::new)
                    .initialProperties(SharedProperties::stone)
                    .properties(BlockBehaviour.Properties::noOcclusion)
                    .addLayer(() -> RenderType::cutoutMipped)
                    .blockstate((c, p) -> p.directionalBlock(c.get(),
                            blockState -> p.models().getExistingFile(p.modLoc("block/analog_torsion_spring/block"))))
                    .tag(BlockTags.MINEABLE_WITH_PICKAXE)
                    .tag(BlockTags.MINEABLE_WITH_AXE)
                    .item().transform(customItemModel())
                    .register();
     */









    //test

    /*

    /*
    public static final BlockEntry<WaterOcclusionTestBlock> WATER_OCCLUSION_TEST =
            REGISTRATE.block("water_occlusion_test", WaterOcclusionTestBlock::new)
                    .initialProperties(SharedProperties::stone)
                    .properties(p -> p.sound(SoundType.COPPER))
                    .item()
                    .build()
                    .register();
     */




    public static BlockBuilder<WoodenSailBlock, CreateRegistrate> createWoodenSail(CreateRegistrate registrate, String woodenName) {
        return registrate.block(woodenName + "_sail", WoodenSailBlock::new)
                .initialProperties(SharedProperties::wooden)
                .properties(properties -> properties.noOcclusion().mapColor(MapColor.WOOD))
                .transform(axeOnly())
                .blockstate(BlockStateGen.directionalBlockProvider(false))
                .tag(AllTags.AllBlockTags.WINDMILL_SAILS.tag)
                .tag(SimAdditionTags.AllBlockTags.LOW_POWER_SAIL.tag)
                .recipe((c, p) -> {
                    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, c.get(), 4)
                            .define('W', BuiltInRegistries.BLOCK.get(ResourceLocation.withDefaultNamespace(woodenName + "_planks")))
                            .define('S', Tags.Items.RODS_WOODEN)
                            .define('A', AllItems.ANDESITE_ALLOY)
                            .pattern("WS")
                            .pattern("SA")
                            .unlockedBy("has_ingredient", RegistrateRecipeProvider.has(AllItems.ANDESITE_ALLOY.get()))
                            .save(p, SimulatedAddition.path("crafting/kinetics/" + woodenName + "_sail"));

                    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, c.get(), 1)
                            .define('A', REGISTRATE.get(woodenName + "_symmetric_sail", Registries.BLOCK).get())
                            .pattern("A")
                            .unlockedBy("has_ingredient", RegistrateRecipeProvider.has(AllItems.ANDESITE_ALLOY.get()))
                            .save(p, SimulatedAddition.path("crafting/kinetics/symmetric_sail_to_sail/" + woodenName + "_sail"));
                });
                /*
                 */
    }

    public static BlockBuilder<WoodenSymmetricSailBlock, CreateRegistrate> createWoodenSymmetricSail(CreateRegistrate registrate, String woodenName) {
        return registrate.block(woodenName + "_symmetric_sail", WoodenSymmetricSailBlock::new)
                .initialProperties(SharedProperties::wooden)
                .properties(properties -> properties.noOcclusion().mapColor(MapColor.WOOD))
                .transform(axeOnly())
                .blockstate((c, p) -> BlockStateGen.axisBlock(c, p, blockState -> p.models()
                                .getExistingFile(SimulatedAddition.path("block/" + woodenName + "_symmetric_sail"))
                ))
                .tag(AllTags.AllBlockTags.WINDMILL_SAILS.tag)
                .tag(SimAdditionTags.AllBlockTags.LOW_POWER_SAIL.tag)
                .recipe((c, p) -> ShapedRecipeBuilder.shaped(RecipeCategory.MISC, c.get(), 2)
                        .define('A', REGISTRATE.get(woodenName + "_sail", Registries.BLOCK).get())
                        .pattern("AA")
                        .unlockedBy("has_ingredient", RegistrateRecipeProvider.has(AllItems.ANDESITE_ALLOY.get()))
                        .save(p, SimulatedAddition.path("crafting/kinetics/" + woodenName + "_symmetric_sail")));
    }

    private static BlockBuilder<WoodenSailBlock, CreateRegistrate> createWoodenSail(String woodenName) {
        return createWoodenSail(REGISTRATE, woodenName);
    }

    private static BlockBuilder<WoodenSymmetricSailBlock, CreateRegistrate> createWoodenSymmetricSail(String woodenName) {
        return createWoodenSymmetricSail(REGISTRATE, woodenName);
    }



    public static void register() {
        //Blocks
    }
}

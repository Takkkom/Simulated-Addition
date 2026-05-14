package com.takkkom.simulated_addition.index;

import com.simibubi.create.AllTags;
import com.takkkom.simulated_addition.SimulatedAddition;
import net.createmod.catnip.lang.Lang;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import static com.simibubi.create.AllTags.NameSpace.MOD;

public class SimAdditionTags {
    public static void register() {
    }

    public enum NameSpace {
        MOD(SimulatedAddition.MODID);

        public final String id;

        NameSpace(String id) {
            this.id = id;
        }

        public ResourceLocation id(String path) {
            return ResourceLocation.fromNamespaceAndPath(this.id, path);
        }

        public ResourceLocation id(Enum<?> entry, @Nullable String pathOverride) {
            return this.id(pathOverride != null ? pathOverride : Lang.asId(entry.name()));
        }
    }

    public enum AllBlockTags {
        LOW_POWER_SAIL,
        HIGH_POWER_SAIL,
        FLOATITE,
        ;

        public final TagKey<Block> tag;

        AllBlockTags() {
            this(NameSpace.MOD);
        }

        AllBlockTags(NameSpace namespace) {
            this(namespace, null);
        }

        AllBlockTags(NameSpace namespace, @Nullable String pathOverride) {
            this.tag = net.minecraft.tags.TagKey.create(Registries.BLOCK, namespace.id(this, pathOverride));
        }

        @SuppressWarnings("deprecation")
        public boolean matches(Block block) {
            return block.builtInRegistryHolder()
                    .is(tag);
        }

        public boolean matches(ItemStack stack) {
            return stack != null && stack.getItem() instanceof BlockItem blockItem && matches(blockItem.getBlock());
        }

        public boolean matches(BlockState state) {
            return state.is(tag);
        }
    }

    public enum AllItemTags {
        FLOATITE;

        public final TagKey<Item> tag;

        AllItemTags() {
            this(NameSpace.MOD);
        }

        AllItemTags(NameSpace namespace) {
            this(namespace, null);
        }

        AllItemTags(NameSpace namespace, @Nullable String pathOverride) {
            this.tag = TagKey.create(Registries.ITEM, namespace.id(this, pathOverride));
        }

        @SuppressWarnings("deprecation")
        public boolean matches(Item item) {
            return item.builtInRegistryHolder()
                    .is(tag);
        }

        public boolean matches(ItemStack stack) {
            return stack.is(tag);
        }
    }
}

package com.takkkom.simulated_addition.content.blocks.test;

import dev.ryanhcode.sable.SableClient;
import dev.ryanhcode.sable.companion.SableCompanion;
import dev.ryanhcode.sable.companion.SubLevelAccess;
import dev.ryanhcode.sable.companion.math.BoundingBox3dc;
import dev.ryanhcode.sable.render.water_occlusion.WaterOcclusionRenderer;
import dev.ryanhcode.sable.sublevel.water_occlusion.WaterOcclusionContainer;
import dev.ryanhcode.sable.sublevel.water_occlusion.WaterOcclusionRegion;
import dev.ryanhcode.sable.util.BoundedBitVolume3i;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import java.util.ArrayList;
import java.util.List;

public class WaterOcclusionTestBlock extends Block {
    public WaterOcclusionTestBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected ItemInteractionResult useItemOn(final ItemStack stack, final BlockState state, final Level level, final BlockPos pos, final Player player, final InteractionHand hand, final BlockHitResult hitResult) {

        WaterOcclusionContainer<?> waterOcclusionContainer = WaterOcclusionContainer.getContainer(level);
        if (waterOcclusionContainer == null) {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }

        /*
        ArrayList<BlockPos> mutableBlockPos = new ArrayList<>();
        for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {
                for (int k = -2; k <= 2; k++) {
                    mutableBlockPos.add(new BlockPos(pos.getX() + i, pos.getY() + j, pos.getZ() + k));
                }
            }
        }
        BoundedBitVolume3i region = BoundedBitVolume3i.fromBlocks(mutableBlockPos);
        WaterOcclusionRegion waterOcclusionRegion = waterOcclusionContainer.addRegion(region);
         */
        SubLevelAccess subLevelAccess = SableCompanion.INSTANCE.getContaining(level, pos);
        if (subLevelAccess != null) {
            ArrayList<BlockPos> floodedBlockPos = new ArrayList<>();
            ArrayList<BlockPos> nextBlockPos = new ArrayList<>();
            nextBlockPos.add(pos);

            while (!nextBlockPos.isEmpty()) {
                BlockPos nowBlockPos = nextBlockPos.getFirst();
                nextBlockPos.remove(nowBlockPos);
                floodedBlockPos.add(nowBlockPos);

                for (Direction value : Direction.values()) {
                    BlockPos targetBlockPos = nowBlockPos.relative(value);
                    if (!floodedBlockPos.contains(targetBlockPos) && level.getBlockState(targetBlockPos).isAir() && SableCompanion.INSTANCE.isInPlotGrid(level, targetBlockPos)) {
                        nextBlockPos.add(targetBlockPos);
                    }
                }
            }
            BoundedBitVolume3i region = BoundedBitVolume3i.fromBlocks(floodedBlockPos);
            WaterOcclusionRegion waterOcclusionRegion = waterOcclusionContainer.addRegion(region);

            return ItemInteractionResult.SUCCESS;
        }

        return ItemInteractionResult.SUCCESS;
    }
}
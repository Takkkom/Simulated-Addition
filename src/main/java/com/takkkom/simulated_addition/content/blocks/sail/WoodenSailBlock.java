package com.takkkom.simulated_addition.content.blocks.sail;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.contraptions.bearing.SailBlock;
import com.takkkom.simulated_addition.helper.SailHelper;
import dev.ryanhcode.sable.api.block.BlockSubLevelLiftProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class WoodenSailBlock extends SailBlock implements BlockSubLevelLiftProvider {
    public WoodenSailBlock(Properties properties) {
        super(properties, false, DyeColor.WHITE);
    }


    @Override
    public void applyDye(BlockState state, Level world, BlockPos pos, Vec3 hit, @Nullable DyeColor color) {
        return;
    }


    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        DyeColor color = DyeColor.getColor(stack);
        if (color != null) {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }
        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }

    @Override
    public @NotNull Direction sable$getNormal(final BlockState state) {
        return state.getValue(BlockStateProperties.FACING).getOpposite();
    }

    @Override
    public float sable$getParallelDragScalar() {
        if (AllBlocks.SAIL.get() instanceof BlockSubLevelLiftProvider blockSubLevelLiftProvider){
            return blockSubLevelLiftProvider.sable$getParallelDragScalar() * SailHelper.LOW_POWER_MULTIPLIER;
        }
        return 0.0f;
    }

    @Override
    public float sable$getLiftScalar() {
        if (AllBlocks.SAIL.get() instanceof BlockSubLevelLiftProvider blockSubLevelLiftProvider){
            return blockSubLevelLiftProvider.sable$getLiftScalar() * SailHelper.LOW_POWER_MULTIPLIER;
        }
        return 0.0f;
    }
}

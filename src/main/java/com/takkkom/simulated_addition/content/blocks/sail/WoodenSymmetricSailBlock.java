package com.takkkom.simulated_addition.content.blocks.sail;

import com.takkkom.simulated_addition.helper.SailHelper;
import dev.ryanhcode.sable.api.block.BlockSubLevelLiftProvider;
import dev.simulated_team.simulated.content.blocks.symmetric_sail.SymmetricSailBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class WoodenSymmetricSailBlock extends SymmetricSailBlock implements BlockSubLevelLiftProvider {
    public WoodenSymmetricSailBlock(Properties properties) {
        super(properties, DyeColor.WHITE);
    }

    @Override
    public void applyDye(BlockState state, Level world, BlockPos pos, Vec3 hit, @Nullable DyeColor color) {
        return;
    }


    @Override
    protected @NotNull ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        DyeColor color = DyeColor.getColor(stack);
        if (color != null) {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }

        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }

    @Override
    public ItemStack getCloneItemStack(final LevelReader level, final BlockPos pos, final BlockState state) {
        return new ItemStack(this);
    }

    @Override
    public float sable$getParallelDragScalar() {
        return super.sable$getParallelDragScalar() * SailHelper.LOW_POWER_MULTIPLIER;
    }

    @Override
    public float sable$getLiftScalar() {
        return super.sable$getLiftScalar() * SailHelper.LOW_POWER_MULTIPLIER;
    }
}

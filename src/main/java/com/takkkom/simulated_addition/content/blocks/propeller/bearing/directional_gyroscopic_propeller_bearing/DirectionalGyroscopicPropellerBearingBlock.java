package com.takkkom.simulated_addition.content.blocks.propeller.bearing.directional_gyroscopic_propeller_bearing;

import com.simibubi.create.content.contraptions.bearing.BearingBlock;
import com.simibubi.create.foundation.block.IBE;
import com.takkkom.simulated_addition.index.SimAdditionBlockEntityTypes;
import dev.eriksonn.aeronautics.data.AeroLang;
import dev.eriksonn.aeronautics.index.AeroBlockShapes;
import dev.simulated_team.simulated.api.CustomStressImpactTooltipProvider;
import net.createmod.catnip.lang.LangBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class DirectionalGyroscopicPropellerBearingBlock extends BearingBlock implements IBE<DirectionalGyroscopicPropellerBearingBlockEntity>, CustomStressImpactTooltipProvider {
    public DirectionalGyroscopicPropellerBearingBlock(final Properties properties) {
        super(properties);
    }

    public LangBuilder getCustomImpactLang() {
        return AeroLang.translate("propeller.sails");
    }

    @Override
    public int getBarLength() {
        return 3;
    }

    @Override
    public int getFilledBarLength() {
        return 3;
    }

    @Override
    protected ItemInteractionResult useItemOn(final ItemStack stack, final BlockState state, final Level level, final BlockPos pos, final Player player, final InteractionHand hand, final BlockHitResult hitResult) {
        if (!player.mayBuild())
            return ItemInteractionResult.FAIL;
        if (player.isShiftKeyDown())
            return ItemInteractionResult.FAIL;
        if (stack.isEmpty()) {
            if (level.isClientSide) {

                return ItemInteractionResult.SUCCESS;
            }
            this.withBlockEntityDo(level, pos, te -> {
                if (te.isRunning()) {

                    te.startDisassemblySlowdown();
                    return;
                }
                te.setAssembleNextTick(true);
            });
            return ItemInteractionResult.SUCCESS;
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    public void neighborChanged(final BlockState state, final Level level, final BlockPos pos, final Block blockIn, final BlockPos fromPos, final boolean isMoving) {

    }

    @Override
    public VoxelShape getShape(final BlockState pState, final BlockGetter pLevel, final BlockPos pPos, final CollisionContext ctx) {
        return AeroBlockShapes.PROPELLER_BEARING.get(pState.getValue(FACING));
    }

    @Override
    public Class<DirectionalGyroscopicPropellerBearingBlockEntity> getBlockEntityClass() {
        return DirectionalGyroscopicPropellerBearingBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends DirectionalGyroscopicPropellerBearingBlockEntity> getBlockEntityType() {
        return SimAdditionBlockEntityTypes.DIRECTIONAL_GYROSCOPIC_PROPELLER_BEARING.get();
    }
}

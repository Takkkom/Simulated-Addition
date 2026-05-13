package com.takkkom.simulated_addition.content.blocks.propeller.bearing.directional_gyroscopic_propeller_bearing;

import dev.eriksonn.aeronautics.content.blocks.propeller.bearing.gyroscopic_propeller_bearing.GyroscopicPropellerBearingBlockEntity;
import dev.eriksonn.aeronautics.content.blocks.propeller.bearing.propeller_bearing.PropellerBearingBlock;
import dev.ryanhcode.sable.Sable;
import dev.ryanhcode.sable.sublevel.SubLevel;
import dev.simulated_team.simulated.util.SimMathUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.joml.Vector2d;
import org.joml.Vector3d;

public class DirectionalGyroscopicPropellerBearingBlockEntity extends GyroscopicPropellerBearingBlockEntity {
    public static final double ANGLE_LIMITE = Math.toRadians(12);

    public DirectionalGyroscopicPropellerBearingBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public void setTilt(final Vector3d tilt, Vector3d target, final double maxStep) {

        final Direction direction = this.getBlockState().getValue(PropellerBearingBlock.FACING);
        final SubLevel subLevel = Sable.HELPER.getContaining(this);

        if (level != null && subLevel != null) {
            int northPower = level.getSignal(worldPosition.north(), Direction.NORTH);
            int southPower = level.getSignal(worldPosition.south(), Direction.SOUTH);
            int westPower = level.getSignal(worldPosition.west(), Direction.WEST);
            int eastPower = level.getSignal(worldPosition.east(), Direction.EAST);

            Vector3d inputVector;
            if (direction.getAxis() == Direction.Axis.Y) {
                inputVector = new Vector3d(southPower - northPower, 0, westPower - eastPower);
            }
            else {
                int upPower = level.getSignal(worldPosition.above(), Direction.UP);
                int downPower = level.getSignal(worldPosition.below(), Direction.DOWN);

                inputVector = new Vector3d(northPower - southPower, upPower - downPower, eastPower - westPower);
                inputVector = subLevel.logicalPose().orientation().transform(inputVector);
            }
            inputVector = inputVector.div(15);

            Vector3d rotOffset = inputVector.mul(ANGLE_LIMITE);

            target.rotateX(rotOffset.x);
            target.rotateY(rotOffset.y);
            target.rotateZ(rotOffset.z);
        }

        this.blockNormal.set(direction.getStepX(), direction.getStepY(), direction.getStepZ());
        SimMathUtils.clampIntoCone(target, this.blockNormal, ANGLE_LIMITE);

        target.lerp(this.blockNormal, 1 - this.getLerpDistance());

        final Vector3d difference = new Vector3d(target).sub(tilt);
        if (difference.lengthSquared() > maxStep * maxStep) {
            tilt.add(difference.normalize().mul(maxStep));
        } else {
            tilt.set(target);
        }
    }

    private double getLerpDistance() {
        double lerpDistance = 1f;
        if (this.getMovedContraption() == null) {
            lerpDistance = 0;
        }

        final double currentSpeed = Math.abs(this.getSpeed());
        if (currentSpeed < 1) {
            lerpDistance *= currentSpeed;
        }

        if (this.disassemblySlowdown) {
            lerpDistance *= this.slowdownController.getCountdown() / this.slowdownController.getMaxTime();
        }

        return lerpDistance;
    }
}

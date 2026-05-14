package com.takkkom.simulated_addition.content.blocks.analog_torsion_spring;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.transmission.SplitShaftBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.blockEntity.behaviour.scrollValue.ScrollValueBehaviour;
import dev.simulated_team.simulated.content.blocks.torsion_spring.TorsionSpringBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class AnalogTorsionSpringBlockEntity extends SplitShaftBlockEntity {
    public ScrollValueBehaviour angleInput;

    private float targetAngle;

    protected float angleLimit;
    protected float angle;
    protected float nextAngle;
    protected boolean angleIsNominal;
    protected boolean angleIsOver;
    protected boolean angleIsFinished;
    protected boolean angleIsNextOver;


    public AnalogTorsionSpringBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }

    @Override
    public void addBehaviours(final List<BlockEntityBehaviour> behaviours) {
        super.addBehaviours(behaviours);
        behaviours.add(this.angleInput = new TorsionSpringBlockEntity.TorsionSpringScrollValueBehaviour(this).between(1, 360));
        this.angleInput.onlyActiveWhen(this::showValue);
        this.angleInput.withCallback(this::updateAngleLimit);
        this.angleInput.setValue(90);
    }

    public boolean showValue() {
        return true;
    }

    public void updateAngleLimit(int angle) {
        angleLimit = angle;
    }

    public void setTargetAngle(float angle) {
        if (targetAngle == angle) return;
        targetAngle = angle;
        //reattachKinetic();
    }

    protected void reattachKinetic() {
        if (hasNetwork()) {
            detachKinetics();
            attachKinetics();
        }
    }

    @Override
    public void tick() {

        if (level.isClientSide) {
            super.tick();
            return;
        }

        super.tick();

        float input = 1.0f;

        float degreesPerTick = KineticBlockEntity.convertToAngular(getSpeed());
        int rotDir = (int)Math.signum(degreesPerTick);

        setTargetAngle(angleLimit * rotDir * input);

        boolean nextNominal = getNominal(rotDir);
        if (angleIsNominal != nextNominal) {
            angleIsNominal = nextNominal;
            reattachKinetic();
        }

        boolean nextOver = getOver(rotDir);
        if (angleIsOver != nextOver) {
            angleIsOver = nextOver;
            reattachKinetic();
        }

        boolean nextFinished = getFinished();
        if (angleIsFinished != nextFinished) {
            angleIsFinished = nextFinished;
            reattachKinetic();
        }

        nextAngle += getDirection() * degreesPerTick * rotDir * getOverFixMul();

        boolean nextNextOver = getNextOver(rotDir);
        if (angleIsNextOver != nextNextOver) {
            angleIsNextOver = nextNextOver;
            reattachKinetic();
        }

        //reattachKinetic();//!!!!!
        angle += getDirection() * degreesPerTick * rotDir * getOverFixMul();

        if (angleIsNextOver) {
            angle = targetAngle;
        }
    }

    @Override
    public float getRotationSpeedModifier(Direction face) {
        if (this.hasSource()) {
            if (face == this.getSourceFacing()) {
                return 1;
            }

            return getDirection() * getOverFixMul();
        }

        return 0;
    }

    private boolean getFinished() {
        return angle == targetAngle;
    }

    private boolean getNominal(int rotDir) {
        return angle * rotDir < targetAngle * rotDir;
    }

    private boolean getOver(int rotDir) {
        return angle * rotDir > targetAngle * rotDir;
    }

    private int getDirection() {
        if (angleIsNominal) return 1;
        else if (angleIsOver) return -1;
        return 0;
    }

    private boolean getNextOver(int dir) {
        if (angleIsNominal) return nextAngle * dir > targetAngle * dir;
        else if (angleIsOver) return nextAngle * dir < targetAngle * dir;
        return false;
    }

    private float getOverFixMul() {
        if (!angleIsNextOver) return 1;
        float degreesPerTick = KineticBlockEntity.convertToAngular(getSpeed());
        float dist = angle - targetAngle;
        return dist * getDirection() / degreesPerTick;
    }
}

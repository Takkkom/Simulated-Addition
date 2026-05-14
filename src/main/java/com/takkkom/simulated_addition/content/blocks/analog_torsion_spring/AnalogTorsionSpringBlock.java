package com.takkkom.simulated_addition.content.blocks.analog_torsion_spring;

import com.simibubi.create.content.kinetics.base.DirectionalAxisKineticBlock;
import com.simibubi.create.content.kinetics.base.IRotate;
import com.simibubi.create.foundation.block.IBE;
import com.takkkom.simulated_addition.index.SimAdditionBlockEntityTypes;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class AnalogTorsionSpringBlock extends DirectionalAxisKineticBlock implements IBE<AnalogTorsionSpringBlockEntity>, IRotate {

    public AnalogTorsionSpringBlock(Properties properties) {
        super(properties);
    }

    @Override
    public Class<AnalogTorsionSpringBlockEntity> getBlockEntityClass() {
        return AnalogTorsionSpringBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends AnalogTorsionSpringBlockEntity> getBlockEntityType() {
        //return SimAdditionBlockEntityTypes.ANALOG_TORSION_SPRING.get();
        return null;
    }
}

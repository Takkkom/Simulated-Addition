package com.takkkom.simulated_addition.index;

import com.simibubi.create.foundation.data.CreateRegistrate;
import com.takkkom.simulated_addition.SimulatedAddition;
import com.takkkom.simulated_addition.content.blocks.propeller.bearing.directional_gyroscopic_propeller_bearing.DirectionalGyroscopicPropellerBearingBlockEntity;
import com.takkkom.simulated_addition.content.blocks.propeller.bearing.directional_propeller_bearing.DirectionalPropellerBearingBlockEntity;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import dev.eriksonn.aeronautics.content.blocks.propeller.bearing.gyroscopic_propeller_bearing.GyroscopicPropellerBearingRenderer;
import dev.eriksonn.aeronautics.content.blocks.propeller.bearing.gyroscopic_propeller_bearing.GyroscopicPropellerBearingVisual;

public class SimAdditionBlockEntityTypes {
    private static final CreateRegistrate REGISTRATE = SimulatedAddition.registrate();

    public static final BlockEntityEntry<DirectionalPropellerBearingBlockEntity> DIRECTIONAL_PROPELLER_BEARING = REGISTRATE
            .blockEntity("directional_propeller_bearing", DirectionalPropellerBearingBlockEntity::new)
            .visual(() -> GyroscopicPropellerBearingVisual::new)
            .validBlocks(SimAdditionBlocks.DIRECTIONAL_PROPELLER_BEARING)
            .renderer(() -> GyroscopicPropellerBearingRenderer::new)
            .register();

    public static final BlockEntityEntry<DirectionalGyroscopicPropellerBearingBlockEntity> DIRECTIONAL_GYROSCOPIC_PROPELLER_BEARING = REGISTRATE
            .blockEntity("directional_gyroscopic_propeller_bearing", DirectionalGyroscopicPropellerBearingBlockEntity::new)
            .visual(() -> GyroscopicPropellerBearingVisual::new)
            .validBlocks(SimAdditionBlocks.DIRECTIONAL_GYROSCOPIC_PROPELLER_BEARING)
            .renderer(() -> GyroscopicPropellerBearingRenderer::new)
            .register();




    public static void register() {

    }
}

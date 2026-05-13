package com.takkkom.simulated_addition.config.server;

import net.createmod.catnip.config.ConfigBase;

public class SimAdditionKinetics extends ConfigBase {
    public final SimAdditionStress stressValues = nested(1, SimAdditionStress::new, Comments.stress);

    @Override
    public String getName() {
        return "kinetics";
    }

    private static class Comments {
        static String stress = "Fine tune the kinetic stats of individual components";
    }
}

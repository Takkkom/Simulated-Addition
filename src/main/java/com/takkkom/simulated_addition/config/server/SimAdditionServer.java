package com.takkkom.simulated_addition.config.server;

import net.createmod.catnip.config.ConfigBase;

public class SimAdditionServer extends ConfigBase {
    public final SimAdditionKinetics kinetics = nested(0, SimAdditionKinetics::new, Comments.kinetics);

    @Override
    public String getName() {
        return "server";
    }

    private static class Comments {
        static String kinetics = "Parameters and abilities of Create's kinetic mechanisms";
    }
}

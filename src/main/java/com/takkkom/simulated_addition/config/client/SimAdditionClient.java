package com.takkkom.simulated_addition.config.client;

import net.createmod.catnip.config.ConfigBase;

public class SimAdditionClient extends ConfigBase {
    public final ConfigBase.ConfigGroup client = group(0, "client",
            Comments.client);

    @Override
    public String getName() {
        return "client";
    }

    private static class Comments {
        static String client = "Client-only settings - If you're looking for general settings, look inside your worlds serverconfig folder!";
    }
}

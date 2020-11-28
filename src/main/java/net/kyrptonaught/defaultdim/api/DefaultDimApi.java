package net.kyrptonaught.defaultdim.api;

import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;

public class DefaultDimApi {
    private static Identifier defaultSpawnDim;

    public static void setDefaultSpawnDim(Identifier spawnDim) {
        if (defaultSpawnDim != null)
            System.out.println("DEFAULTDIM: error, default dim already set");
        else
            defaultSpawnDim = spawnDim;
    }

    public static Identifier getDefaultSpawnDim() {
        if (defaultSpawnDim != null)
            return defaultSpawnDim;
        return World.OVERWORLD.getValue();
    }

    public static RegistryKey<World> getRegistryKey(MinecraftServer server) {
        for (RegistryKey<World> registryKey : server.getWorldRegistryKeys()) {
            if (registryKey.getValue().equals(defaultSpawnDim))
                return registryKey;
        }
        System.out.println("DEFAULTDIM: error, dim id not found");
        return World.OVERWORLD;
    }
}

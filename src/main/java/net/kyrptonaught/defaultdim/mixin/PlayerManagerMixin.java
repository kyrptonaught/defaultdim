package net.kyrptonaught.defaultdim.mixin;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PlayerManager.class)
public abstract class PlayerManagerMixin {

    @Shadow
    public abstract CompoundTag loadPlayerData(ServerPlayerEntity player);

    @Redirect(method = "respawnPlayer", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;getOverworld()Lnet/minecraft/server/world/ServerWorld;"))
    public ServerWorld setCustomRespawn(MinecraftServer server) {
        return server.getWorld(World.NETHER);
    }

    @Redirect(method = "createPlayer", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;getOverworld()Lnet/minecraft/server/world/ServerWorld;"))
    public ServerWorld setCustomSpawn(MinecraftServer server) {
        return server.getWorld(World.NETHER);
    }

    @Redirect(method = "Lnet/minecraft/server/PlayerManager;onPlayerConnect(Lnet/minecraft/network/ClientConnection;Lnet/minecraft/server/network/ServerPlayerEntity;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;getWorld(Lnet/minecraft/util/registry/RegistryKey;)Lnet/minecraft/server/world/ServerWorld;"))
    public ServerWorld connect(MinecraftServer server, RegistryKey<World> key, ClientConnection connection, ServerPlayerEntity player) {
        if (this.loadPlayerData(player) == null) {
            return server.getWorld(World.NETHER);
        }
        return server.getWorld(key);
    }
}

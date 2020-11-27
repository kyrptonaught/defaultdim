package net.kyrptonaught.defaultdim.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerMixin {

    @Shadow
    public abstract Entity moveToWorld(ServerWorld destination);

    @Shadow
    @Final
    public MinecraftServer server;

    @Inject(method = "onSpawn", at = @At(value = "HEAD"))
    public void onSpawn(CallbackInfo ci) {
       // this.moveToWorld(this.server.getWorld(World.NETHER));
    }
}

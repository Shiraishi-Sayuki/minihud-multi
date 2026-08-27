package fi.dy.masa.minihud.mixin.world;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import fi.dy.masa.minihud.data.HudDataManager;

@Mixin(ServerWorld.class)
public class MixinServerWorld
{
    @Inject(method = "setSpawnPos", at = @At("TAIL"))
    private void minihud_checkSpawnPos(BlockPos pos, float angle, CallbackInfo ci)
    {
        ServerWorld self = (ServerWorld) (Object) this;
        MinecraftServer server = self.getServer();
        int spawnRadius = server.getSpawnRadius(self);
        HudDataManager.getInstance().setWorldSpawn(pos);
        HudDataManager.getInstance().setSpawnChunkRadius(spawnRadius, true);
    }

    @Inject(method = "tickWeather()V", at = @At("HEAD"))
    private void minihud_onTickWeather(CallbackInfo ci)
    {
        HudDataManager.getInstance().receiveWeatherData(new net.minecraft.nbt.NbtCompound());
    }
}

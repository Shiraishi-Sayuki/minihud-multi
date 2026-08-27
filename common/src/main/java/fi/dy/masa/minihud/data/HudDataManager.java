package fi.dy.masa.minihud.data;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import fi.dy.masa.malilib.interfaces.IClientTickHandler;

public class HudDataManager implements IClientTickHandler {
    private static final HudDataManager INSTANCE = new HudDataManager();
    public static HudDataManager getInstance() { return INSTANCE; }
    public void onGameInit() {}
    public void onWorldPre() {}
    public void onWorldJoin() {}
    public void reset(boolean b) {}
    public boolean hasServuxServer() { return false; }
    public boolean isSpawnChunkRadiusKnown() { return false; }
    public boolean isWorldSeedKnown() { return false; }
    public boolean isWorldSpawnKnown() { return false; }
    public int getSpawnChunkRadius() { return 0; }
    public long getWorldSeed() { return 0; }
    public BlockPos getWorldSpawn() { return BlockPos.ORIGIN; }
    public void setWorldSeed(long s) {}
    public void setWorldSpawn(BlockPos p) {}
    public void setSpawnChunkRadius(int r, boolean b) {}
    public boolean checkWorldSeed() { return false; }
    public boolean checkWorldSeed(net.minecraft.server.MinecraftServer server) { return false; }
    public void onHudDataSyncToggled(boolean b) {}
    public void onPacketFailure() {}
    public void onServerWeatherTick(NbtCompound nbt) {}
    public void resetWeatherData() {}
    public void refreshDataLoggers() {}
    public void requestSpawnMetadata() {}
    public void fromJson(com.google.gson.JsonObject o) {}
    public com.google.gson.JsonObject toJson() { return new com.google.gson.JsonObject(); }
    public boolean receiveMetadata(NbtCompound nbt) { return false; }
    public void receiveSpawnMetadata(NbtCompound nbt) {}
    public void receiveWeatherData(NbtCompound nbt) {}
    public void receiveDataLogger(NbtCompound nbt) {}
    public void handleBulkEntityData(int id, NbtCompound nbt) {}
    @Override public void onClientTick(net.minecraft.client.MinecraftClient mc) {}
    public void onClientTickPost(net.minecraft.client.MinecraftClient mc) {}
    public boolean hasValidWeatherCycle() { return false; }
    public boolean isWeatherThunder() { return false; }
    public boolean isWeatherRain() { return false; }
    public boolean isWeatherClear() { return true; }
    public int getThunderTime() { return -1; }
    public int getRainTime() { return -1; }
    public int getClearTime() { return -1; }
    public String getServuxVersion() { return "not_connected"; }
    public String getWorldSpawnAsString() { return "unknown"; }
    public boolean hasStoredWorldSeed() { return false; }

    public boolean isWorldSeedKnown(World world) { return false; }
    public long getWorldSeed(World world) { return 0L; }
    public boolean isWorldSpawnKnown(World world) { return false; }
    public BlockPos getWorldSpawn(World world) { return BlockPos.ORIGIN; }

}

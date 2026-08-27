package fi.dy.masa.minihud.data;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import fi.dy.masa.malilib.interfaces.IClientTickHandler;
import org.apache.commons.lang3.tuple.Pair;
import javax.annotation.Nullable;

public class EntitiesDataManager implements IClientTickHandler {
    private static final EntitiesDataManager INSTANCE = new EntitiesDataManager();
    public static EntitiesDataManager getInstance() { return INSTANCE; }
    public void onGameInit() {}
    public void onWorldPre() {}
    public void onWorldJoin() {}
    public void reset(boolean b) {}
    public void resetOpCheck() {}
    public int getCacheTimeout() { return 0; }
    public void handleVanillaQueryNbt(int id, NbtCompound nbt) {}
    @Nullable public Pair<net.minecraft.block.entity.BlockEntity, NbtCompound> requestBlockEntity(World w, BlockPos p) { return null; }
    @Nullable public Pair<Entity, NbtCompound> requestEntity(World w, int id) { return null; }
    public void onEntityDataSyncToggled(boolean b) {}
    @Override public void onClientTick(net.minecraft.client.MinecraftClient mc) {}
    public boolean hasServuxServer() { return false; }
    public String getServuxVersion() { return "not_connected"; }
    public int getBlockEntityCacheCount() { return 0; }
    public int getPendingBlockEntitiesCount() { return 0; }
    public int getEntityCacheCount() { return 0; }
    public int getPendingEntitiesCount() { return 0; }

    public boolean hasBackupStatus() { return false; }
    public boolean hasOperatorStatus() { return false; }

}

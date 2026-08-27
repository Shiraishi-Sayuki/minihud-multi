package fi.dy.masa.minihud.data;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import fi.dy.masa.malilib.interfaces.IClientTickHandler;

public class DebugDataManager implements IClientTickHandler {
    private static final DebugDataManager INSTANCE = new DebugDataManager();
    public static DebugDataManager getInstance() { return INSTANCE; }
    public void onGameInit() {}
    public void onWorldPre() {}
    public void onWorldJoin() {}
    public void reset(boolean b) {}
    public void requestMetadata() {}
    public void registerDebugService() {}
    public void unregisterDebugService() {}
    public void sendBeeDebugData(ServerWorld w, Object o) {}
    public void sendBrainDebugData(ServerWorld w, Object o) {}
    public void sendBreezeDebugData(ServerWorld w, Object o) {}
    public void sendChunkWatchingChange(ServerWorld w, Object o) {}
    public void sendGameEvent(ServerWorld w, Object o, Object p) {}
    public void sendGameEventListener(ServerWorld w, Object o) {}
    public void sendGoalSelector(ServerWorld w, Object o) {}
    public void sendPathfindingData(ServerWorld w, Object o) {}
    public void sendPoi(ServerWorld w, Object o) {}
    public void sendPoiAdditions(ServerWorld w, Object o) {}
    public void sendPoiRemoval(ServerWorld w, Object o) {}
    public void sendPointOfInterest(ServerWorld w, Object o) {}
    public void sendRaids(ServerWorld w, Object o) {}
    public void sendStructureStart(ServerWorld w, Object o) {}
    @Override public void onClientTick(net.minecraft.client.MinecraftClient mc) {}
}

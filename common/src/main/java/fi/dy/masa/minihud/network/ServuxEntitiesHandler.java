package fi.dy.masa.minihud.network;

import java.util.List;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import fi.dy.masa.malilib.network.IPluginChannelHandler;
import fi.dy.masa.malilib.network.ClientPacketChannelHandler;

public class ServuxEntitiesHandler implements IPluginChannelHandler
{
    private static final ServuxEntitiesHandler INSTANCE = new ServuxEntitiesHandler() {};
    public static ServuxEntitiesHandler getInstance() { return INSTANCE; }

    public static final Identifier CHANNEL_ID = new Identifier("servux:entities");

    private boolean payloadRegistered = false;

    @Override
    public List<Identifier> getChannels() { return List.of(CHANNEL_ID); }

    @Override
    public void onPacketReceived(PacketByteBuf buf) {
        // Stub for 1.20.1 - packet handling to be restored
    }

    public Identifier getNetworkChannel() { return CHANNEL_ID; }
    public Object getNetworkHandler() { return this; }
    public void registerPlayReceiver() { ClientPacketChannelHandler.getInstance().registerClientChannelHandler(this); payloadRegistered = true; }
    public void unregisterPlayReceiver() { ClientPacketChannelHandler.getInstance().unregisterClientChannelHandler(this); payloadRegistered = false; }
    public boolean isPlayRegistered(Identifier channel) { return payloadRegistered && channel.equals(CHANNEL_ID); }
    public void setPlayRegistered(Identifier channel) { if (channel.equals(CHANNEL_ID)) payloadRegistered = true; }
    public void reset(Identifier channel) { payloadRegistered = false; }
    public void resetFailures(Identifier channel) {}
    public void encodePacket(ServuxEntitiesPacket packet) {
        // Stub
    }

    public void encodeClientData(ServuxEntitiesPacket packet) {
        // Stub
    }
}

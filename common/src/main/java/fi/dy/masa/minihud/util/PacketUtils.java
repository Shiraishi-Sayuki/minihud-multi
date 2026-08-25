package fi.dy.masa.minihud.util;

import javax.annotation.Nullable;
import net.minecraft.network.packet.Packet;

import fi.dy.masa.minihud.MiniHUD;

// パケット送信ヘルパー - YarnとMojMapでメソッド名が違う(sendPacket/send)のでリフレクションで両対応する
// サーバー側ハンドラ(ServerPlayNetworkHandler)とクライアント側のどちらでも使えるようにObjectで受ける
public class PacketUtils
{
    public static boolean sendPacket(@Nullable Object networkHandler, Packet<?> packet)
    {
        if (networkHandler == null)
        {
            return false;
        }

        try
        {
            java.lang.reflect.Method method = networkHandler.getClass().getMethod("sendPacket", Packet.class);
            method.invoke(networkHandler, packet);
            return true;
        }
        catch (NoSuchMethodException e)
        {
            try
            {
                java.lang.reflect.Method method = networkHandler.getClass().getMethod("send", Packet.class);
                method.invoke(networkHandler, packet);
                return true;
            }
            catch (Exception e2)
            {
                MiniHUD.LOGGER.warn("PacketUtils#sendPacket: failed to send packet [{}]", packet.getClass().getName(), e2);
            }
        }
        catch (Exception e)
        {
            MiniHUD.LOGGER.warn("PacketUtils#sendPacket: failed to send packet [{}]", packet.getClass().getName(), e);
        }

        return false;
    }

    // インスタンス化させない - staticだけのクラスだから
    private PacketUtils() {}
}

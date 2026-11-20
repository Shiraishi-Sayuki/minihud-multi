package com.sayuki.minihud.neoforge;

import fi.dy.masa.minihud.network.ServuxDebugHandler;
import fi.dy.masa.minihud.network.ServuxDebugPacket;
import fi.dy.masa.minihud.network.ServuxEntitiesHandler;
import fi.dy.masa.minihud.network.ServuxEntitiesPacket;
import fi.dy.masa.minihud.network.ServuxHudHandler;
import fi.dy.masa.minihud.network.ServuxHudPacket;
import fi.dy.masa.minihud.network.ServuxStructuresHandler;
import fi.dy.masa.minihud.network.ServuxStructuresPacket;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

// NeoForge用ペイロード登録 - Servux系4チャンネルの受信を共通ハンドラに橋渡しする
public class NeoForgePayloads {
    // 登録処理 - registrarはRegisterPayloadHandlersEventからもらう、playToClientで受信だけ受ける
    // 受信処理はメインスレッドでやらないとワールドアクセスが危ないのでenqueueWorkで投げる
    public static void register(PayloadRegistrar registrar) {
        // optional() を付けないと NeoForge はこのチャンネルを「必須」とみなし、
        // Servux の無いサーバーへ接続しただけで mod 不一致で切断される
        PayloadRegistrar optional = registrar.optional();

        optional.playToClient(ServuxHudPacket.Payload.ID, ServuxHudPacket.Payload.CODEC, (payload, context) ->
                context.enqueueWork(() -> ServuxHudHandler.getInstance().receivePlayPayload(payload, null)));

        optional.playToClient(ServuxEntitiesPacket.Payload.ID, ServuxEntitiesPacket.Payload.CODEC, (payload, context) ->
                context.enqueueWork(() -> ServuxEntitiesHandler.getInstance().receivePlayPayload(payload, null)));

        optional.playToClient(ServuxStructuresPacket.Payload.ID, ServuxStructuresPacket.Payload.CODEC, (payload, context) ->
                context.enqueueWork(() -> ServuxStructuresHandler.getInstance().receivePlayPayload(payload, null)));

        optional.playToClient(ServuxDebugPacket.Payload.ID, ServuxDebugPacket.Payload.CODEC, (payload, context) ->
                context.enqueueWork(() -> ServuxDebugHandler.getInstance().receivePlayPayload(payload, null)));
    }
}

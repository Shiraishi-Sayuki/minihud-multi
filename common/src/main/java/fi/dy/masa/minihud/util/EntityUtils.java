package fi.dy.masa.minihud.util;

import fi.dy.masa.malilib.util.WorldUtils;
import fi.dy.masa.minihud.mixin.entity.IMixinEntity;
import fi.dy.masa.minihud.mixin.world.IMixinWorld;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.LeashKnotEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.text.Text;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class EntityUtils
{
    // entity.readNbt(nbt);
    @Deprecated(forRemoval = true)
    public static void loadNbtIntoEntity(Entity entity, NbtCompound nbt)
    {
        entity.fallDistance = nbt.getFloat("FallDistance");
        entity.setFireTicks(nbt.getShort("Fire"));
        if (nbt.contains("Air"))
        {
            entity.setAir(nbt.getShort("Air"));
        }

        entity.setOnGround(nbt.getBoolean("OnGround"));
        entity.setInvulnerable(nbt.getBoolean("Invulnerable"));
        entity.setPortalCooldown(nbt.getInt("PortalCooldown"));
        if (nbt.containsUuid("UUID"))
        {
            entity.setUuid(nbt.getUuid("UUID"));
        }

        if (nbt.contains("CustomName", NbtElement.STRING_TYPE))
        {
            String string = nbt.getString("CustomName");
            entity.setCustomName(Text.Serializer.fromJson(string));
        }

        entity.setCustomNameVisible(nbt.getBoolean("CustomNameVisible"));
        entity.setSilent(nbt.getBoolean("Silent"));
        entity.setNoGravity(nbt.getBoolean("NoGravity"));
        entity.setGlowing(nbt.getBoolean("Glowing"));
        entity.setFrozenTicks(nbt.getInt("TicksFrozen"));
        if (nbt.contains("Tags", NbtElement.LIST_TYPE))
        {
            entity.getCommandTags().clear();
            NbtList nbtList4 = nbt.getList("Tags", NbtElement.STRING_TYPE);
            int max = Math.min(nbtList4.size(), 1024);

            for (int i = 0; i < max; ++i)
            {
                entity.getCommandTags().add(nbtList4.getString(i));
            }
        }

        if (entity instanceof net.minecraft.entity.Entity)
        {
            readEntityEntityCustomData(entity, nbt);
        }
        else
        {
            ((IMixinEntity) entity).minihud_readCustomDataFromNbt(nbt);
        }
    }

    @Deprecated(forRemoval = true)
    private static void readEntityEntityCustomData(Entity entity, NbtCompound nbt)
    {
        MinecraftClient mc = MinecraftClient.getInstance();
        assert entity instanceof net.minecraft.entity.Entity;
        Entity leashable = (Entity) entity;
        ((IMixinEntity) entity).minihud_readCustomDataFromNbt(nbt);
        // 1.20.1 - leash data handling via NBT, no unresolvedLeashData
        // if (entity instanceof net.minecraft.entity.Entity) { /* stub */ }
    }

    @SuppressWarnings("unchecked")
    public static <T extends Entity> List<T> getEntitiesByClass(MinecraftClient mc, Class<T> entityClass, Box box, Predicate<? super T> predicate)
    {
        if (mc.world == null)
        {
            return Collections.emptyList();
        }

        List<Integer> entityIds = mc.world.getEntitiesByClass(entityClass, box, predicate).stream().map(Entity::getId).toList();
        World world = WorldUtils.getBestWorld(mc);
        return entityIds.stream().map(it -> (T) world.getEntityById(it))
                .filter(Objects::nonNull)
                .toList();
    }
}

package fi.dy.masa.minihud.data;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import net.minecraft.nbt.NbtCompound;

import javax.annotation.Nullable;

public enum ServuxDataLogger implements fi.dy.masa.malilib.config.IConfigOptionListEntry
{
    TPS             ("tps",             NbtCompound.CODEC),
    MOB_CAPS        ("mob_caps",        NbtCompound.CODEC)
    ;
    public static final ImmutableList<ServuxDataLogger> VALUES = ImmutableList.copyOf(values());

    private final String name;
    private final Codec<?> codec;

    ServuxDataLogger(String name, Codec<?> codec)
    {
        this.name = name;
        this.codec = codec;
    }

    @Override
    public String getStringValue()
    {
        return this.name;
    }

    public Codec<?> codec() { return this.codec; }

    @Override
    public String getDisplayName() { return this.name; }

    @Override
    public fi.dy.masa.malilib.config.IConfigOptionListEntry cycle(boolean forward) {
        int idx = VALUES.indexOf(this);
        int next = forward ? (idx + 1) % VALUES.size() : (idx - 1 + VALUES.size()) % VALUES.size();
        return VALUES.get(next);
    }

    @Override
    public fi.dy.masa.malilib.config.IConfigOptionListEntry fromString(String name) {
        ServuxDataLogger val = fromStringStatic(name);
        return val != null ? val : TPS;
    }

    public static @Nullable ServuxDataLogger fromStringStatic(String name)
    {
        for (ServuxDataLogger type : VALUES)
        {
            if (type.name.equalsIgnoreCase(name))
            {
                return type;
            }
        }

        return null;
    }
}

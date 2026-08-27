package fi.dy.masa.minihud.mixin.block;

import java.util.List;
import javax.annotation.Nullable;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.entity.effect.StatusEffect;

@Mixin(BeaconBlockEntity.class)
public interface IMixinBeaconBlockEntity
{
    @Accessor("level")
    int minihud_getLevel();

    @Accessor("field_19178")
    List<BeaconBlockEntity.BeamSegment> minihud_getBeamEmitter();

    @Accessor("primary")
    @Nullable StatusEffect minihud_getPrimary();

    @Accessor("secondary")
    @Nullable StatusEffect minihud_getSecondary();
}

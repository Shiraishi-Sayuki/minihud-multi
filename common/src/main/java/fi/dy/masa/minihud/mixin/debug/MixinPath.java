package fi.dy.masa.minihud.mixin.debug;

import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;

import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.ai.pathing.PathNode;
import net.minecraft.entity.ai.pathing.TargetPathNode;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Path.class)
public class MixinPath
{
    @Shadow @Final private List<PathNode> nodes;
    @Shadow private PathNode[] debugNodes;
    @Shadow private PathNode[] debugSecondNodes;
    @Shadow private Set<TargetPathNode> debugTargetNodes;
    @Shadow @Final private BlockPos target;

    @Inject(method = "toBuffer", at = @At("HEAD"))
    private void minihud_PathfindingFix(PacketByteBuf buf, CallbackInfo ci)
    {
        this.debugNodes = this.nodes.stream().filter((pathNode) -> !pathNode.visited).toArray(PathNode[]::new);
        this.debugSecondNodes = this.nodes.stream().filter((pathNode) -> pathNode.visited).toArray(PathNode[]::new);
        this.debugTargetNodes = Set.of(new TargetPathNode(this.target.getX(), this.target.getY(), this.target.getZ()));
    }
}

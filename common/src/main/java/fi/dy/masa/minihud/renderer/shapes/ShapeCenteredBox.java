package fi.dy.masa.minihud.renderer.shapes;
import com.google.gson.JsonObject;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.util.math.MatrixStack;
import org.joml.Matrix4f;
import fi.dy.masa.minihud.renderer.RenderObjectBase;
public class ShapeCenteredBox extends RenderObjectBase {
    public ShapeCenteredBox() { super(null, null); }
    public JsonObject toJson() { return new JsonObject(); }
    @Override public void uploadData(BufferBuilder buffer) {}
    @Override public void draw(MatrixStack matrixStack, Matrix4f projMatrix) {}
    @Override public void deleteGlResources() {}
}

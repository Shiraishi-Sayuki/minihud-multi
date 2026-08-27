package fi.dy.masa.minihud.info.entity;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import org.apache.commons.lang3.tuple.Pair;

import com.mojang.serialization.DynamicOps;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.painting.PaintingEntity;
import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.entity.passive.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtOps;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import fi.dy.masa.malilib.util.data.Constants;
import fi.dy.masa.malilib.util.nbt.NbtEntityUtils;
import fi.dy.masa.malilib.util.nbt.NbtKeys;
import fi.dy.masa.minihud.Reference;
import fi.dy.masa.minihud.config.InfoToggle;
import fi.dy.masa.minihud.info.InfoLine;
import fi.dy.masa.minihud.info.InfoLineContext;

public class InfoLineEntityVariant extends InfoLine
{
    private static final String VARIANT_KEY = Reference.MOD_ID+".info_line.entity_variant";

    public InfoLineEntityVariant(InfoToggle type)
    {
        super(type);
    }

    public InfoLineEntityVariant()
    {
        super(InfoToggle.ENTITY_VARIANT);
    }

    @Override
    public boolean succeededType() { return false; }

    @Override
    public List<Entry> parse(@Nonnull InfoLineContext ctx)
    {
        if (ctx.world() == null) return null;

        if (ctx.hasLiving() && ctx.hasNbt())
        {
            EntityType<?> entityType = NbtEntityUtils.getEntityTypeFromNbt(ctx.nbt());
            if (entityType == null) return null;

            return this.parseNbt(ctx.world(), entityType, ctx.nbt());
        }

        return ctx.ent() != null ? this.parseEnt(ctx.world(), ctx.ent()) : null;
    }

    @Override
    public List<Entry> parseNbt(@Nonnull World world, @Nonnull EntityType<?> entityType, @Nonnull NbtCompound nbt)
    {
        List<Entry> list = new ArrayList<>();
        DynamicRegistryManager registry = world.getRegistryManager();
        DynamicOps<NbtElement> ops = NbtOps.INSTANCE;

        if (entityType.equals(EntityType.AXOLOTL))
        {
            AxolotlEntity.Variant variant = NbtEntityUtils.getAxolotlVariantFromNbt(nbt);

            if (variant != null)
            {
                list.add(this.translate(VARIANT_KEY+".axolotl", variant.asString()));
            }
        }
        else if (entityType.equals(EntityType.CAT))
        {
            Pair<RegistryKey<CatVariant>, DyeColor> catPair = NbtEntityUtils.getCatVariantFromNbt(nbt);

            if (catPair.getLeft() != null)
            {
                list.add(this.translate(VARIANT_KEY+".cat", catPair.getLeft().getValue().getPath(), catPair.getRight().asString()));
            }
        }
        // todo 1.21.5+
//        else if (entityType.equals(EntityType.COW))
//        {
//            RegistryKey<CowVariant> variant = NbtEntityUtils.getCowVariantFromNbt(nbt, world.getRegistryManager());
//
//            if (variant != null)
//            {
//                list.add(this.translate(VARIANT_KEY+".cow", variant.toString()));
//            }
//        }
//        else if (entityType.equals(EntityType.CHICKEN))
//        {
//            RegistryKey<ChickenVariant> variant = NbtEntityUtils.getChickenVariantFromNbt(nbt, world.getRegistryManager());
//
//            if (variant != null)
//            {
//                list.add(this.translate(VARIANT_KEY+".chicken", variant.toString()));
//            }
//        }
        else if (entityType.equals(EntityType.MOOSHROOM))
        {
            MooshroomEntity.Type mooType = nbt.contains(NbtKeys.FOX_TYPE)
                                           ? MooshroomEntity.Type.CODEC.byId(nbt.getString(NbtKeys.FOX_TYPE))
                                           : null;

            if (mooType != null)
            {
                list.add(this.translate(VARIANT_KEY + ".mooshroom", mooType.asString()));
            }
        }
        else if (entityType.equals(EntityType.FOX))
        {
            FoxEntity.Type foxType = NbtEntityUtils.getFoxVariantFromNbt(nbt);

            if (foxType != null)
            {
                list.add(this.translate(VARIANT_KEY+".fox", foxType.asString()));
            }
        }
        else if (entityType.equals(EntityType.FROG))
        {
            FrogVariant variant = NbtEntityUtils.getFrogVariantFromNbt(nbt);

            if (variant != null)
            {
                list.add(this.translate(VARIANT_KEY+".frog", variant.toString()));
            }
        }
        else if (entityType.equals(EntityType.HORSE))
        {
            Pair<HorseColor, HorseMarking> horsePair = NbtEntityUtils.getHorseVariantFromNbt(nbt);

            if (horsePair.getLeft() != null)
            {
                list.add(this.translate(VARIANT_KEY+".horse", horsePair.getLeft().asString(), horsePair.getRight().name().toLowerCase()));
            }
        }
        else if (entityType.equals(EntityType.LLAMA) || entityType.equals(EntityType.TRADER_LLAMA))
        {
            Pair<LlamaEntity.Variant, Integer> llamaPair = NbtEntityUtils.getLlamaTypeFromNbt(nbt);

            if (llamaPair.getLeft() != null)
            {
                list.add(this.translate(VARIANT_KEY+".llama", llamaPair.getLeft().asString(), llamaPair.getRight()));
            }
        }
        else if (entityType.equals(EntityType.PAINTING))
        {
            Pair<Direction, PaintingVariant> paintingPair = NbtEntityUtils.getPaintingDataFromNbt(nbt, registry);

            if (paintingPair.getRight() != null)
            {
                // todo 1.21.3+
//                Optional<net.minecraft.text.Text> title = paintingPair.getRight().title();
//                Optional<net.minecraft.text.Text> author = paintingPair.getRight().author();
//
//                if (title.isPresent() && author.isPresent())
//                {
//                    list.add(this.translate(VARIANT_KEY+".painting.both", title.get().getString(), author.get().getString()));
//                }
//                else if (title.isPresent())
//                {
//                    list.add(this.translate(VARIANT_KEY+".painting.title_only", title.get().getString()));
//                }
//                else
//                {
//                    author.ifPresent(text -> list.add(this.translate(VARIANT_KEY + ".painting.author_only", text.getString())));
//                }

                list.add(this.translate(VARIANT_KEY+".painting.title_only", paintingPair.getRight().toString()));
            }
        }
        else if (entityType.equals(EntityType.PARROT))
        {
            ParrotEntity.Variant variant = NbtEntityUtils.getParrotVariantFromNbt(nbt);

            if (variant != null)
            {
                list.add(this.translate(VARIANT_KEY+".parrot", variant.asString()));
            }
        }
        // todo 1.21.5+
//        else if (entityType.equals(EntityType.PIG))
//        {
//            RegistryKey<PigVariant> variant = NbtEntityUtils.getPigVariantFromNbt(nbt, world.getRegistryManager());
//
//            if (variant != null)
//            {
//                list.add(this.translate(VARIANT_KEY+".pig", variant.toString()));
//            }
//        }
        else if (entityType.equals(EntityType.RABBIT))
        {
            RabbitEntity.RabbitType rabbitType = NbtEntityUtils.getRabbitTypeFromNbt(nbt);

            if (rabbitType != null)
            {
                list.add(this.translate(VARIANT_KEY+".rabbit", rabbitType.asString()));
            }
        }
        // todo 1.21.3+
//        else if (entityType.equals(EntityType.SALMON))
//        {
//            SalmonEntity.Variant salmonVariant = NbtEntityUtils.getSalmonVariantFromNbt(nbt);
//
//            if (salmonVariant != null)
//            {
//                list.add(this.translate(VARIANT_KEY+".salmon", salmonVariant.asString()));
//            }
//        }
        else if (entityType.equals(EntityType.SHEEP))
        {
            DyeColor color = NbtEntityUtils.getSheepColorFromNbt(nbt);

            if (color != null)
            {
                list.add(this.translate(VARIANT_KEY+".sheep", color.asString()));
            }
        }
        else if (entityType.equals(EntityType.TROPICAL_FISH))
        {
//            TropicalFishEntity.Variety variant = NbtEntityUtils.getFishVariantFromNbt(nbt);
            // 1.20.1 - variant is stored as int bucketVariant
            TropicalFishEntity.Variant variant = null;
            if (nbt.contains(NbtKeys.VARIANT_2, Constants.NBT.TAG_INT)) {
                int id = nbt.getInt(NbtKeys.VARIANT_2);
                variant = new TropicalFishEntity.Variant(TropicalFishEntity.Variety.KOB, net.minecraft.util.DyeColor.ORANGE, net.minecraft.util.DyeColor.GRAY);
            } else if (nbt.contains(NbtKeys.BUCKET_VARIANT, Constants.NBT.TAG_INT)) {
                int id = nbt.getInt(NbtKeys.BUCKET_VARIANT);
                variant = new TropicalFishEntity.Variant(TropicalFishEntity.Variety.KOB, net.minecraft.util.DyeColor.ORANGE, net.minecraft.util.DyeColor.GRAY);
            }

            if (variant != null)
            {
                list.add(this.translate(VARIANT_KEY+".tropical_fish", variant.variety().asString(), variant.baseColor().asString(), variant.patternColor().asString()));
            }
        }
        else if (entityType.equals(EntityType.WOLF))
        {
            // Wolf variant doesn't exist in 1.20.1
        }

        return list;
    }

    @Override
    public List<Entry> parseEnt(@Nonnull World world, @Nonnull Entity ent)
    {
        List<Entry> list = new ArrayList<>();

        if (ent instanceof AxolotlEntity axolotl) { list.add(this.translate(VARIANT_KEY + ".axolotl", axolotl.getVariant().asString())); }
        else if (ent instanceof CatEntity cat)
            {
                CatVariant variant = cat.getVariant();
                list.add(this.translate(VARIANT_KEY + ".cat", variant.toString(), cat.getCollarColor().asString()));
            }
            // todo 1.21.5+
//            case ChickenEntity chicken -> list.add(this.translate(VARIANT_KEY + ".chicken", chicken.getVariant().getKey().orElse(ChickenVariants.DEFAULT).getValue().getPath()));
//            case CowEntity cow -> list.add(this.translate(VARIANT_KEY + ".cow", cow.getVariant().getKey().orElse(CowVariants.DEFAULT).getValue().getPath()));
        else if (ent instanceof MooshroomEntity mooshroom) { list.add(this.translate(VARIANT_KEY + ".mooshroom", mooshroom.getVariant().asString())); }
        else if (ent instanceof FoxEntity fox) { list.add(this.translate(VARIANT_KEY + ".fox", fox.getVariant().asString())); }
        else if (ent instanceof FrogEntity frog) { list.add(this.translate(VARIANT_KEY + ".frog", frog.getVariant().toString())); }
        else if (ent instanceof HorseEntity horse) { list.add(this.translate(VARIANT_KEY + ".horse", horse.getVariant().asString(), horse.getMarking().name().toLowerCase())); }
        else if (ent instanceof LlamaEntity llama) { list.add(this.translate(VARIANT_KEY + ".llama", llama.getVariant().asString(), llama.getStrength())); }
        else if (ent instanceof PaintingEntity painting)
            {
                PaintingVariant paintingVariant = painting.getVariant().value();

                if (paintingVariant != null)
                {
                    list.add(this.translate(VARIANT_KEY + ".painting.title_only", paintingVariant.toString()));
                }
            }
        else if (ent instanceof ParrotEntity parrot) { list.add(this.translate(VARIANT_KEY + ".parrot", parrot.getVariant().asString())); }
            // todo 1.21.5+
//            case PigEntity pig -> list.add(this.translate(VARIANT_KEY + ".pig", pig.getVariant().getKey().orElse(PigVariants.DEFAULT).getValue().getPath()));
        else if (ent instanceof RabbitEntity rabbit) { list.add(this.translate(VARIANT_KEY + ".rabbit", rabbit.getVariant().asString())); }
            // todo 1.21.3+
//            case SalmonEntity salmon -> list.add(this.translate(VARIANT_KEY + ".salmon", salmon.getVariant().asString()));
        else if (ent instanceof SheepEntity sheep) { list.add(this.translate(VARIANT_KEY + ".sheep", sheep.getColor().asString())); }
        else if (ent instanceof TropicalFishEntity fish)
            {
                DyeColor baseColor = fish.getBaseColorComponents();
                DyeColor patternColor = fish.getPatternColorComponents();
                TropicalFishEntity.Variety variety = fish.getVariant();

                list.add(this.translate(VARIANT_KEY + ".tropical_fish", variety.asString(), baseColor.asString(), patternColor.asString()));
            }
        else if (ent instanceof WolfEntity wolf)
            {
                net.minecraft.util.DyeColor collar = wolf.getCollarColor(); // 1.20.1 no variant
                DyeColor collar2 = wolf.getCollarColor();
                list.add(this.translate(VARIANT_KEY + ".wolf", "minecraft:wolf", collar.asString()));
            }

        return list;
    }
}

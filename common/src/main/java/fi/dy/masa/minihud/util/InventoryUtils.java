package fi.dy.masa.minihud.util;

import java.util.Iterator;
import java.util.List;

import net.minecraft.block.entity.BeehiveBlockEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import fi.dy.masa.minihud.event.RenderHandler;

public class InventoryUtils
{
    public static Inventory getInventory(World world, BlockPos pos)
    {
        Inventory inv = fi.dy.masa.malilib.util.InventoryUtils.getInventory(world, pos);

        if ((inv == null || inv.isEmpty()) && !DataStorage.getInstance().hasIntegratedServer())
        {
        // requestBlockEntityAt doesn't exist in 1.20.1 port
        }

        return inv;
    }

    public static int recalculateBundleSize(Object bundle, int maxCount) { return 0; }

    /*
    public static Inventory getNbtInventoryHorseFix(@Nonnull NbtCompound nbt, int slotCount, @Nonnull DynamicRegistryManager registry)
    {
        DefaultedList<ItemStack> horseEquipment = NbtEntityUtils.getHorseEquipmentFromNbt(nbt, registry);
        if (slotCount > 256)
        {
            slotCount = 256;
        }

        if (!nbt.contains(NbtKeys.ITEMS))
        {
            if (!horseEquipment.getLast().isEmpty())
            {
                SimpleInventory inv = new SimpleInventory(1);
                inv.setStack(0, horseEquipment.getLast().copy());

                return inv;
            }
            else
            {
                if (nbt.contains(NbtKeys.ITEM))
                {
                    ItemStack entry = fi.dy.masa.malilib.util.InventoryUtils.fromNbtOrEmpty(registry, nbt.get(NbtKeys.ITEM));
                    SimpleInventory inv = new SimpleInventory(1);
                    inv.setStack(0, entry.copy());

                    return inv;
                }

                return null;
            }
        }
        else
        {
            if (slotCount < 0)
            {
                NbtList list = nbt.getList(NbtKeys.ITEMS, 10);
                slotCount = list.size();
            }

            SimpleInventory inv = new SimpleInventory(slotCount + 1);
            DefaultedList<ItemStack> items = DefaultedList.ofSize(slotCount, ItemStack.EMPTY);
            Inventories.readNbt(nbt, items, registry);
            inv.setStack(0, horseEquipment.getLast().copy());

            if (!items.isEmpty())
            {
                for (int i = 0; i < slotCount; ++i)
                {
                    inv.setStack(i + 1, items.get(i));
                }
            }

            return inv;
        }
    }
     */
}

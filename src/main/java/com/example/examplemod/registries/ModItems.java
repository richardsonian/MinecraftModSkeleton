package com.example.examplemod.registries;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.items.ItemDemoItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * Registry for all items in the mod.
 */
@ObjectHolder(ExampleMod.MODID) //all static fields will automatically be injected.
public class ModItems {
    /*
        Add public static final item fields here: They will be auto-filled with the instance of the item object
        which was created in init() and added to the registry. If an item has a different variable name than its
        registry name, it needs an @ObjectHolder tag.
        Example:
        public static final ItemClass item_name = null;
     */
    public static final Item demo_classless_item = null;
    public static final ItemDemoItem demoitem = null;


    /**
     * Returns a list of instances of items that are to be added to the registry.
     * @return The list.
     */
    public static List<Item> getInstancesForRegistry() {
        List<Item> items = new ArrayList<>();

        /* Add one instance of each item here */
        items.add(new Item().setRegistryName("demo_classless_item").setUnlocalizedName(ExampleMod.MODID + ".demo_classless_item").setCreativeTab(CreativeTabs.MISC));
        items.add(new ItemDemoItem());
        /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
        return items;
    }

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        /*
            Init the models for all the items. Each of the public static final fields at the top must also
            have a line here. If it has its own class, call .initModel() on the field. If not,
            call .setCustomModelResourceLocation()

            Example:

            demoitem.initModel();
            -or-
            ModelLoader.setCustomModelResourceLocation(anon_item, 0, new ModelResourceLocation(anon_item.getRegistryName(), "inventory"));
         */
        demoitem.initModel();
        ModelLoader.setCustomModelResourceLocation(demo_classless_item, 0, new ModelResourceLocation(demo_classless_item.getRegistryName(), "inventory"));
    }

    /**
     * Gets all the items in static fields at the top of the class which were filled by the registry.
     * Useful if you need to get a list of all the items registered.
     * @return A list of the items.
     */
    public static List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();

        Field[] fields = ModItems.class.getDeclaredFields();
        for (Field f : fields) {
            if(Modifier.isStatic(f.getModifiers())) {
                Object fieldValue = null;
                try {
                    fieldValue = f.get(null);
                } catch (IllegalAccessException e) {
                    //Shouldn't happen
                    System.err.println("Error getting all items from static fields.");
                    e.printStackTrace();
                }
                if (fieldValue instanceof Item) {
                    items.add((Item) fieldValue);
                }
            }
        }
        return items;
    }
}

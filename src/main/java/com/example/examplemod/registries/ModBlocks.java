package com.example.examplemod.registries;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.blocks.BlockDemoBlock;
import net.minecraft.block.Block;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

@ObjectHolder(ExampleMod.MODID)
public class ModBlocks {
    /*
        Add public static final item fields here: They will be auto-filled with the instance of the item object
        which was created in init() and added to the registry. If an item has a different variable name than its
        registry name, it needs an @ObjectHolder tag.
        Example:
        public static final ItemClass item_name = null;
     */
    public static final BlockDemoBlock demoblock = null;

    /**
     * Returns a list of instances of blocks that are to be added to the registry.
     * @return The list.
     */
    public static List<Block> getInstancesForRegistry() {
        List<Block> blocks = new ArrayList<>();

        /* Add one instance of each block here */
        blocks.add(new BlockDemoBlock());
        /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

        return blocks;
    }

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        /*
            Init the models for all the blocks' Items. Each of the public static final fields at the top must also
            have a line here. If it has its own class, call .initModel() on the field. If not,
            call .setCustomModelResourceLocation() on

            Example:

            demoitem.initModel();
            -or-
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(anon_block), 0,
                new ModelResourceLocation(anon_block.getRegistryName(), "inventory"));
         */
        demoblock.initModel();
    }

    /**
     * Gets all the blocks in static fields at the top of the class which were filled by the registry.
     * Useful if you need to get a list of all the blocks registered.
     * @return A list of the blocks.
     */
    public static List<Block> getAllBlocks() {
        List<Block> blocks = new ArrayList<>();

        Field[] fields = ModBlocks.class.getDeclaredFields();
        for (Field f : fields) {
            if(Modifier.isStatic(f.getModifiers())) {
                Object fieldValue = null;
                try {
                    fieldValue = f.get(null);
                } catch (IllegalAccessException e) {
                    //Shouldn't happen
                    System.err.println("Error getting all blocks from static fields.");
                    e.printStackTrace();
                }
                if (fieldValue instanceof Block) {
                    blocks.add((Block) fieldValue);
                }
            }
        }
        return blocks;
    }
}
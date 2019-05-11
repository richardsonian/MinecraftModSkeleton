package com.example.examplemod.items;

import com.example.examplemod.ExampleMod;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemDemoItem extends Item {
    public ItemDemoItem() {
        setRegistryName("demoitem");  // The unique name (within your mod) that identifies this item
        setUnlocalizedName(ExampleMod.MODID + ".demoitem");     // Used for localization (en_US.lang)
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}

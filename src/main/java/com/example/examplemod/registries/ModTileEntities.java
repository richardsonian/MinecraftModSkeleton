package com.example.examplemod.registries;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.tileEntities.TileDemoBlock;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModTileEntities {
    public static void registerTileEntities() {
        //multiblock radio
        GameRegistry.registerTileEntity(TileDemoBlock.class, getTeResourceLocation(ModBlocks.demoblock));
    }

    private static ResourceLocation getTeResourceLocation(Block block) {
        return new ResourceLocation(ExampleMod.MODID, block.getRegistryName().getResourcePath() + "_tileEntity");
    }
}

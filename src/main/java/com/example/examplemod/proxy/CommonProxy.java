package com.example.examplemod.proxy;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.guis.GuiHandler;
import com.example.examplemod.registries.ModBlocks;
import com.example.examplemod.registries.ModItems;
import com.example.examplemod.registries.ModNetworkMessages;
import com.example.examplemod.registries.ModTileEntities;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

/**
 * A proxy for both server- and client- side initialization events
 */
@Mod.EventBusSubscriber
public class CommonProxy {
    public void preInit(FMLPreInitializationEvent e) {
        ModNetworkMessages.init(ExampleMod.MODID);
    }

    public void init(FMLInitializationEvent e) {
        ModTileEntities.registerTileEntities(); //Put here so that @ObjectHolder fields have been filled (after registry events).

        NetworkRegistry.INSTANCE.registerGuiHandler(ExampleMod.instance, new GuiHandler());
    }

    public void postInit(FMLPostInitializationEvent e) {
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        //Register Blocks
        for(Block block : ModBlocks.getInstancesForRegistry()) {
            event.getRegistry().register(block);
        }
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        //Block Items
        for(Block block : ModBlocks.getAllBlocks()) {
            event.getRegistry().register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
        }

        //Normal Items
        for(Item item : ModItems.getInstancesForRegistry()) {
            event.getRegistry().register(item);
        }
    }
}
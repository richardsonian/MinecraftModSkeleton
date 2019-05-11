package com.example.examplemod.blocks;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.registries.ModGuis;
import com.example.examplemod.tileEntities.TileDemoBlock;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Block is associated with its GUI and Container through ModGuis
 */
public class BlockDemoBlock extends Block implements ITileEntityProvider {
    protected Class<? extends TileEntity> tileEntityClass;

    public BlockDemoBlock() {
        super(Material.IRON);
        this.tileEntityClass = TileDemoBlock.class;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote) {
            return true;
        }
        TileEntity te = worldIn.getTileEntity(pos);
        if (!(tileEntityClass.isInstance(te))) {
            return false;
        }

        playerIn.openGui(ExampleMod.instance, ModGuis.getGuiIDFromTileEntityClass(tileEntityClass), worldIn, pos.getX(), pos.getY(),
                pos.getZ());
        return true;
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0,
                new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {return true;}

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        try {
            return tileEntityClass.getConstructor().newInstance();
        } catch(ReflectiveOperationException e) {
            e.printStackTrace();
            System.err.println("Error creating new TileEntity of type " + tileEntityClass.getName() + " for block " + this);
            return null;
        }
    }
}

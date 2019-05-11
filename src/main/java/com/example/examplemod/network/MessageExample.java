package com.example.examplemod.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class MessageExample implements IMessage{
    private int content;

    public MessageExample() {}
    public MessageExample(int content) {
        this.content = content;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        content = buf.readInt();
    }
    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(content);
    }

    public static class Handler implements IMessageHandler<MessageExample, IMessage> {
        @Override
        public IMessage onMessage(MessageExample message, MessageContext ctx) {
            if(ctx.side == Side.CLIENT) {
                FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(
                        () -> handle(message, ctx));

            }

            return null;
        }

        private void handle(MessageExample message, MessageContext ctx) {
            Minecraft mc = Minecraft.getMinecraft();
            WorldClient world = mc.world;
            EntityPlayerSP player = mc.player;

            player.sendStatusMessage(new TextComponentString(TextFormatting.GREEN +
                    "Message from server received! Content=" + message.content), false);

        }
    }
}

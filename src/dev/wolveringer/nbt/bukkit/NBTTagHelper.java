package dev.wolveringer.nbt.bukkit;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import dev.wolveringer.nbt.NBTBase;
import dev.wolveringer.nbt.NBTCompressedStreamTools;
import dev.wolveringer.nbt.NBTTagCompound;
import dev.wolveringer.nbt.NBTTagList;

public class NBTTagHelper {
	public static NBTTagCompound convert(net.minecraft.server.v1_8_R3.NBTTagCompound comp) {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			net.minecraft.server.v1_8_R3.NBTCompressedStreamTools.a(comp, (OutputStream) os);
		}
		catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			return NBTCompressedStreamTools.read(new ByteArrayInputStream(os.toByteArray()));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static NBTTagList convert(net.minecraft.server.v1_8_R3.NBTTagList comp) {
		return convert(comp);
	}
	
	public static NBTBase convert(net.minecraft.server.v1_8_R3.NBTBase comp) {
		net.minecraft.server.v1_8_R3.NBTTagCompound nbt = new net.minecraft.server.v1_8_R3.NBTTagCompound();
		nbt.set("value", comp);
		return convert(nbt).get("value");
	}
	
	public static net.minecraft.server.v1_8_R3.NBTTagCompound convert(NBTTagCompound comp) {
		try {
			return net.minecraft.server.v1_8_R3.NBTCompressedStreamTools.a(new ByteArrayInputStream(NBTCompressedStreamTools.toByte(comp)));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static net.minecraft.server.v1_8_R3.NBTTagList convert(NBTTagList comp) {
		return convert(comp);
	}
	
	public static net.minecraft.server.v1_8_R3.NBTBase convert(NBTBase comp) {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.set("value", comp);
		return convert(nbt).get("value");
	}
}

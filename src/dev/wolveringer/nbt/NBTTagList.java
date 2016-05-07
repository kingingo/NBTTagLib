package dev.wolveringer.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.util.ArrayList;
import java.util.List;

public class NBTTagList extends NBTBase {
	private List<NBTBase> list = new ArrayList<NBTBase>();
	private byte type = 0;

	void write(DataOutput paramDataOutput) throws Exception {
		if(!this.list.isEmpty()){
			this.type = ((NBTBase) this.list.get(0)).getTypeId();
		}else{
			this.type = 0;
		}
		paramDataOutput.writeByte(this.type);
		paramDataOutput.writeInt(this.list.size());
		for(int i = 0;i < this.list.size();i++){
			((NBTBase) this.list.get(i)).write(paramDataOutput);
		}
	}

	void load(DataInput paramDataInput, int paramInt, NBTReadLimiter paramNBTReadLimiter) throws Exception {
		if(paramInt > 512){
			throw new RuntimeException("Tried to read NBT tag with too high complexity, depth > 512");
		}
		paramNBTReadLimiter.readBytes(8L);
		this.type = paramDataInput.readByte();
		int i = paramDataInput.readInt();

		this.list = new ArrayList<NBTBase>();
		for(int j = 0;j < i;j++){
			NBTBase localNBTBase = NBTBase.createTag(this.type);
			localNBTBase.load(paramDataInput, paramInt + 1, paramNBTReadLimiter);
			this.list.add(localNBTBase);
		}
	}

	public byte getTypeId() {
		return 9;
	}

	public String toString() {
		String str = "[";
		int i = 0;
		for(NBTBase localNBTBase : this.list){
			str = str + "" + i + ':' + localNBTBase + ',';
			i++;
		}
		return str + "]";
	}

	@Override
	String toFormatedString(String in) {
		if(this.list.isEmpty())
			return "[]";
		String str = "[\n";
		int i = 0;
		for(NBTBase localNBTBase : this.list){
			str = str + "\n" + in + format(i) + ": " + localNBTBase.toFormatedString("");
			i++;
		}
		return str.replaceFirst("\n", "") + "\n" + in.substring(1) + "]";
	}

	public String format(int i) {
		String out = i + "";
		String x = (list.size()-1) + "";
		while (x.length() > out.length()){
			out = "0" + out;
		}
		return out;
	}

	public void add(NBTBase paramNBTBase) {
		if(this.type == 0){
			this.type = paramNBTBase.getTypeId();
		}else if(this.type != paramNBTBase.getTypeId()){
			System.err.println("WARNING: Adding mismatching tag types to tag list");
			return;
		}
		this.list.add(paramNBTBase);
	}

	public NBTTagCompound get(int paramInt) {
		if((paramInt < 0) || (paramInt >= this.list.size())){
			return new NBTTagCompound();
		}
		NBTBase localNBTBase = (NBTBase) this.list.get(paramInt);
		if(localNBTBase.getTypeId() == 10){
			return (NBTTagCompound) localNBTBase;
		}
		return new NBTTagCompound();
	}

	public int[] getIntArray(int paramInt) {
		if((paramInt < 0) || (paramInt >= this.list.size())){
			return new int[0];
		}
		NBTBase localNBTBase = (NBTBase) this.list.get(paramInt);
		if(localNBTBase.getTypeId() == 11){
			return ((NBTTagIntArray) localNBTBase).getData();
		}
		return new int[0];
	}

	public double getDouble(int paramInt) {
		if((paramInt < 0) || (paramInt >= this.list.size())){
			return 0.0D;
		}
		NBTBase localNBTBase = (NBTBase) this.list.get(paramInt);
		if(localNBTBase.getTypeId() == 6){
			return ((NBTTagDouble) localNBTBase).asDouble();
		}
		return 0.0D;
	}

	public float getFloat(int paramInt) {
		if((paramInt < 0) || (paramInt >= this.list.size())){
			return 0.0F;
		}
		NBTBase localNBTBase = (NBTBase) this.list.get(paramInt);
		if(localNBTBase.getTypeId() == 5){
			return ((NBTTagFloat) localNBTBase).asFloat();
		}
		return 0.0F;
	}

	public String getString(int paramInt) {
		if((paramInt < 0) || (paramInt >= this.list.size())){
			return "";
		}
		NBTBase localNBTBase = (NBTBase) this.list.get(paramInt);
		if(localNBTBase.getTypeId() == 8){
			return localNBTBase.toString();
		}
		return localNBTBase.toString();
	}

	public int size() {
		return this.list.size();
	}

	public List<NBTBase> asList() {
		return list;
	}

	public NBTBase clone() {
		NBTTagList localNBTTagList = new NBTTagList();
		localNBTTagList.type = this.type;
		for(NBTBase localNBTBase1 : this.list){
			NBTBase localNBTBase2 = localNBTBase1.clone();
			localNBTTagList.list.add(localNBTBase2);
		}
		return localNBTTagList;
	}

	public boolean equals(Object paramObject) {
		if(super.equals(paramObject)){
			NBTTagList localNBTTagList = (NBTTagList) paramObject;
			if(this.type == localNBTTagList.type){
				return this.list.equals(localNBTTagList.list);
			}
		}
		return false;
	}

	public int hashCode() {
		return super.hashCode() ^ this.list.hashCode();
	}

	public int getType() {
		return this.type;
	}
}

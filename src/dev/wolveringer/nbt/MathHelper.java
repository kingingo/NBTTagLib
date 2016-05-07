package dev.wolveringer.nbt;

import java.util.Random;

public class MathHelper {

	private static float[] a = new float[65536];
	static{
		for(int i = 0;i < 65536;++i){
			a[i] = (float) Math.sin(i * 3.141592653589793D * 2.0D / 65536.0D);
		}
	}

	public static double getHighestDouble(double d0, double d1) {
		if(d0 < 0.0D){
			d0 = -d0;
		}

		if(d1 < 0.0D){
			d1 = -d1;
		}

		return d0 > d1 ? d0 : d1;
	}

	public static double a(double d0, double d1, double d2) {
		return d0 < d1 ? d1 : d0 > d2 ? d2 : d0;
	}

	public static float a(float f, float f1, float f2) {
		return f < f1 ? f1 : f > f2 ? f2 : f;
	}

	public static int a(int i) {
		return i >= 0 ? i : -i;
	}

	public static int a(int i, int j, int k) {
		return i < j ? j : i > k ? k : i;
	}

	public static double a(long[] along) {
		long i = 0L;
		long[] along1 = along;
		int j = along.length;

		for(int k = 0;k < j;++k){
			long l = along1[k];

			i += l;
		}

		return (double) i / (double) along.length;
	}

	public static double random(Random random, double d0, double d1) {
		return d0 >= d1 ? d0 : random.nextDouble() * (d1 - d0) + d0;
	}

	public static float random(Random random, float f, float f1) {
		return f >= f1 ? f : random.nextFloat() * (f1 - f) + f;
	}

	public static double a(String s, double d0) {
		double d1 = d0;

		try{
			d1 = Double.parseDouble(s);
		}catch (Throwable throwable){
			;
		}

		return d1;
	}

	public static double a(String s, double d0, double d1) {
		double d2 = d0;

		try{
			d2 = Double.parseDouble(s);
		}catch (Throwable throwable){
			;
		}

		if(d2 < d1){
			d2 = d1;
		}

		return d2;
	}

	public static int parseInteger(String raw, int i) {
		int j = i;
		try{
			j = Integer.parseInt(raw);
		}catch (Throwable throwable){
			;
		}

		return j;
	}

	public static int parseInteger(String s, int default_value, int min) {
		int value = default_value;

		try{
			value = Integer.parseInt(s);
		}catch (Throwable throwable){
		}

		if(value < min){
			value = min;
		}

		return value;
	}

	public static float abs(float f) {
		return f >= 0.0F ? f : -f;
	}

	public static double b(double d0, double d1, double d2) {
		return d2 < 0.0D ? d0 : d2 > 1.0D ? d1 : d0 + (d1 - d0) * d2;
	}

	public static final float sqrt(float f) {
		return (float) Math.sqrt(f);
	}

	public static final float cos(float f) {
		return a[(int) (f * 10430.378F + 16384.0F) & '\uffff'];
	}

	public static long d(double d0) {
		long i = (long) d0;

		return d0 < i ? i - 1L : i;
	}

	public static int d(float f) {
		int i = (int) f;

		return f < i ? i - 1 : i;
	}

	public static int f(double d0) {
		int i = (int) d0;

		return d0 > i ? i + 1 : i;
	}

	public static int f(float f) {
		int i = (int) f;

		return f > i ? i + 1 : i;
	}

	public static int floor(double d0) {
		int i = (int) d0;

		return d0 < i ? i - 1 : i;
	}

	public static int nextInt(Random random, int min, int max) {
		return min >= max ? min : random.nextInt(max - min + 1) + min;
	}

	public static final float sin(float f) {
		return a[(int) (f * 10430.378F) & '\uffff'];
	}

	public static final float sqrt(double d0) {
		return (float) Math.sqrt(d0);
	}
}
/*
 * Copyright 2014-2016 f2time.com All right reserved.
 */
package mobi.opendsp.commons;

import java.util.BitSet;

/**
 * @author wangwp
 *
 */
public final class BitSetUtils {

	public static BitSet convert(long value) {
		BitSet bits = new BitSet();
		int index = 0;
		while (value != 0L) {
			if (value % 2L != 0) {
				bits.set(index);
			}
			++index;
			value = value >>> 1;
		}
		return bits;
	}

	public static long convert(BitSet bits) {
		long value = 0L;
		for (int i = 0; i < bits.length(); ++i) {
			value += bits.get(i) ? (1L << i) : 0L;
		}
		return value;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BitSet bits = new BitSet();
		bits = BitSetUtils.convert(0x1F);
		System.out.println(BitSetUtils.convert(bits));
	}
}

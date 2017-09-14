/*
 * Copyright 2012 The OpenDSP Project
 *
 * The OpenDSP Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package mobi.opendsp.commons;

import java.util.BitSet;

/**
 * BitSet工具类
 * @author wangwp
 *
 */
public final class BitSets {

	public static BitSet toBitSet(long value) {
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

	public static long toLong(BitSet bits) {
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
		bits = BitSets.toBitSet(0x1F);
		System.out.println(BitSets.toLong(bits));
	}
}

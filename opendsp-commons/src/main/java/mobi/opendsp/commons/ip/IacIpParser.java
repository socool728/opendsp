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
package mobi.opendsp.commons.ip;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

import java.util.TreeMap;

/**
 * 广协IP库解析器
 * 
 * @author weiping wang (javagossip at gmail.com)
 *
 */
public final class IacIpParser {
	private static final String FIELD_SEPARATOR = ",";
	private static final String IP_FIELD_SEPARATOR = "\\.";

	private final TreeMap<Long, IacIpRecord> IP_CACHE;

	private IacIpParser(String ipFile) {
		IP_CACHE = new TreeMap<>();
		loadIacIpFile2Cache(ipFile);
	}

	private void loadIacIpFile2Cache(String ipFile) throws RuntimeException {
		BufferedReader ipFileReader = null;
		try {
			ipFileReader = new BufferedReader(new FileReader(ipFile));
			String line = null;
			IacIpRecord ipRecord = null;
			while ((line = ipFileReader.readLine()) != null) {
				ipRecord = parseIpRecord(line);
				if (ipRecord != null)
					IP_CACHE.put(ipRecord.begin, ipRecord);
			}
		} catch (IOException ex) {
			throw new RuntimeException("load iac-ip lib error", ex);
		} finally {
			try {
				if (ipFileReader != null)
					ipFileReader.close();
			} catch (Exception ex) {
				// DO NOTHING
			}
		}
	}

	private IacIpRecord parseIpRecord(String line) {
		if (line == null)
			return null;
		String[] ipRecordFields = line.split(FIELD_SEPARATOR);
		IacIpRecord ipRecord = new IacIpRecord();

		ipRecord.begin = ip2Long(ipRecordFields[0]);
		// 去掉国家编码, 保留最后6位城市编码
		ipRecord.code = ipRecordFields[2].substring(4);

		return ipRecord;
	}

	private long ip2Long(String ip) {
		final String[] ipNums = ip.split(IP_FIELD_SEPARATOR);
		if (ipNums == null || ipNums.length != 4) {
			throw new RuntimeException("Invalid ip address: " + ip == null ? StringUtils.EMPTY : ip);
		}
		
		return (Long.parseLong(ipNums[0]) << 24) + (Long.parseLong(ipNums[1]) << 16) + (Long.parseLong(ipNums[2]) << 8)
				+ (Long.parseLong(ipNums[3]));
	}

	public static IacIpParser newInstance(String ipFilePath) {
		return new IacIpParser(ipFilePath);
	}

	public String getAreaCode(String ip) {
		try {
			if (ip == null || ip.trim().length() == 0) {
				return null;
			}
			long _ip = ip2Long(ip);
			Entry<Long, IacIpRecord> entry = IP_CACHE.floorEntry(_ip);
			if (entry == null) {
				return null;
			}
			return entry.getValue().code;
		} catch (Throwable ex) {
			// DO NOTHING
		}
		return null;
	}

	private static class IacIpRecord {
		public long begin;
		public String code;
	}
}

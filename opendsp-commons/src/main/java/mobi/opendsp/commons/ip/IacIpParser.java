/*
 * Copyright 2014-2017 f2time.com All right reserved.
 */
package mobi.opendsp.commons.ip;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * 广协ip库解析工具类
 * 
 * @author wangweiping
 *
 */
public final class IacIpParser {
	private static final String FIELD_SEPARATOR = ",";

	private final TreeMap<Long, IacIpRecord> _ip_cache;

	private IacIpParser(String ipFile) {
		_ip_cache = new TreeMap<>();
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
					_ip_cache.put(ipRecord.begin, ipRecord);
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
		ipRecord.end = ip2Long(ipRecordFields[1]);
		// 去掉国家编码, 保留最后6位城市编码
		ipRecord.code = ipRecordFields[2].substring(4);

		return ipRecord;
	}

	private long ip2Long(String ip) {
		final String[] ipNums = ip.split("\\.");
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
			Entry<Long, IacIpRecord> entry = _ip_cache.floorEntry(_ip);
			if (entry == null) {
				return null;
			}
			return entry.getValue().code;
		} catch (Throwable ex) {
			// DO NOTHING
		}
		return null;
	}

	private class IacIpRecord {
		public long begin;
		public long end;
		public String code;
	}

	public static void main(String[] args) throws IOException {
		IacIpParser parser = IacIpParser.newInstance("D:/work_documents/广协ip库/iac_ip_zh.csv");
		String areaCode = parser.getAreaCode("183.202.167.82");

		System.out.println(areaCode);
	}
}

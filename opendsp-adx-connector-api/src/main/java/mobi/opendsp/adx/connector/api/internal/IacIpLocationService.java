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
package mobi.opendsp.adx.connector.api.internal;

import mobi.opendsp.adx.connector.api.IpLocationService;

/**
 * 广协IP库实现
 * 
 * @author weiping wang (javagossip at gmail.com)
 *
 */
public class IacIpLocationService implements IpLocationService {
	@Override
	public int getAreaCode(String ip) {
		return 0;
	}
}

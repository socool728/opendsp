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
package mobi.opendsp.adx.connector.api;

/**
 * IP库服务接口定义
 * 
 * @author weiping wang
 *
 */
public interface IpLocationService {
	/**
	 * 根据ip地址获得对应的地域码
	 * 
	 * @param ip
	 * @return 地域码
	 */
	int getAreaCode(String ip);
}

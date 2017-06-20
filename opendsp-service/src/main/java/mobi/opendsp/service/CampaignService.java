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
package mobi.opendsp.service;

import java.util.List;

import mobi.opendsp.model.Campaign;

/**
 * @author wangweiping
 *
 */
public interface CampaignService {
	void saveCampaign(Campaign campaign);

	void updateCampaign(Campaign campaign);

	void deleteCampaign(int campaignId);

	List<Campaign> listCampaignByStatus(int status, int pageNo, int pageSize);

	List<Campaign> listCampaigns(String campaignName, int pageNo, int pageSize);
}

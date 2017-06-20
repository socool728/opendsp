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
package mobi.opendsp.service.impl;

import java.util.List;

import mobi.opendsp.dao.CampaignMapper;
import mobi.opendsp.model.Campaign;
import mobi.opendsp.model.CampaignExample;
import mobi.opendsp.service.CampaignService;

/**
 * @author weiping wang <javagossip@gmail.com>
 *
 */
public class CampaignServiceImpl implements CampaignService {
	private CampaignMapper campaignMapper;

	@Override
	public void saveCampaign(Campaign campaign) {
		campaignMapper.insert(campaign);
	}

	@Override
	public void updateCampaign(Campaign campaign) {
		campaignMapper.updateByPrimaryKey(campaign);
	}

	@Override
	public void deleteCampaign(int campaignId) {
		campaignMapper.deleteByPrimaryKey(campaignId);
	}

	@Override
	public List<Campaign> listCampaignByStatus(int status, int pageNo, int pageSize) {
		CampaignExample example = new CampaignExample();
		example.setLimitStart((pageNo - 1) * pageSize);
		example.setLimitEnd(pageSize);

		example.createCriteria().andStatusEqualTo(status);

		return campaignMapper.selectByExample(example);
	}

	@Override
	public List<Campaign> listCampaigns(String campaignName, int pageNo, int pageSize) {
		CampaignExample example = new CampaignExample();
		example.setLimitStart((pageNo - 1) * pageSize);
		example.setLimitEnd(pageSize);

		example.createCriteria().andNameLike(campaignName);
		return campaignMapper.selectByExample(example);
	}

}

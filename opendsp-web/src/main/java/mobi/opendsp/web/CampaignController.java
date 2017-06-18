/*
 * Copyright 2014-2016 f2time.com All right reserved.
 */
package mobi.opendsp.web;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mobi.opendsp.model.Campaign;

/**
 * @author wangwp
 *
 */
@RestController
public class CampaignController {

	@RequestMapping("/campaigns")
	public List<Campaign> list(@RequestParam(value = "offset") int offset, @RequestParam("limit") int limit) {
		return null;
	}

	@RequestMapping("/campaign/{id}")
	public Campaign load(@PathVariable("id") int campaignId) {
		return null;
	}

	@RequestMapping(value = "/campaign/{id}", method = RequestMethod.PUT)
	public void update(@PathVariable("id") int campaignId, @RequestBody Campaign campaign) {
	}

	@RequestMapping(value = "/campaign/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") int campaignId) {
		
	}
}

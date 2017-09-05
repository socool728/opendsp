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
package mobi.opendsp.adx.connector.api.vast3;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import mobi.opendsp.adx.connector.api.vast3.gen.IconType;
import mobi.opendsp.adx.connector.api.vast3.gen.IconType.StaticResource;
import mobi.opendsp.adx.connector.api.vast3.gen.ImpressionType;
import mobi.opendsp.adx.connector.api.vast3.gen.VAST;
import mobi.opendsp.adx.connector.api.vast3.gen.VAST.Ad;
import mobi.opendsp.adx.connector.api.vast3.gen.VAST.Ad.InLine;
import mobi.opendsp.adx.connector.api.vast3.gen.VAST.Ad.InLine.Creatives;
import mobi.opendsp.adx.connector.api.vast3.gen.VAST.Ad.InLine.Creatives.Creative;
import mobi.opendsp.adx.connector.api.vast3.gen.VAST.Ad.InLine.Creatives.Creative.Linear;
import mobi.opendsp.adx.connector.api.vast3.gen.VAST.Ad.InLine.Creatives.Creative.Linear.Icons;
import mobi.opendsp.adx.connector.api.vast3.gen.VAST.Ad.InLine.Creatives.Creative.Linear.MediaFiles;
import mobi.opendsp.adx.connector.api.vast3.gen.VAST.Ad.InLine.Creatives.Creative.Linear.MediaFiles.MediaFile;
import mobi.opendsp.adx.connector.api.vast3.gen.VideoClicksType;
import mobi.opendsp.adx.connector.api.vast3.gen.VideoClicksType.ClickThrough;
import mobi.opendsp.adx.connector.api.vast3.gen.VideoClicksType.ClickTracking;

/**
 * @author wangwp
 *
 */
public class VASTBuilder {
	private static final String vast_version = "3.0";

	private VASTBuilder() {
	}

	private ClickThrough clickThrough;

	private List<String> clickTrackings;

	private List<ImpressionType> impressions;

	private String logo;

	private String mediaFile;

	private String creativeId;

	public VASTBuilder clickThrough(ClickThrough clickThrough) {
		this.clickThrough = clickThrough;
		return this;
	}

	public VASTBuilder addImpression(ImpressionType impression) {
		if (this.impressions == null) {
			this.impressions = new ArrayList<>();
		}
		this.impressions.add(impression);
		return this;
	}

	public VASTBuilder addClickTracking(String clickTracking) {
		if (this.clickTrackings == null) {
			this.clickTrackings = new ArrayList<>();
		}
		this.clickTrackings.add(clickTracking);
		return this;
	}

	public VASTBuilder logo(String logoUrl) {
		this.logo = logoUrl;
		return this;
	}

	public VASTBuilder mediaFile(String mediaFileUrl) {
		this.mediaFile = mediaFileUrl;
		return this;
	}

	public VASTBuilder creativeId(String creativeId) {
		this.creativeId = creativeId;
		return this;
	}

	public static VASTBuilder newBuilder() {
		return new VASTBuilder();
	}

	public VAST build() {
		VAST vast = new VAST();
		vast.setVersion(vast_version);

		Ad ad = new Ad();
		vast.getAd().add(ad);

		InLine inLine = new InLine();
		ad.setInLine(inLine);

		inLine.getImpression().addAll(impressions);

		Creatives creatives = new Creatives();
		inLine.setCreatives(creatives);

		Creative creative = new Creative();
		creative.setId(creativeId);
		creative.setAdID(StringUtils.EMPTY);
		creatives.getCreative().add(creative);

		Linear linear = new Linear();
		creative.setLinear(linear);

		linear.setVideoClicks(new VideoClicksType());
		linear.getVideoClicks().setClickThrough(clickThrough);

		for (String clickTracking : clickTrackings) {
			ClickTracking ct = new ClickTracking();
			ct.setValue(clickTracking);
			linear.getVideoClicks().getClickTracking().add(ct);
		}

		if (StringUtils.isNotBlank(logo)) {
			Icons icons = new Icons();
			linear.setIcons(icons);

			IconType icon = new IconType();
			StaticResource res = new StaticResource();
			res.setValue(logo);
			icon.setStaticResource(res);
			icons.getIcon().add(icon);
			linear.setIcons(icons);
		}

		if (StringUtils.isNotBlank(mediaFile)) {
			MediaFiles mediaFiles = new MediaFiles();
			linear.setMediaFiles(mediaFiles);
			
			MediaFile _mediaFile = new MediaFile();
			_mediaFile.setValue(mediaFile);
			linear.getMediaFiles().getMediaFile().add(_mediaFile);
		}
		return vast;
	}

	public static class ImpressionBuilder {
		public static ImpressionBuilder newBuilder() {
			return new ImpressionBuilder();
		}

		public ImpressionType build(String id, String value) {
			ImpressionType impressionType = new ImpressionType();
			impressionType.setId(id);
			impressionType.setValue(value);
			return impressionType;
		}
	}

	public static class ClickThroughBuilder {

		public static ClickThroughBuilder newBuilder() {
			return new ClickThroughBuilder();
		}

		public ClickThrough build(String type, String value) {
			ClickThrough clickThrough = new ClickThrough();
			clickThrough.setType(type);
			clickThrough.setValue(value);

			return clickThrough;
		}
	}
}

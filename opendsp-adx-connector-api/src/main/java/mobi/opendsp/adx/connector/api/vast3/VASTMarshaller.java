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

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler;

import mobi.opendsp.adx.connector.api.vast3.gen.IconType;
import mobi.opendsp.adx.connector.api.vast3.gen.IconType.StaticResource;
import mobi.opendsp.adx.connector.api.vast3.gen.ImpressionType;
import mobi.opendsp.adx.connector.api.vast3.gen.ObjectFactory;
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
@SuppressWarnings("restriction")
public final class VASTMarshaller {
	private static JAXBContext vast_ctx = null;
	private static Marshaller vast_marshaller = null;

	static {
		try {
			vast_ctx = JAXBContext.newInstance(VAST.class);
			vast_marshaller = vast_ctx.createMarshaller();
			vast_marshaller.setProperty(CharacterEscapeHandler.class.getName(), new NOOPCharacterEscapeHandler());
		} catch (JAXBException ex) {
			// DO NOTHING
		}
	}

	public static class NOOPCharacterEscapeHandler implements CharacterEscapeHandler {
		@Override
		public void escape(char[] ac, int i, int j, boolean flag, Writer writer) throws IOException {
			writer.write(ac, i, j);
		}
	}

	public static String marshall(VAST vast) {
		StringWriter writer = new StringWriter();
		try {
			vast_marshaller.marshal(vast, writer);
			return writer.toString();
		} catch (JAXBException e) {
			// DO NOTHING
		}
		return null;
	}

	public static void main(String... args) throws Exception {
		ObjectFactory vastFactory = new ObjectFactory();
		VAST vast = vastFactory.createVAST();
		vast.setVersion("3.0");

		InLine inLine = vastFactory.createVASTAdInLine();
		ImpressionType impressionType = vastFactory.createImpressionType();
		impressionType.setValue("http://www.baidu.com");
		inLine.getImpression().add(impressionType);

		Creatives inLineCreatives = vastFactory.createVASTAdInLineCreatives();
		inLine.setCreatives(inLineCreatives);

		Creative inLineCreative = vastFactory.createVASTAdInLineCreativesCreative();
		Linear inLineCreativeLiner = vastFactory.createVASTAdInLineCreativesCreativeLinear();
		inLineCreative.setLinear(inLineCreativeLiner);
		inLineCreatives.getCreative().add(inLineCreative);

		// VideoClicks
		// videoClicks=vastFactory.createVASTAdWrapperCreativesCreativeLinearVideoClicks();
		VideoClicksType videoClicks = vastFactory.createVideoClicksType();
		inLineCreativeLiner.setVideoClicks(videoClicks);
		// 添加落地页面
		ClickThrough clickThrough = vastFactory.createVideoClicksTypeClickThrough();
		clickThrough.setType("1");
		clickThrough.setValue("http://www.google.com/search");
		videoClicks.setClickThrough(clickThrough);
		// 添加点击监测
		ClickTracking clickTracking = vastFactory.createVideoClicksTypeClickTracking();
		clickTracking.setValue("http://www.iqiyi.com/click/tracking");
		videoClicks.getClickTracking().add(clickTracking);

		// 添加广告平台logo
		inLineCreativeLiner.setIcons(vastFactory.createVASTAdInLineCreativesCreativeLinearIcons());
		Icons icons = inLineCreativeLiner.getIcons();
		StaticResource iconResource = vastFactory.createIconTypeStaticResource();
		iconResource.setValue("http://cdn.f2time.com/static/image/feifan.png");
		IconType iconType = vastFactory.createIconType();
		iconType.setStaticResource(iconResource);
		icons.getIcon().add(iconType);

		// 添加媒体文件
		MediaFiles mediaFiles = vastFactory.createVASTAdInLineCreativesCreativeLinearMediaFiles();
		inLineCreativeLiner.setMediaFiles(mediaFiles);

		MediaFile mediaFile = vastFactory.createVASTAdInLineCreativesCreativeLinearMediaFilesMediaFile();
		mediaFile.setValue("http://cdn.f2time.com/videos/xxxxx.mp4");

		mediaFiles.getMediaFile().add(mediaFile);
		Ad ad = vastFactory.createVASTAd();
		ad.setId("123");
		ad.setInLine(inLine);

		vast.getAd().add(ad);

		String str = VASTMarshaller.marshall(vast);
		System.out.println(str);
	}
}

package com.viewhigh.service.impl;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.viewhigh.Constants;
import com.viewhigh.dao.IMovieInfoDAO;
import com.viewhigh.entity.MovieInfo;
import com.viewhigh.service.MovieMgrService;
import com.viewhigh.vadp.framework.base.service.BaseServiceImpl;

@Service
public class MovieMgrServiceImpl extends BaseServiceImpl implements MovieMgrService {

	public long lastInvokeTime = 0L;
	
	@Autowired
	private IMovieInfoDAO movieInfoDao;
	
	@Override
	public JSONObject saveDoubanJson(String doubanId) {
		MovieInfo mi = movieInfoDao.getMovieInfo(doubanId);
		if(mi != null && StringUtils.isNotBlank(mi.getResponseJson())){
			String response = mi.getResponseJson();
			System.out.println("-------------获取本地豆瓣数据-------------");
			return this.response2json(response);
		}
		
		long currentTime = new Date().getTime();
		if(currentTime - lastInvokeTime < Constants.INVOKE_DOUBAN_INTERVAL){
			return null;
		}
		
		HttpResponse response = this.getDoubanJson(doubanId);
	    lastInvokeTime = currentTime;
	    int code = response.getStatusLine().getStatusCode();  
	    if (code == 200) {
	        String resultJson = null;
			try {
				resultJson = EntityUtils.toString(response.getEntity(), "utf-8");
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		    movieInfoDao.addResponseJson(doubanId, resultJson);
		    return this.response2json(resultJson);
	    }
	    return null;
	}
	
	private HttpResponse getDoubanJson(String doubanId){
		HttpClient client = new DefaultHttpClient();
	    HttpGet httpGet = null;
	    HttpResponse response = null;
	    String resultJson = null;
	    try {  
	        httpGet = new HttpGet(Constants.DOUBAN_API_PREFIX + doubanId);  
	        httpGet.setHeader("Host", Constants.DOUBAN_API_HOST);  
	        httpGet.setHeader(
	                "User-Agent",  
	                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36");  
	        response = client.execute(httpGet);
	        System.out.println("-------------调用豆瓣接口-------------");
	        return response;
	    } catch (ClientProtocolException e) {  
	        e.printStackTrace();  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }
	    return null;
	}
	/**
	 * 格式化JSON
	 * @param response
	 * @return
	 */
	private JSONObject response2json(String response){
		JSONObject obj = JSONObject.parseObject(response);
		//评分
		String rating = obj.getJSONObject("rating").getString("average");
		obj.put("rating", rating);
		// 年份
		String year = obj.getString("year");
		// 小图片
		String smallImage = obj.getJSONObject("images").getString("small");
		obj.put("smallImage", smallImage);
		// 中图片
		String mediumImage = obj.getJSONObject("images").getString("medium");
		obj.put("mediumImage", mediumImage);
		// 大图片
		String largeImage = obj.getJSONObject("images").getString("large");
		obj.put("largeImage", largeImage);
		// 豆瓣地址
		String alt = obj.getString("alt");
		// 电影名称
		String title = obj.getString("title");
		// 国家
		StringBuffer countriesSb = new StringBuffer();
		JSONArray countries = obj.getJSONArray("countries");
		for (int i = 0; i < countries.size(); i++) {
			String country = (String) countries.get(i);
			countriesSb.append(country + "/");
		}
		obj.put("countries", StringUtils.removeEnd(countriesSb.toString(), "/"));
		// 类型（剧情、音乐）
		StringBuffer genresSb = new StringBuffer();
		JSONArray genres = obj.getJSONArray("genres");
		for (int i = 0; i < genres.size(); i++) {
			String genre = (String) genres.get(i);
			genresSb.append(genre + "/");
		}
		obj.put("genres",StringUtils.removeEnd(genresSb.toString(), "/"));
		// 演员
		StringBuffer castsSb = new StringBuffer();
		JSONArray casts = obj.getJSONArray("casts");
		for (int i = 0; i < casts.size(); i++) {
			JSONObject cast = casts.getJSONObject(i);
			String id = cast.getString("id");
			String name = cast.getString("name");
			castsSb.append(name + "/");
			String castAlt = cast.getString("alt");
			JSONObject avatars = cast.getJSONObject("avatars");
			String avatarSmallImage = avatars.getString("small");
			String avatarMediumImage = avatars.getString("medium");
			String avatarLargeImage = avatars.getString("large");
		}
		obj.put("casts",StringUtils.removeEnd(castsSb.toString(), "/"));
		// 第几季
		String current_season = obj.getString("current_season");
		// 原标题
		String original_title = obj.getString("original_title");
		// 介绍
		String summary = obj.getString("summary");
		// 类型
		String subtype = obj.getString("subtype");
		// 导演
		StringBuffer directorsSb = new StringBuffer();
		JSONArray directors = obj.getJSONArray("directors");
		for (int i = 0; i < directors.size(); i++) {
			JSONObject director = directors.getJSONObject(i);
			String id = director.getString("id");
			String name = director.getString("name");
			directorsSb.append(name + "/");
			String directorAlt = director.getString("alt");
		}
		obj.put("directors",StringUtils.removeEnd(directorsSb.toString(), "/"));
		// 别名
		StringBuffer akaSb = new StringBuffer();
		JSONArray aka = obj.getJSONArray("aka");
		for (int i = 0; i < aka.size(); i++) {
			String alias = (String) aka.get(i);
			akaSb.append(alias + "/");
		}
		obj.put("aka",StringUtils.removeEnd(akaSb.toString(), "/"));
		return obj;
	}
		

//	{
//		"rating": {
//			"max": 10,
//			"average": 8.0,
//			"stars": "40",
//			"min": 0
//		},
//		"reviews_count": 1585,
//		"wish_count": 28629,
//		"douban_site": "",
//		"year": "2017",
//		"images": {
//			"small": "http://img7.doubanio.com\/view\/photo\/s_ratio_poster\/public\/p2508925590.webp",
//			"large": "http://img7.doubanio.com\/view\/photo\/s_ratio_poster\/public\/p2508925590.webp",
//			"medium": "http://img7.doubanio.com\/view\/photo\/s_ratio_poster\/public\/p2508925590.webp"
//		},
//		"alt": "https:\/\/movie.douban.com\/subject\/26942674\/",
//		"id": "26942674",
//		"mobile_url": "https:\/\/movie.douban.com\/subject\/26942674\/mobile",
//		"title": "神秘巨星",
//		"do_count": null,
//		"share_url": "http:\/\/m.douban.com\/movie\/subject\/26942674",
//		"seasons_count": null,
//		"schedule_url": "https:\/\/movie.douban.com\/subject\/26942674\/cinema\/",
//		"episodes_count": null,
//		"countries": ["印度"],
//		"genres": ["剧情", "音乐"],
//		"collect_count": 68775,
//		"casts": [{
//			"alt": "https:\/\/movie.douban.com\/celebrity\/1373292\/",
//			"avatars": {
//				"small": "http://img7.doubanio.com\/view\/celebrity\/s_ratio_celebrity\/public\/p1494080264.12.webp",
//				"large": "http://img7.doubanio.com\/view\/celebrity\/s_ratio_celebrity\/public\/p1494080264.12.webp",
//				"medium": "http://img7.doubanio.com\/view\/celebrity\/s_ratio_celebrity\/public\/p1494080264.12.webp"
//			},
//			"name": "塞伊拉·沃西",
//			"id": "1373292"
//		}, {
//			"alt": "https:\/\/movie.douban.com\/celebrity\/1383897\/",
//			"avatars": {
//				"small": "http://img3.doubanio.com\/view\/celebrity\/s_ratio_celebrity\/public\/p1510229457.27.webp",
//				"large": "http://img3.doubanio.com\/view\/celebrity\/s_ratio_celebrity\/public\/p1510229457.27.webp",
//				"medium": "http://img3.doubanio.com\/view\/celebrity\/s_ratio_celebrity\/public\/p1510229457.27.webp"
//			},
//			"name": "梅·维贾",
//			"id": "1383897"
//		}, {
//			"alt": "https:\/\/movie.douban.com\/celebrity\/1031931\/",
//			"avatars": {
//				"small": "http://img3.doubanio.com\/view\/celebrity\/s_ratio_celebrity\/public\/p13628.webp",
//				"large": "http://img3.doubanio.com\/view\/celebrity\/s_ratio_celebrity\/public\/p13628.webp",
//				"medium": "http://img3.doubanio.com\/view\/celebrity\/s_ratio_celebrity\/public\/p13628.webp"
//			},
//			"name": "阿米尔·汗",
//			"id": "1031931"
//		}, {
//			"alt": "https:\/\/movie.douban.com\/celebrity\/1383898\/",
//			"avatars": {
//				"small": "http://img3.doubanio.com\/view\/celebrity\/s_ratio_celebrity\/public\/p1510229759.29.webp",
//				"large": "http://img3.doubanio.com\/view\/celebrity\/s_ratio_celebrity\/public\/p1510229759.29.webp",
//				"medium": "http://img3.doubanio.com\/view\/celebrity\/s_ratio_celebrity\/public\/p1510229759.29.webp"
//			},
//			"name": "拉杰·阿晶",
//			"id": "1383898"
//		}],
//		"current_season": null,
//		"original_title": "Secret Superstar",
//		"summary": "14岁的印度少女尹希娅（塞伊拉·沃西 饰）热爱唱歌，因父亲阻挠，她只能蒙面拍摄并上传自弹自唱原创歌曲的视频，孰料凭借天籁歌喉在网上一炮而红，备受争议的音乐人夏克提·库马尔（阿米尔·汗 饰）也向她抛出橄榄枝，尹希娅的生活发生了翻天覆地的变化……",
//		"subtype": "movie",
//		"directors": [{
//			"alt": "https:\/\/movie.douban.com\/celebrity\/1379532\/",
//			"avatars": {
//				"small": "http://img3.doubanio.com\/view\/celebrity\/s_ratio_celebrity\/public\/p1509423054.09.webp",
//				"large": "http://img3.doubanio.com\/view\/celebrity\/s_ratio_celebrity\/public\/p1509423054.09.webp",
//				"medium": "http://img3.doubanio.com\/view\/celebrity\/s_ratio_celebrity\/public\/p1509423054.09.webp"
//			},
//			"name": "阿德瓦·香登",
//			"id": "1379532"
//		}],
//		"comments_count": 32542,
//		"ratings_count": 66206,
//		"aka": ["秘密巨星", "隐藏的大明星(台)", "सीक्रेट सुपरस्टार"]
//	}


}

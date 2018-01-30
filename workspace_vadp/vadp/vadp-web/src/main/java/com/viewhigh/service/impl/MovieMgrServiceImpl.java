package com.viewhigh.service.impl;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.viewhigh.Constants;
import com.viewhigh.dao.BdDeptDao;
import com.viewhigh.dao.IMovieInfoDAO;
import com.viewhigh.entity.MovieInfo;
import com.viewhigh.service.MovieMgrService;
import com.viewhigh.util.Strings;
import com.viewhigh.vadp.framework.base.service.BaseServiceImpl;

@Service
public class MovieMgrServiceImpl extends BaseServiceImpl implements MovieMgrService {

	public long lastInvokeTime = 0L;
	
	@Autowired
	private IMovieInfoDAO movieInfoDao;
	
	@Override
	public JSONObject getDoubanMovieInfo(String doubanId) {
		MovieInfo mi = movieInfoDao.getMovieInfo(doubanId);
		if(mi != null && StringUtils.isNotBlank(mi.getResponseJson())){
			String response = mi.getResponseJson();
			return this.response2json(response);
		}
		
		long currentTime = new Date().getTime();
		if(currentTime - lastInvokeTime < Constants.INVOKE_DOUBAN_INTERVAL){
			return null;
		}
		
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
	        int code = response.getStatusLine().getStatusCode();  
	        if (code == 200) {
	            resultJson = EntityUtils.toString(response.getEntity(), "utf-8");
	    	    movieInfoDao.addResponseJson(doubanId, resultJson);
	    	    return this.response2json(resultJson);
//	            //评分
//	            String rating = obj.getJSONObject("rating").getString("average");
//	            System.out.println("rating"+rating);
//	            //年份
//	            String year = obj.getString("year");
//	            System.out.println("year"+year);
//	            //小图片
//	            String smallImage = obj.getJSONObject("images").getString("small");
//	            System.out.println("smallImage"+smallImage);
//	            //中图片
//	            String mediumImage = obj.getJSONObject("images").getString("medium");
//	            System.out.println("mediumImage"+mediumImage);
//	            //大图片
//	            String largeImage = obj.getJSONObject("images").getString("large");
//	            System.out.println("largeImage"+largeImage);
//	            //豆瓣地址
//	            String alt = obj.getString("alt");
//	            System.out.println("alt="+alt);
//	            //电影名称
//	            String title = obj.getString("title");
//	            System.out.println("title="+title);
//	            //国家
//	            JSONArray countries = obj.getJSONArray("countries");
//	            for(int i = 0;i < countries.size();i++){
//	            	String country = (String) countries.get(i);
//	            	System.out.println("country="+country);
//	            }
//	            //类型（剧情、音乐）
//	            JSONArray genres = obj.getJSONArray("genres");
//	            for(int i = 0;i < genres.size();i++){
//	            	String cast = (String) genres.get(i);
//	            	System.out.println("cast="+cast);
//	            }
//	            //演员
//	            JSONArray casts = obj.getJSONArray("casts");
//	            for(int i = 0;i < casts.size();i++){
//	            	JSONObject cast = casts.getJSONObject(i);
//	            	String id = cast.getString("id");
//	            	System.out.println("id="+id);
//	            	String name = cast.getString("name");
//	            	System.out.println("name="+name);
//	            	String castAlt = cast.getString("alt");
//	            	System.out.println("castAlt="+castAlt);
//	            	JSONObject avatars = cast.getJSONObject("avatars");
//	            	String avatarSmallImage = avatars.getString("small");
//	            	String avatarMediumImage = avatars.getString("medium");
//	            	String avatarLargeImage = avatars.getString("large");
//	            }
//	            //第几季
//	            String current_season = obj.getString("current_season");
//	            System.out.println("current_season="+current_season);
//	            //原标题
//	            String original_title = obj.getString("original_title");
//	            System.out.println("original_title="+original_title);
//	            //介绍
//	            String summary = obj.getString("summary");
//	            System.out.println("summary="+summary);
//	            //类型
//	            String subtype = obj.getString("subtype");
//	            System.out.println("subtype="+subtype);
//	            //导演
//	            JSONArray directors = obj.getJSONArray("directors");
//	            for(int i = 0;i < directors.size();i++){
//	            	JSONObject director = directors.getJSONObject(i);
//	            	String id = director.getString("id");
//	            	System.out.println("id="+id);
//	            	String name = director.getString("name");
//	            	System.out.println("name="+name);
//	            	String directorAlt = director.getString("alt");
//	            	System.out.println("directorAlt="+directorAlt);
//	            }
//	            //别名
//	            JSONArray aka = obj.getJSONArray("aka");
//	            for(int i = 0;i < aka.size();i++){
//	            	String alias = (String) aka.get(i);
//	            	System.out.println("alias="+alias);
//	            }
	            
	        }
	    } catch (ClientProtocolException e) {  
	        e.printStackTrace();  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }
	    return null;
		
//		MovieInfo mi = new MovieInfo();
//		//评分
//		String rating = obj.getJSONObject("rating").getString("average");
//		mi.setRating(rating);
//		// 年份
//		String year = obj.getString("year");
//		mi.setYear(year);
//		// 小图片
//		String smallImage = obj.getJSONObject("images").getString("small");
//		mi.setSmallImage(smallImage);
//		// 中图片
//		String mediumImage = obj.getJSONObject("images").getString("medium");
//		mi.setMediumImage(mediumImage);
//		// 大图片
//		String largeImage = obj.getJSONObject("images").getString("large");
//		mi.setLargeImage(largeImage);
//		// 豆瓣地址
//		String alt = obj.getString("alt");
//		mi.setAlt(alt);
//		// 电影名称
//		String title = obj.getString("title");
//		mi.setTitle(title);
//		// 国家
//		StringBuffer countriesSb = new StringBuffer();
//		JSONArray countries = obj.getJSONArray("countries");
//		for (int i = 0; i < countries.size(); i++) {
//			String country = (String) countries.get(i);
//			countriesSb.append(country);
//		}
//		mi.setCountries(countriesSb.toString());
//		// 类型（剧情、音乐）
//		StringBuffer genresSb = new StringBuffer();
//		JSONArray genres = obj.getJSONArray("genres");
//		for (int i = 0; i < genres.size(); i++) {
//			String genre = (String) genres.get(i);
//			genresSb.append(genre);
//		}
//		mi.setGenres(genresSb.toString());
//		// 演员
//		StringBuffer castsSb = new StringBuffer();
//		JSONArray casts = obj.getJSONArray("casts");
//		for (int i = 0; i < casts.size(); i++) {
//			JSONObject cast = casts.getJSONObject(i);
//			String id = cast.getString("id");
//			System.out.println("id=" + id);
//			String name = cast.getString("name");
//			castsSb.append(name);
//			String castAlt = cast.getString("alt");
//			System.out.println("castAlt=" + castAlt);
//			JSONObject avatars = cast.getJSONObject("avatars");
//			String avatarSmallImage = avatars.getString("small");
//			String avatarMediumImage = avatars.getString("medium");
//			String avatarLargeImage = avatars.getString("large");
//		}
//		mi.setCasts(castsSb.toString());
//		// 第几季
//		String current_season = obj.getString("current_season");
//		mi.setCurrentSeason(current_season);
//		// 原标题
//		String original_title = obj.getString("original_title");
//		mi.setOriginalTitle(original_title);
//		// 介绍
//		String summary = obj.getString("summary");
//		System.out.println("summary=" + summary);
//		// 类型
//		String subtype = obj.getString("subtype");
//		mi.setSubtype(subtype);
//		// 导演
//		StringBuffer directorsSb = new StringBuffer();
//		JSONArray directors = obj.getJSONArray("directors");
//		for (int i = 0; i < directors.size(); i++) {
//			JSONObject director = directors.getJSONObject(i);
//			String id = director.getString("id");
//			System.out.println("id=" + id);
//			String name = director.getString("name");
//			directorsSb.append(name);
//			String directorAlt = director.getString("alt");
//			System.out.println("directorAlt=" + directorAlt);
//		}
//		mi.setDirectors(directorsSb.toString());
//		// 别名
//		StringBuffer akaSb = new StringBuffer();
//		JSONArray aka = obj.getJSONArray("aka");
//		for (int i = 0; i < aka.size(); i++) {
//			String alias = (String) aka.get(i);
//			akaSb.append(alias);
//		}
//		mi.setAkas(akaSb.toString());
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
		System.out.println("year" + year);
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
		System.out.println("alt=" + alt);
		// 电影名称
		String title = obj.getString("title");
		System.out.println("title=" + title);
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
			System.out.println("genre="+genre);
			genresSb.append(genre + "/");
		}
		obj.put("genres",StringUtils.removeEnd(genresSb.toString(), "/"));
		// 演员
		StringBuffer castsSb = new StringBuffer();
		JSONArray casts = obj.getJSONArray("casts");
		for (int i = 0; i < casts.size(); i++) {
			JSONObject cast = casts.getJSONObject(i);
			String id = cast.getString("id");
			System.out.println("id=" + id);
			String name = cast.getString("name");
			System.out.println("name=" + name);
			castsSb.append(name + "/");
			String castAlt = cast.getString("alt");
			System.out.println("castAlt=" + castAlt);
			JSONObject avatars = cast.getJSONObject("avatars");
			String avatarSmallImage = avatars.getString("small");
			String avatarMediumImage = avatars.getString("medium");
			String avatarLargeImage = avatars.getString("large");
		}
		obj.put("casts",StringUtils.removeEnd(castsSb.toString(), "/"));
		// 第几季
		String current_season = obj.getString("current_season");
		System.out.println("current_season=" + current_season);
		// 原标题
		String original_title = obj.getString("original_title");
		System.out.println("original_title=" + original_title);
		// 介绍
		String summary = obj.getString("summary");
		System.out.println("summary=" + summary);
		// 类型
		String subtype = obj.getString("subtype");
		System.out.println("subtype=" + subtype);
		// 导演
		StringBuffer directorsSb = new StringBuffer();
		JSONArray directors = obj.getJSONArray("directors");
		for (int i = 0; i < directors.size(); i++) {
			JSONObject director = directors.getJSONObject(i);
			String id = director.getString("id");
			System.out.println("id=" + id);
			String name = director.getString("name");
			System.out.println("name=" + name);
			directorsSb.append(name + "/");
			String directorAlt = director.getString("alt");
			System.out.println("directorAlt=" + directorAlt);
		}
		obj.put("directors",StringUtils.removeEnd(directorsSb.toString(), "/"));
		// 别名
		StringBuffer akaSb = new StringBuffer();
		JSONArray aka = obj.getJSONArray("aka");
		for (int i = 0; i < aka.size(); i++) {
			String alias = (String) aka.get(i);
			System.out.println("alias=" + alias);
			akaSb.append(alias + "/");
		}
		obj.put("aka",StringUtils.removeEnd(akaSb.toString(), "/"));
		return obj;
	}
	public static void main(String[] args){
		HttpClient client = new DefaultHttpClient();  
	    HttpGet httpGet = null;  
	    HttpResponse response = null;  
	    String resultJson = null;  
	    try {  
	        httpGet = new HttpGet("http://api.douban.com/v2/movie/subject/26942674");  
	        httpGet.setHeader("Host", "api.douban.com");  
	        httpGet.setHeader(  
	                "User-Agent",  
	                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36");  
	        response = client.execute(httpGet);  
	        int code = response.getStatusLine().getStatusCode();  
	        if (code == 200) {
	            resultJson = EntityUtils  
	                    .toString(response.getEntity(), "utf-8");
	    	    JSONObject obj = JSONObject.parseObject(resultJson);
	            //评分
	            String rating = obj.getJSONObject("rating").getString("average");
	            System.out.println("rating"+rating);
	            //年份
	            String year = obj.getString("year");
	            System.out.println("year"+year);
	            //小图片
	            String smallImage = obj.getJSONObject("images").getString("small");
	            System.out.println("smallImage"+smallImage);
	            //中图片
	            String mediumImage = obj.getJSONObject("images").getString("medium");
	            System.out.println("mediumImage"+mediumImage);
	            //大图片
	            String largeImage = obj.getJSONObject("images").getString("large");
	            System.out.println("largeImage"+largeImage);
	            //豆瓣地址
	            String alt = obj.getString("alt");
	            System.out.println("alt="+alt);
	            //电影名称
	            String title = obj.getString("title");
	            System.out.println("title="+title);
	            //国家
	            JSONArray countries = obj.getJSONArray("countries");
	            for(int i = 0;i < countries.size();i++){
	            	String country = (String) countries.get(i);
	            	System.out.println("country="+country);
	            }
	            //类型（剧情、音乐）
	            JSONArray genres = obj.getJSONArray("genres");
	            for(int i = 0;i < genres.size();i++){
	            	String cast = (String) genres.get(i);
	            	System.out.println("cast="+cast);
	            }
	            //演员
	            JSONArray casts = obj.getJSONArray("casts");
	            for(int i = 0;i < casts.size();i++){
	            	JSONObject cast = casts.getJSONObject(i);
	            	String id = cast.getString("id");
	            	System.out.println("id="+id);
	            	String name = cast.getString("name");
	            	System.out.println("name="+name);
	            	String castAlt = cast.getString("alt");
	            	System.out.println("castAlt="+castAlt);
	            	JSONObject avatars = cast.getJSONObject("avatars");
	            	String avatarSmallImage = avatars.getString("small");
	            	String avatarMediumImage = avatars.getString("medium");
	            	String avatarLargeImage = avatars.getString("large");
	            }
	            //第几季
	            String current_season = obj.getString("current_season");
	            System.out.println("current_season="+current_season);
	            //原标题
	            String original_title = obj.getString("original_title");
	            System.out.println("original_title="+original_title);
	            //介绍
	            String summary = obj.getString("summary");
	            System.out.println("summary="+summary);
	            //类型
	            String subtype = obj.getString("subtype");
	            System.out.println("subtype="+subtype);
	            //导演
	            JSONArray directors = obj.getJSONArray("directors");
	            for(int i = 0;i < directors.size();i++){
	            	JSONObject director = directors.getJSONObject(i);
	            	String id = director.getString("id");
	            	System.out.println("id="+id);
	            	String name = director.getString("name");
	            	System.out.println("name="+name);
	            	String directorAlt = director.getString("alt");
	            	System.out.println("directorAlt="+directorAlt);
	            }
	            //别名
	            JSONArray aka = obj.getJSONArray("aka");
	            for(int i = 0;i < aka.size();i++){
	            	String alias = (String) aka.get(i);
	            	System.out.println("alias="+alias);
	            }
	            
	        }  
	    } catch (ClientProtocolException e) {  
	        e.printStackTrace();  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }  
		
//	    String resultJson = "{\"rating\": {\"max\": 10, \"average\": 8.0, \"stars\": \"40\", \"min\": 0}, \"reviews_count\": 1585, \"wish_count\": 28629, \"douban_site\": \"\", \"year\": \"2017\", \"images\": {\"small\": \"http://img7.doubanio.com\\/view\\/photo\\/s_ratio_poster\\/public\\/p2508925590.webp\", \"large\": \"http://img7.doubanio.com\\/view\\/photo\\/s_ratio_poster\\/public\\/p2508925590.webp\", \"medium\": \"http://img7.doubanio.com\\/view\\/photo\\/s_ratio_poster\\/public\\/p2508925590.webp\"}, \"alt\": \"https:\\/\\/movie.douban.com\\/subject\\/26942674\\/\", \"id\": \"26942674\", \"mobile_url\": \"https:\\/\\/movie.douban.com\\/subject\\/26942674\\/mobile\", \"title\": \"\\u795e\\u79d8\\u5de8\\u661f\", \"do_count\": null, \"share_url\": \"http:\\/\\/m.douban.com\\/movie\\/subject\\/26942674\", \"seasons_count\": null, \"schedule_url\": \"https:\\/\\/movie.douban.com\\/subject\\/26942674\\/cinema\\/\", \"episodes_count\": null, \"countries\": [\"\\u5370\\u5ea6\"], \"genres\": [\"\\u5267\\u60c5\", \"\\u97f3\\u4e50\"], \"collect_count\": 68775, \"casts\": [{\"alt\": \"https:\\/\\/movie.douban.com\\/celebrity\\/1373292\\/\", \"avatars\": {\"small\": \"http://img7.doubanio.com\\/view\\/celebrity\\/s_ratio_celebrity\\/public\\/p1494080264.12.webp\", \"large\": \"http://img7.doubanio.com\\/view\\/celebrity\\/s_ratio_celebrity\\/public\\/p1494080264.12.webp\", \"medium\": \"http://img7.doubanio.com\\/view\\/celebrity\\/s_ratio_celebrity\\/public\\/p1494080264.12.webp\"}, \"name\": \"\\u585e\\u4f0a\\u62c9\\u00b7\\u6c83\\u897f\", \"id\": \"1373292\"}, {\"alt\": \"https:\\/\\/movie.douban.com\\/celebrity\\/1383897\\/\", \"avatars\": {\"small\": \"http://img3.doubanio.com\\/view\\/celebrity\\/s_ratio_celebrity\\/public\\/p1510229457.27.webp\", \"large\": \"http://img3.doubanio.com\\/view\\/celebrity\\/s_ratio_celebrity\\/public\\/p1510229457.27.webp\", \"medium\": \"http://img3.doubanio.com\\/view\\/celebrity\\/s_ratio_celebrity\\/public\\/p1510229457.27.webp\"}, \"name\": \"\\u6885\\u00b7\\u7ef4\\u8d3e\", \"id\": \"1383897\"}, {\"alt\": \"https:\\/\\/movie.douban.com\\/celebrity\\/1031931\\/\", \"avatars\": {\"small\": \"http://img3.doubanio.com\\/view\\/celebrity\\/s_ratio_celebrity\\/public\\/p13628.webp\", \"large\": \"http://img3.doubanio.com\\/view\\/celebrity\\/s_ratio_celebrity\\/public\\/p13628.webp\", \"medium\": \"http://img3.doubanio.com\\/view\\/celebrity\\/s_ratio_celebrity\\/public\\/p13628.webp\"}, \"name\": \"\\u963f\\u7c73\\u5c14\\u00b7\\u6c57\", \"id\": \"1031931\"}, {\"alt\": \"https:\\/\\/movie.douban.com\\/celebrity\\/1383898\\/\", \"avatars\": {\"small\": \"http://img3.doubanio.com\\/view\\/celebrity\\/s_ratio_celebrity\\/public\\/p1510229759.29.webp\", \"large\": \"http://img3.doubanio.com\\/view\\/celebrity\\/s_ratio_celebrity\\/public\\/p1510229759.29.webp\", \"medium\": \"http://img3.doubanio.com\\/view\\/celebrity\\/s_ratio_celebrity\\/public\\/p1510229759.29.webp\"}, \"name\": \"\\u62c9\\u6770\\u00b7\\u963f\\u6676\", \"id\": \"1383898\"}], \"current_season\": null, \"original_title\": \"Secret Superstar\", \"summary\": \"14\\u5c81\\u7684\\u5370\\u5ea6\\u5c11\\u5973\\u5c39\\u5e0c\\u5a05\\uff08\\u585e\\u4f0a\\u62c9\\u00b7\\u6c83\\u897f \\u9970\\uff09\\u70ed\\u7231\\u5531\\u6b4c\\uff0c\\u56e0\\u7236\\u4eb2\\u963b\\u6320\\uff0c\\u5979\\u53ea\\u80fd\\u8499\\u9762\\u62cd\\u6444\\u5e76\\u4e0a\\u4f20\\u81ea\\u5f39\\u81ea\\u5531\\u539f\\u521b\\u6b4c\\u66f2\\u7684\\u89c6\\u9891\\uff0c\\u5b70\\u6599\\u51ed\\u501f\\u5929\\u7c41\\u6b4c\\u5589\\u5728\\u7f51\\u4e0a\\u4e00\\u70ae\\u800c\\u7ea2\\uff0c\\u5907\\u53d7\\u4e89\\u8bae\\u7684\\u97f3\\u4e50\\u4eba\\u590f\\u514b\\u63d0\\u00b7\\u5e93\\u9a6c\\u5c14\\uff08\\u963f\\u7c73\\u5c14\\u00b7\\u6c57 \\u9970\\uff09\\u4e5f\\u5411\\u5979\\u629b\\u51fa\\u6a44\\u6984\\u679d\\uff0c\\u5c39\\u5e0c\\u5a05\\u7684\\u751f\\u6d3b\\u53d1\\u751f\\u4e86\\u7ffb\\u5929\\u8986\\u5730\\u7684\\u53d8\\u5316\\u2026\\u2026\", \"subtype\": \"movie\", \"directors\": [{\"alt\": \"https:\\/\\/movie.douban.com\\/celebrity\\/1379532\\/\", \"avatars\": {\"small\": \"http://img3.doubanio.com\\/view\\/celebrity\\/s_ratio_celebrity\\/public\\/p1509423054.09.webp\", \"large\": \"http://img3.doubanio.com\\/view\\/celebrity\\/s_ratio_celebrity\\/public\\/p1509423054.09.webp\", \"medium\": \"http://img3.doubanio.com\\/view\\/celebrity\\/s_ratio_celebrity\\/public\\/p1509423054.09.webp\"}, \"name\": \"\\u963f\\u5fb7\\u74e6\\u00b7\\u9999\\u767b\", \"id\": \"1379532\"}], \"comments_count\": 32542, \"ratings_count\": 66206, \"aka\": [\"\\u79d8\\u5bc6\\u5de8\\u661f\", \"\\u9690\\u85cf\\u7684\\u5927\\u660e\\u661f(\\u53f0)\", \"\\u0938\\u0940\\u0915\\u094d\\u0930\\u0947\\u091f \\u0938\\u0941\\u092a\\u0930\\u0938\\u094d\\u091f\\u093e\\u0930\"]}";
//	    JSONObject obj = JSONObject.parseObject(resultJson);
//        //评分
//        String rating = obj.getJSONObject("rating").getString("average");
//        System.out.println("rating"+rating);
//        //年份
//        String year = obj.getString("year");
//        System.out.println("year"+year);
//        //小图片
//        String smallImage = obj.getJSONObject("images").getString("small");
//        System.out.println("smallImage"+smallImage);
//        //中图片
//        String mediumImage = obj.getJSONObject("images").getString("medium");
//        System.out.println("mediumImage"+mediumImage);
//        //大图片
//        String largeImage = obj.getJSONObject("images").getString("large");
//        System.out.println("largeImage"+largeImage);
//        //豆瓣地址
//        String alt = obj.getString("alt");
//        System.out.println("alt="+alt);
//        //电影名称
//        String title = obj.getString("title");
//        System.out.println("title="+title);
//        //国家
//        JSONArray countries = obj.getJSONArray("countries");
//        for(int i = 0;i < countries.size();i++){
//        	String country = (String) countries.get(i);
//        	System.out.println("country="+country);
//        }
//        //类型（剧情、音乐）
//        JSONArray genres = obj.getJSONArray("genres");
//        for(int i = 0;i < genres.size();i++){
//        	String cast = (String) genres.get(i);
//        	System.out.println("cast="+cast);
//        }
//        //演员
//        JSONArray casts = obj.getJSONArray("casts");
//        for(int i = 0;i < casts.size();i++){
//        	JSONObject cast = casts.getJSONObject(i);
//        	String name = cast.getString("name");
//        	System.out.println("name="+name);
//        }
//        //第几季
//        String current_season = obj.getString("current_season");
//        System.out.println("current_season="+current_season);
//        //原标题
//        String original_title = obj.getString("original_title");
//        System.out.println("original_title="+original_title);
//        //介绍
//        String summary = obj.getString("summary");
//        System.out.println("summary="+summary);
//        //导演
//        JSONArray directors = obj.getJSONArray("directors");
//        for(int i = 0;i < directors.size();i++){
//        	JSONObject director = directors.getJSONObject(i);
//        	String name = director.getString("name");
//        	System.out.println("name="+name);
//        }
//        //别名
//        JSONArray aka = obj.getJSONArray("aka");
//        for(int i = 0;i < aka.size();i++){
//        	String alias = (String) aka.get(i);
//        	System.out.println("alias="+alias);
//        }
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
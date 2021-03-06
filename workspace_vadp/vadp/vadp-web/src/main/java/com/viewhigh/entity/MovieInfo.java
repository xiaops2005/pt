package com.viewhigh.entity;


import com.viewhigh.excel.domain.Base;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import java.math.BigDecimal;
import java.util.Date;

@XmlRootElement
@Entity
@Table(name = "movie_info")
@DynamicInsert(true)
@DynamicUpdate(true)
public class MovieInfo {

	@Id
	@Column(name = "id")
	private String id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "rating")
	private Float rating;
	
	@Column(name = "year")
	private String year;
	
	@Column(name = "small_image")
	private String smallImage;
	
	@Column(name = "medium_image")
	private String mediumImage;
	
	@Column(name = "large_image")
	private String largeImage;
	
	@Column(name = "alt")
	private String alt;
	
	@Column(name = "countries")
	private String countries;
	
	@Column(name = "genres")
	private String genres;
	
	@Column(name = "current_season")
	private String currentSeason;
	
	@Column(name = "original_title")
	private String originalTitle;
	
	@Column(name = "summary")
	private String summary;
	
	@Column(name = "subtype")
	private String subtype;
	
	@Column(name = "akas")
	private String akas;
	
	@Column(name = "create_time")
	private Date createTime;
	
	@Column(name = "update_time")
	private Date updateTime;
	
	@Column(name = "casts")
	private String casts;
	
	@Column(name = "directors")
	private String directors;
	
	@Column(name = "response_json")
	private String responseJson;
	
	@Column(name = "ratings_count")
	private Integer ratingsCount;
	
	@Column(name = "torrent_flag")
	private String torrentFlag;
	
	@Column(name = "subtitle_flag")
	private String subtitleFlag;
	
	@Column(name = "publish_flag")
	private String publishFlag;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getSmallImage() {
		return smallImage;
	}

	public void setSmallImage(String smallImage) {
		this.smallImage = smallImage;
	}

	public String getMediumImage() {
		return mediumImage;
	}

	public void setMediumImage(String mediumImage) {
		this.mediumImage = mediumImage;
	}

	public String getLargeImage() {
		return largeImage;
	}

	public void setLargeImage(String largeImage) {
		this.largeImage = largeImage;
	}

	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}

	public String getCountries() {
		return countries;
	}

	public void setCountries(String countries) {
		this.countries = countries;
	}

	public String getGenres() {
		return genres;
	}

	public void setGenres(String genres) {
		this.genres = genres;
	}

	public String getCurrentSeason() {
		return currentSeason;
	}

	public void setCurrentSeason(String currentSeason) {
		this.currentSeason = currentSeason;
	}

	public String getOriginalTitle() {
		return originalTitle;
	}

	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getSubtype() {
		return subtype;
	}

	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}

	public String getAkas() {
		return akas;
	}

	public void setAkas(String akas) {
		this.akas = akas;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getCasts() {
		return casts;
	}

	public void setCasts(String casts) {
		this.casts = casts;
	}

	public String getDirectors() {
		return directors;
	}

	public void setDirectors(String directors) {
		this.directors = directors;
	}

	public String getResponseJson() {
		return responseJson;
	}

	public void setResponseJson(String responseJson) {
		this.responseJson = responseJson;
	}

	public Integer getRatingsCount() {
		return ratingsCount;
	}

	public void setRatingsCount(Integer ratingsCount) {
		this.ratingsCount = ratingsCount;
	}

	public String getTorrentFlag() {
		return torrentFlag;
	}

	public void setTorrentFlag(String torrentFlag) {
		this.torrentFlag = torrentFlag;
	}

	public String getSubtitleFlag() {
		return subtitleFlag;
	}

	public void setSubtitleFlag(String subtitleFlag) {
		this.subtitleFlag = subtitleFlag;
	}

	public String getPublishFlag() {
		return publishFlag;
	}

	public void setPublishFlag(String publishFlag) {
		this.publishFlag = publishFlag;
	}


	
	

}

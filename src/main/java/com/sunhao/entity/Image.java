package com.sunhao.entity;

/**
 * 项目名称：sunhaocms
 * 类 名 称：Image
 * 类 描 述：TODO
 * 创建时间：2019/11/14 12:14 下午
 * 创 建 人：sunhao
 */
public class Image {
	
	private String url;
	private String desc;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	@Override
	public String toString() {
		return "Image [url=" + url + ", desc=" + desc + "]";
	}
	
	

}

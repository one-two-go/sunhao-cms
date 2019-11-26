
package com.sunhao.common;

/** 
 *  抛出异常  异常在html页面显示
 * @ClassName: CmcExceptionHtml 
 * @Description: TODO
 * @date: 2019年11月19日 上午10:50:58
 */
public class CmcExceptionHtml extends RuntimeException {
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = -6762629250308786819L;

	/**
	 * 
	 */
	public CmcExceptionHtml(String msg) {
		super(msg);
	}

}

package jp.logware.custmer.subform;

import java.util.List;

public class InfoListValue {
	/**
	 * お知らせ日付
	 */
	private List<InfoValue> infoList;

	/**
	 * お知らせ日付を取得します。
	 * @return お知らせ日付
	 */
	public List<InfoValue> getInfoList() {
	    return infoList;
	}

	/**
	 * お知らせ日付を設定します。
	 * @param infoList お知らせ日付
	 */
	public void setInfoList(List<InfoValue> infoList) {
	    this.infoList = infoList;
	}
}

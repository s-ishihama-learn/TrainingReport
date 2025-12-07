package jp.logware.custmer.subform;

import java.util.Map;

import jp.logware.custmer.common.BaseSelectBox;

/**
 * 開始時刻 セレクトボックスクラス
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class StartTimeSelectBox extends BaseSelectBox {
	public StartTimeSelectBox(Map<String, String> map) {
		super();
		for (String key : map.keySet()) {
			setMapData(key, map.get(key));
		}
	}

	public StartTimeSelectBox() {
		super();
		setMapData("0", "");
		setMapData("1", "SELECT-1");
		setMapData("2", "SELECT-2");
		setMapData("3", "SELECT-3");
	}
}

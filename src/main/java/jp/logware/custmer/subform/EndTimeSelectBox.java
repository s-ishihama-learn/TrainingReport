package jp.logware.custmer.subform;

import java.util.Map;

import jp.logware.custmer.common.BaseSelectBox;

/**
 * 終了時刻 セレクトボックスクラス
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class EndTimeSelectBox extends BaseSelectBox {
	public EndTimeSelectBox(Map<String, String> map) {
		super();
		for (String key : map.keySet()) {
			setMapData(key, map.get(key));
		}
	}

	public EndTimeSelectBox() {
		super();
		setMapData("0", "");
		setMapData("1", "SELECT-1");
		setMapData("2", "SELECT-2");
		setMapData("3", "SELECT-3");
	}
}

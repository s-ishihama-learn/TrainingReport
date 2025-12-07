package jp.logware.custmer.logic;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ラジオ、セレクトボックス用データ格納クラス
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class ValueBean {
	private Map<String, Object> map;

	public ValueBean() {
		map = new LinkedHashMap<String, Object>();
	}

	public void setMapData(String key, Object value) {
		map.put(key, value);
	}

	public Map<String, Object> getValues() {
		return map;
	}
}

package jp.logware.custmer.subform;

import java.util.Map;

import jp.logware.custmer.common.BaseSelectBox;

public class HolidayKindSelectBox extends BaseSelectBox {
	public HolidayKindSelectBox(Map<String, String> map) {
		super();
		for (String key : map.keySet()) {
			setMapData(key, map.get(key));
		}
	}
}

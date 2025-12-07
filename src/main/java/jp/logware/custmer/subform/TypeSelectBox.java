package jp.logware.custmer.subform;

import java.util.Map;

import jp.logware.custmer.common.BaseSelectBox;

/**
 * 分類 セレクトボックスクラス
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class TypeSelectBox extends BaseSelectBox {
	public TypeSelectBox(Map<String, String> map) {
		super();
		for (String key : map.keySet()) {
			setMapData(key, map.get(key));
		}
	}
}

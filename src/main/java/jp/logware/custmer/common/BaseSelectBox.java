package jp.logware.custmer.common;

import java.util.LinkedHashMap;

/**
 * セレクトボックス用ベースクラス
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class BaseSelectBox {

    private LinkedHashMap<String,Object> map;

    public BaseSelectBox(){
        map = new LinkedHashMap<String,Object>();
    }

    /**
     * セレクトボックスのゲッター
     * @return LinkedHashMap<String,Object>
     */
    public LinkedHashMap<String,Object> getValues() {
        return map;
    }

    /**
     * セレクトボックスのセッター
     * @param key キー
     * @param value 値
     */
    public void setMapData(String key, Object value) {
        map.put(key, value);
    }
}

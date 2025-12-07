package jp.logware.custmer.entity;

/**
 * データ格納拡張クラス<br>
 * 表示グループ テーブル拡張
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class ViewGroupEntity extends ViewGroupEntityBase {
	/**
	 * コンストラクタ
	 */
	public ViewGroupEntity() {
	}

	/**
	 * コンストラクタ
	 */
	public ViewGroupEntity(ViewGroupEntityBase value) {
		super.setUserId(value.getUserId());
		super.setViewUserId(value.getViewUserId());
		super.setIdate(value.getIdate());
	}

}

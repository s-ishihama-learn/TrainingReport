package jp.logware.custmer.entity;

/**
 * データ格納拡張クラス<br>
 * 日報コメント テーブル拡張
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class CommentEntity extends CommentEntityBase {
	/**
	 * コンストラクタ
	 */
	public CommentEntity() {
	}

	/**
	 * コンストラクタ
	 */
	public CommentEntity(CommentEntityBase value) {
		super.setUserId(value.getUserId());
		super.setDate(value.getDate());
		super.setSeq(value.getSeq());
		super.setSeqComment(value.getSeqComment());
		super.setRegUserId(value.getRegUserId());
		super.setComment(value.getComment());
		super.setIdate(value.getIdate());
		super.setUdate(value.getUdate());
	}

}

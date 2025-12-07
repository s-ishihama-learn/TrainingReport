package jp.logware.custmer.subform;

/**
 * 顧客カルテ編集 アクションフォーム用サブクラス<br>
 * CommentValueクラス
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class CommentValue {
	/**
	 * コメント投稿者
	 */
	private String commentUser;

	/**
	 * コメント
	 */
	private String comment;

	/**
	 * コンストラクタ
	 */
	public CommentValue() {
		commentUser = "";
		comment = "";
	}

	/**
	 * コメント投稿者のゲッター
	 *
	 * @return コメント投稿者
	 */
	public String getCommentUser() {
		return commentUser;
	}

	/**
	 * コメント投稿者のセッター
	 *
	 * @param commentUser コメント投稿者
	 */
	public void setCommentUser(String commentUser) {
		this.commentUser = commentUser;
	}

	/**
	 * コメントのゲッター
	 *
	 * @return コメント
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * コメントのセッター
	 *
	 * @param comment コメント
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

}

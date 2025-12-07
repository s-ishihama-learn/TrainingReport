package jp.logware.custmer.logic.master;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jp.logware.custmer.common.Utility;
import jp.logware.custmer.dao.InfoDao;
import jp.logware.custmer.entity.InfoEntity;
import jp.logware.custmer.form.master.InfoEditActionForm;
import jp.logware.custmer.form.master.InfoListActionForm;
import jp.logware.custmer.subform.InfoValue;

/**
 * お知らせ編集 ロジッククラス
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */

public class InfoLogic {
	/**
	 * データベースコネクション
	 */
	private Connection dbConn = null;

	/**
	 * コンストラクタ
	 *
	 * @param conn
	 */
	public InfoLogic(Connection conn) {
		dbConn = conn;
	}

	/**
	 * ActionFormクラスへ値を代入
	 *
	 * @return InfoListActionForm
	 */
	public InfoListActionForm getInfoForm() throws Exception {
		InfoListActionForm form = new InfoListActionForm();
		String limitDate = Utility.getNowDate();

		InfoDao infoDao = new InfoDao(dbConn);
		String where = "LIMIT_DATE>='" + Utility.toDate(limitDate) + "'";
		List<InfoEntity> infoList = infoDao.getListWhereSort(where, "DATE DESC");
		List<InfoValue> infomList = new ArrayList<InfoValue>();
		for (InfoEntity info : infoList) {
			InfoValue infom = new InfoValue();
			infom.setInfoDate(getDateString(info.getDate()));
			infom.setSeq(String.valueOf(info.getSeq()));
			infom.setInfoBody(info.getBody());
			infomList.add(infom);
		}

		form.setInfomList(infomList);

		return form;
	}

	/**
	 * ActionFormクラスへ値を代入
	 *
	 * @return InfoListActionForm
	 */
	public InfoListActionForm getListForm() throws Exception {
		InfoListActionForm form = new InfoListActionForm();

		InfoDao infoDao = new InfoDao(dbConn);
		List<InfoEntity> infoList = infoDao.getListSort("");
		List<InfoValue> infomList = new ArrayList<InfoValue>();
		for (InfoEntity info : infoList) {
			InfoValue infom = new InfoValue();
			infom.setInfoDate(getDateString(info.getDate()));
			infom.setSeq(String.valueOf(info.getSeq()));
			infom.setInfoBody(info.getBody());
			infomList.add(infom);
		}

		form.setInfomList(infomList);

		return form;
	}

	/**
	 * ActionFormクラスへ値を代入
	 *
	 * @param String date
	 * @param String seq
	 * @return InfoEditActionForm
	 */
	public InfoEditActionForm getEditForm(String seq) throws Exception {
		InfoEditActionForm form = new InfoEditActionForm();

		InfoDao infoDao = new InfoDao(dbConn);
		InfoEntity info = infoDao.getValue(Integer.parseInt(seq));
		if (info != null) {
			form.setInfoDate(getDateString(info.getDate()));
			form.setSeq(String.valueOf(info.getSeq()));
			form.setInfoBody(info.getBody());
			form.setLimiDate(getLimitDateString(info.getLimitDate()));
		}

		return form;
	}

	/**
	 * ActionFormクラスからＤＢへ値を新規登録
	 *
	 * @param form InfoEditActionForm
	 */
	public void registData(InfoEditActionForm form) throws Exception {
		try {
			InfoDao infoDao = new InfoDao(dbConn);
			InfoEntity info = new InfoEntity();
			info.setDate(Utility.toDate(form.getInfoDate()));
			info.setSeq(getNewInfoSeq(form.getSeq()));
			info.setBody(form.getInfoBody());
			info.setLimitDate(Utility.toDate(form.getLimiDate()));
			info.setIdate(Utility.getTimestampNow());
			info.setUdate(Utility.getTimestampNow());
			infoDao.setValue(info);
			form.setSeq(String.valueOf(info.getSeq()));

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * ActionFormクラスからＤＢの更新
	 *
	 * @param form InfoEditActionForm
	 */
	public void updateData(InfoEditActionForm form) throws Exception {
		try {
			InfoDao infoDao = new InfoDao(dbConn);
			InfoEntity info = infoDao.getValue(Integer.parseInt(form.getSeq()));
			info.setDate(Utility.toDate(form.getInfoDate()));
			info.setSeq(Utility.toInt(form.getSeq()));
			info.setBody(form.getInfoBody());
			info.setLimitDate(Utility.toDate(form.getLimiDate()));
			info.setUdate(Utility.getTimestampNow());
			infoDao.setValue(info);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * ActionFormクラスからＤＢの削除
	 *
	 * @param form InfoEditActionForm
	 */
	public void removeData(InfoEditActionForm form) throws Exception {
		try {
			InfoDao infoDao = new InfoDao(dbConn);
			infoDao.removeValue(Integer.parseInt(form.getSeq()));

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 参照変換処理
	 *
	 * @param date Date
	 * @return String
	 */
	public String getDateString(Date date) throws Exception {
		return Utility.getDate(date);
	}

	/**
	 * 新規変換処理
	 *
	 * @param seq int
	 * @return String
	 */
	private int getNewInfoSeq(String seq) throws Exception {
		InfoDao infoDao = new InfoDao(dbConn);
		List<InfoEntity> list = infoDao.getListSort("SEQ DESC LIMIT(1)");
		int n = 1;
		if (list.size() > 0) {
			InfoEntity info = list.get(0);
			n = info.getSeq() + 1;
		}
		return n;
	}

	/**
	 * 参照変換処理
	 *
	 * @param limitDate Date
	 * @return String
	 */
	public String getLimitDateString(Date limitDate) throws Exception {
		return Utility.getDate(limitDate);
	}
}

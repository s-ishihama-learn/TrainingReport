package jp.logware.custmer.logic;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.struts.util.MessageResources;

import jp.logware.custmer.dao.MDeptDao;
import jp.logware.custmer.dao.MTypeDao;
import jp.logware.custmer.dao.MUserDao;
import jp.logware.custmer.entity.MDeptEntity;
import jp.logware.custmer.entity.MTypeEntity;
import jp.logware.custmer.entity.MUserEntity;

/**
 * セレクトボックス ロジッククラス
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */

public class SelectBoxLogic {
	/**
	 * データベースコネクション
	 */
	private Connection dbConn = null;

	/**
	 * メッセージリソース
	 */
	private MessageResources resource = null;

	/**
	 * コンストラクタ
	 *
	 * @param conn
	 * @param res
	 */
	public SelectBoxLogic(Connection conn, MessageResources res) {
		dbConn = conn;
		resource = res;
	}

	/**
	 * 処理メソッド
	 *
	 * @return Map<String,String>
	 */
	public Map<String, String> getMasterDeptNameMap() throws Exception {
		MDeptDao mDeptDao = new MDeptDao(dbConn);
		List<MDeptEntity> mDeptList = mDeptDao.getListSort("DEPT_CODE");
		Map<String, String> deptMap = new LinkedHashMap<String, String>();
		deptMap.put("", "");
		for (MDeptEntity mDept : mDeptList) {
			deptMap.put(mDept.getDeptCode(), mDept.getDeptName());
		}
		return deptMap;
	}

	/**
	 * 処理メソッド
	 *
	 * @return Map<String,String>
	 */
	public Map<String, String> getMasterUserDeptNameMap() throws Exception {
		MUserDao mUserDao = new MUserDao(dbConn);
		MDeptDao mDeptDao = new MDeptDao(dbConn);
		List<MUserEntity> mUserList = mUserDao.getListSort("USER_ID");
		List<MDeptEntity> mDeptList = mDeptDao.getList();
		Map<String, String> userMap = new LinkedHashMap<String, String>();
		for (MUserEntity mUser : mUserList) {
			for (MDeptEntity mDept : mDeptList) {
				if (mUser.getDeptCode().equals(mDept.getDeptCode())) {
					userMap.put(mUser.getUserId(), mDept.getDeptName());
					break;
				}
			}
		}
		return userMap;
	}

	/**
	 * 処理メソッド
	 *
	 * @return Map<String,String>
	 */
	public Map<String, String> getMasterUserNameMap() throws Exception {
		MUserDao mUserDao = new MUserDao(dbConn);
		List<MUserEntity> mUserList = mUserDao.getListSort("USER_ID");
		Map<String, String> userMap = new LinkedHashMap<String, String>();
		for (MUserEntity mUser : mUserList) {
			userMap.put(mUser.getUserId(), mUser.getUserName());
		}
		return userMap;
	}

	/**
	 * 処理メソッド
	 *
	 * @return Map<String,String>
	 */
	public Map<String, String> getUserComSelectBox() throws Exception {
		Map<String, String> mapUser = new LinkedHashMap<String, String>();
		mapUser.put("", "");
		mapUser.put("9", resource.getMessage("user.com.admin")); // 管理者
		mapUser.put("2", resource.getMessage("user.com.approval")); // 承認者
		mapUser.put("1", resource.getMessage("user.com.general")); // 一般社員
		return mapUser;
	}

	/**
	 * 処理メソッド
	 *
	 * @return Map<String,String>
	 */
	public Map<String, String> getStartTimeselectBox(Properties prop) throws Exception {
		String start = prop.getProperty("time_select_start");
		String pitch = prop.getProperty("time_select_pitch");

		int startHour = Integer.parseInt(start.substring(0, 2));
		int startMin = Integer.parseInt(start.substring(3, 5));
		int pitchHour = Integer.parseInt(pitch.substring(0, 2));
		int pitchMin = Integer.parseInt(pitch.substring(3, 5));

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, startHour);
		calendar.set(Calendar.MINUTE, startMin);
		calendar.set(Calendar.SECOND, 0);

		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("", "");
		String time0 = sdf.format(calendar.getTime());
		String key0 = time0.replaceAll(":", "");
		if(time0.startsWith("0")){
			map.put(key0, time0.substring(1, 5));
		} else {
			map.put(key0, time0);
		}

		int plus = 60 * pitchHour + pitchMin;
		int n = 1440 / plus - 1;
		for (int i = 0; i < n; i++) {
			calendar.add(Calendar.MINUTE, plus);
			String time = sdf.format(calendar.getTime());
			String key = time.replaceAll(":", "");
			if(time.startsWith("0")){
				map.put(key, time.substring(1, 5));
			} else {
				map.put(key, time);
			}
		}

		return map;
	}

	/**
	 * 処理メソッド
	 *
	 * @return Map<String,String>
	 */
	public Map<String, String> getEndTimeselectBox(Properties prop) throws Exception {
		String start = prop.getProperty("time_select_start");
		String pitch = prop.getProperty("time_select_pitch");

		int startHour = Integer.parseInt(start.substring(0, 2));
		int startMin = Integer.parseInt(start.substring(3, 5));
		int pitchHour = Integer.parseInt(pitch.substring(0, 2));
		int pitchMin = Integer.parseInt(pitch.substring(3, 5));

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, startHour);
		calendar.set(Calendar.MINUTE, startMin);
		calendar.set(Calendar.SECOND, 0);

		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("", "");

		int plus = 60 * pitchHour + pitchMin;
		int n = 1440 / plus;
		for (int i = 0; i < n; i++) {
			calendar.add(Calendar.MINUTE, plus);
			String time = sdf.format(calendar.getTime());
			String key = time.replaceAll(":", "");
			if(time.startsWith("0")){
				map.put(key, time.substring(1, 5));
			} else {
				map.put(key, time);
			}
		}

		return map;
	}

	/**
	 * 処理メソッド
	 *
	 * @return Map<String,String>
	 */
	public Map<String, String> getTypeSelectBox() throws Exception {
		MTypeDao mTypeDao = new MTypeDao(dbConn);
		List<MTypeEntity> mTypeList = mTypeDao.getListSort("TYPE_CODE");
		Map<String, String> typeMap = new LinkedHashMap<String, String>();
		typeMap.put("", "");
		for (MTypeEntity mType : mTypeList) {
			typeMap.put(mType.getTypeCode(), mType.getTypeName());
		}
		return typeMap;
	}

	/**
	 * 休日種別
	 * @param prop
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getHolidayKindSelectBox(Properties prop) throws Exception {
		String str = prop.getProperty("holiday_kind");
		String[] para = str.split(",");
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("", "");
		for (String p : para) {
			String[] s = p.split(":");
			map.put(s[0], s[1]);
		}
		return map;
	}

	/**
	 * 休日種別
	 * @param prop
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getHolidayColor(Properties prop) throws Exception {
		String str = prop.getProperty("holiday_color");
		String[] para = str.split(",");
		Map<String, String> map = new HashMap<String, String>();
		for (String p : para) {
			String[] s = p.split(":");
			map.put(s[0], s[1]);
		}
		return map;
	}
}

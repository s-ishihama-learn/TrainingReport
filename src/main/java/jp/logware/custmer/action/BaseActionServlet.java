package jp.logware.custmer.action;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.apache.struts.action.ActionServlet;

import jp.logware.custmer.common.ConnectionPool;
import jp.logware.custmer.common.InitParam;

/**
 * ベースサーブレットクラス
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class BaseActionServlet extends ActionServlet {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3803471653761914322L;

	/**
	 * 初期化処理
	 * @param config (ServletConfigオブジェクト)
	 * @throws ServletException (ServletExceptionクラスの例外)
	 */
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		try{
			ServletContext ctx = config.getServletContext();

			// web.xmlのパラメータの取得
			InitParam param = new InitParam();
			param.setJdbcDriverName(ctx.getInitParameter("jdbcDriverName"));
			param.setJdbcConnectParam(ctx.getInitParameter("jdbcConnectParam"));
			param.setJdbcUserId(ctx.getInitParameter("jdbcUserId"));
			param.setJdbcPwd(ctx.getInitParameter("jdbcPwd"));
			param.setConnectionSize(Integer.parseInt(ctx.getInitParameter("connectionSize")));
			param.setWaitTime(Integer.parseInt(ctx.getInitParameter("waitTime")));
			ctx.setAttribute("InitParam", param);

		} catch ( Exception e ){
			e.printStackTrace();
		}
	}

	/**
	 * デストラクタ 
	 */
	public void destroy() {
		ConnectionPool.clearInstance();
	}

}

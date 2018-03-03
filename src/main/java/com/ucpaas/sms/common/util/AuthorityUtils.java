package com.ucpaas.sms.common.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 权限控制工具类
 * 
 */
public class AuthorityUtils {

	/**
	 * 当前登录用户的sid、roleId保存在session中的key
	 */
	private static final String LOGIN_USER = "LOGIN_USER";
	private static final String LOGIN_USER_NAME = "LOGIN_USER_NAME";
	private static final String LOGIN_CLIENT = "LOGIN_CLIENT";
	private static final String LOGIN_TYPE = "LOGIN_TYPE";

	private static final String LOGIN_TYPE_USER = "1";
	private static final String LOGIN_TYPE_CLIENT = "2";

	/*public static void setLoginUser(HttpServletRequest request, User user) {
		HttpSession session = request.getSession();
		session.setAttribute(LOGIN_USER, user);
		session.setAttribute(LOGIN_TYPE, LOGIN_TYPE_USER);
		session.setAttribute(LOGIN_USER_NAME, user.getRealname());
	}

	public static void setLoginClient(HttpServletRequest request, Account account) {
		HttpSession session = request.getSession();
		session.setAttribute(LOGIN_CLIENT, account);
		session.setAttribute(LOGIN_TYPE, LOGIN_TYPE_CLIENT);
		session.setAttribute(LOGIN_USER_NAME, account.getClientid());
	}

	public static String getLoginId(HttpServletRequest request) {
		String id = null;
		Object obj = request.getSession().getAttribute(LOGIN_TYPE);
		if (obj != null) {
			if (obj.toString().equals(LOGIN_TYPE_USER)) {
				id = ((User) request.getSession().getAttribute(LOGIN_USER)).getId().toString();
			} else {
				id = ((Account) request.getSession().getAttribute(LOGIN_CLIENT)).getClientid();
			}
		}
		return id;
	}

	public static Role getLoginRole(HttpServletRequest request) {
		Role role = null;
		Object obj = request.getSession().getAttribute(LOGIN_TYPE);
		if (obj != null) {
			if (obj.toString().equals(LOGIN_TYPE_USER)) {
				role = ((User) request.getSession().getAttribute(LOGIN_TYPE_USER)).getRole();
			}
		}
		return role;
	}*/

	public static Object getLoginUser(HttpServletRequest request) {
		Object user = null;
		Object obj = request.getSession().getAttribute(LOGIN_TYPE);
		if (obj != null) {
			if (obj.toString().equals(LOGIN_TYPE_USER)) {
				user = request.getSession().getAttribute(LOGIN_USER);
			} else {
				user = request.getSession().getAttribute(LOGIN_CLIENT);
			}
		}
		return user;
	}

	public static void logout(HttpServletRequest request) {
		request.getSession().setAttribute(LOGIN_TYPE, null);
		request.getSession().setAttribute(LOGIN_USER, null);
		request.getSession().setAttribute(LOGIN_CLIENT, null);
		request.getSession().setAttribute(LOGIN_USER_NAME, null);
	}

	/**
	 * 判断当前是否已登录
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isLogin(HttpServletRequest request) {
		return getLoginUser(request) != null;
	}

}

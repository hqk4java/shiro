package demo.hqk.shiro._03;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

/**
org.apache.shiro.realm.text.IniRealm; 默认
public static final String USERS_SECTION_NAME = "users";
public static final String ROLES_SECTION_NAME = "roles";
*/
public class JdbcDemo{
	@Test
	public void testJdbcRealm() {
		// 1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory(
				"classpath:shiro_jdbc.ini");
		// 2、得到SecurityManager实例并绑定给SecurityUtils
		org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		// 3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
		Subject subject = SecurityUtils.getSubject();
		String username = "hqk";
		String password = "123";
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		try {
			subject.login(token);
			System.out.println("身份验证成功!!!");
			boolean hasRole = subject.hasRole("update");
			boolean permitted = subject.isPermitted("delete");
			System.out.println(hasRole);
			System.out.println(permitted);
			if(!hasRole || !permitted){
				System.out.println("权限校验失败！！！");
			}
		} catch (AuthenticationException e) {
			e.printStackTrace();
			System.out.println("身份验证失败!!!!" + e.toString());
		}
		// 退出登录
		subject.logout();
	}
	 
}

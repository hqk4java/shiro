package demo.hqk.shiro._02;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

public class TestRealm {
	@Test
	public void testRealm() {
		// 1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory(
				"classpath:shiro_realm.ini");
		// 2、得到SecurityManager实例并绑定给SecurityUtils
		org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		// 3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("hqk", "123");
		try {
			subject.login(token);
			System.out.println("身份验证成功!!!");
		} catch (AuthenticationException e) {
			e.printStackTrace();
			System.out.println("身份验证失败!!!!" + e.toString());
		}
		// 退出登录
		subject.logout();
	}
}

package demo.hqk.shiro._01;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.util.Factory;
import org.junit.Test;

public class ShiroTestIni {
	@Test
	public void tesyLogin(){
		   //1:创建SecurityManager工厂对象：加载配置文件，创建工厂对象
		   Factory<org.apache.shiro.mgt.SecurityManager> factory =  
			         new IniSecurityManagerFactory("classpath:shiro.ini");  
		   //2：通过工厂对象传概念SecurityMnanger对象
		    SecurityManager securityManager=factory.getInstance();
		   //3:将securityManager绑定到当前运行环境中：让系统随时都可以访问securityManager对象
		    SecurityUtils.setSecurityManager(securityManager);
		    //4:创建当前登录的主体  注意：此时的主题没有经过认证
		    org.apache.shiro.subject.Subject  subject=SecurityUtils.getSubject();
		    //5:收集主题登录的身份/凭证，即账号和密码
		    UsernamePasswordToken  token=new  UsernamePasswordToken("zhangsan", "6616");
		    //6:主题登录
		    try {
		    	   subject.login(token);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println("登录失败");
			}
		 
		    //7:判断是否登录成功
		    System.out.println("登录成功"+subject.isAuthenticated());
		    //8:登出（注销）
		    subject.logout();
		    System.out.println("登出成功"+subject.isAuthenticated());
		}
	
}

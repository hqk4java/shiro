package demo.hqk.shiro._02;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class RealmDemo extends AuthorizingRealm   {
// 	org.apache.shiro.realm.CachingRealm 重构这个方法
 	@Override
	public String getName(){
 		return "MyRealm";
	}

 	//授权操作
 	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}

	//认证操作
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//参数token:表示登陆时包装的UsernamePasswordToken
		//通过用户名到数据库中查用户 信息，封装成一个AutenticationInfo对象返回，方便认证其进行对比
		
		//获取token中的用户名
		String username = (String) token.getPrincipal();
		
		//通过用户名查询数据库，将该用户对应数据查询返回，账号和密码
		if(!"hqk".equals(username)){
			return null;
		}
		
		String password = "123";
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username,password,getName());
		return info;
		
	}
	
}
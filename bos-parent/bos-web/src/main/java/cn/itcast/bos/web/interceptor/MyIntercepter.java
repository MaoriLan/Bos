package cn.itcast.bos.web.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import cn.itcast.bos.utils.BosUtils;

public class MyIntercepter extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		ActionProxy actionProxy=invocation.getProxy();
		String actionName = actionProxy.getActionName();
		String namespace = actionProxy.getNamespace();
		String url=namespace+actionName;
		if(BosUtils.getLoginUser()==null){
			return "login";
		}
		else{
		  return invocation.invoke();
		}
	}

}

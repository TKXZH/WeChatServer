package xvzh.http;

import java.lang.reflect.Method;
/**
 * Http Api方法信息，存储api类型和处理方法
 * @author xvzh
 *
 */
public class ApiInfo {
	public ApiType apiType;
	public Method apiMethod;
	
	public ApiInfo(ApiType apiType, Method apiMethod) {
		this.apiType = apiType;
		this.apiMethod = apiMethod;
	}
}

package xvzh.wechat.util.menu;

import java.util.ArrayList;
import java.util.List;

public class WeChatMenu {
	//对于单按钮和拥有子按钮的符合按钮应当重写一个共同的父类，不过这里用Object也足够了
	public List<Object> menu = new ArrayList<Object>();
	
	public WeChatMenu(Object menu1) {
		menu.add(menu1);
	}
	
	public WeChatMenu(Object menu1, Object menu2) {
		this(menu1);
		menu.add(menu2);
	}
	
	public WeChatMenu(Object menu1, Object menu2, Object menu3) {
		this(menu1,menu2);
		menu.add(menu3);
	}

	@Override
	public String toString() {
		String string = "{"+addQuotationMarks("button")+":"+"[";
		StringBuffer sb = new StringBuffer(string);
		for(Object object : menu) {
			sb.append(object);
			sb.append(",");
		}
		string = sb.toString().substring(0,sb.toString().length()-1);
		string += "]}";
		return string;
	}
	
	private String addQuotationMarks(String rawString) {
		return "\"" + rawString + "\"";
	}
}

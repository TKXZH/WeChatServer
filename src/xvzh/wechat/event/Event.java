package xvzh.wechat.event;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Event {
	public static Enum<WeChatEventType> checkEventType(String requseStr) {
	Pattern pattern1 = Pattern.compile("<MsgType><!\\[CDATA\\[([a-z]{1,})\\]\\]></MsgType>");
	Matcher matcher1 = pattern1.matcher(requseStr);
	if(matcher1.find()) {
		if(matcher1.group(1).equals("text")) {
			return WeChatEventType.text;
		} else {
			if(requseStr.indexOf("CLICK") != -1) {
				return WeChatEventType.click;
			}
		}
	}
		return WeChatEventType.other;
	}
}


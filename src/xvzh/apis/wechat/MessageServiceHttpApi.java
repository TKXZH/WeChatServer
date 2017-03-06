package xvzh.apis.wechat;

import xvzh.http.ApiType;
import xvzh.http.HttpApiMethod;
import xvzh.http.HttpRequest;
import xvzh.http.HttpResponse;
import xvzh.sensor.DHT11;
import xvzh.sensor.HCSR501;
import xvzh.sensor.RELAY;
import xvzh.sensor.ULN2003;
import xvzh.wechat.event.ClickEvent;
import xvzh.wechat.event.Event;
import xvzh.wechat.event.Image;
import xvzh.wechat.event.PictureMessageReply;
import xvzh.wechat.event.TextMessageReceive;
import xvzh.wechat.event.TextMessageReply;
import xvzh.wechat.event.WeChatEventType;
import xvzh.wechat.util.ClientCustomSSL;
import xvzh.wechat.util.StreamUtil;
import xvzh.wechat.util.XMLUtil;

public class MessageServiceHttpApi {
	@HttpApiMethod(apiType = ApiType.Default)
	public void test(HttpRequest request, HttpResponse response) throws Exception  {
		String messageRec = StreamUtil.Inputstr2Str_byteArr(request.request.getInputStream(), null);
		System.out.println(messageRec);
		if(Event.checkEventType(messageRec) == WeChatEventType.text) {
			TextMessageReceive textMessageReceive = (TextMessageReceive)XMLUtil.XML2Jaxb(messageRec,TextMessageReceive.class);
			TextMessageReply textMessageReply = new TextMessageReply(textMessageReceive.getFromUserName(), textMessageReceive.getToUserName(), textMessageReceive.getContent());
			response.respStr = XMLUtil.jaxb2XML(textMessageReply);
		} 
		if(Event.checkEventType(messageRec) == WeChatEventType.click) {
			ClickEvent clickEvent = (ClickEvent)XMLUtil.XML2Jaxb(messageRec, ClickEvent.class);
			String responseStr = "";
			if(clickEvent.getEventKey().equals("xvzh1")) {
				float temperature = DHT11.getInstance(0).getTemperature();
				TextMessageReply textMessageReply = new TextMessageReply(clickEvent.getFromUserName(), clickEvent.getToUserName(), "当前室温:"+temperature+"℃");
				responseStr = XMLUtil.jaxb2XML(textMessageReply);
			}
			if(clickEvent.getEventKey().equals("xvzh2")) {
				float humidity = DHT11.getInstance(0).getHumidity();
				TextMessageReply textMessageReply = new TextMessageReply(clickEvent.getFromUserName(), clickEvent.getToUserName(), "当前湿度:"+humidity+"%RH");
				responseStr = XMLUtil.jaxb2XML(textMessageReply);
			}
			if(clickEvent.getEventKey().equals("xvzh3")) {
				String string = "";
				boolean status = HCSR501.getInstance(5).getStatus();
				if(status) {
					string = "有人";
				}	else {
					string = "无人";
				}
				TextMessageReply textMessageReply = new TextMessageReply(clickEvent.getFromUserName(), clickEvent.getToUserName(), "人体感应:"+string);
				responseStr = XMLUtil.jaxb2XML(textMessageReply);
			} 
			if(clickEvent.getEventKey().equals("xvzh4")) {
				RELAY relay = RELAY.getInstance(29);
				relay.change();
			} 
			if(clickEvent.getEventKey().equals("xvzh5")) {
				ULN2003 uln2003 = ULN2003.getInstance(1,2,3,4);
				uln2003.turnLeft();
				TextMessageReply textMessageReply = new TextMessageReply(clickEvent.getFromUserName(), clickEvent.getToUserName(), "步进电机已向左旋转90度");
				responseStr = XMLUtil.jaxb2XML(textMessageReply);
			}
			if(clickEvent.getEventKey().equals("xvzh6")) {
				ULN2003 uln2003 = ULN2003.getInstance(1,2,3,4);
				uln2003.turnRight();
				TextMessageReply textMessageReply = new TextMessageReply(clickEvent.getFromUserName(), clickEvent.getToUserName(), "步进电机已向右旋转90度");
				responseStr = XMLUtil.jaxb2XML(textMessageReply);
			}
			if(clickEvent.getEventKey().equals("xvzh7")) {
				byte[] pic = ClientCustomSSL.getRealTimePic();
				String imageId = ClientCustomSSL.picUploadByByte(pic);
				Image image = new Image(imageId);
				PictureMessageReply pmr = new PictureMessageReply();
				pmr.setCreateTime(123123);
				pmr.setFromUserName(clickEvent.getToUserName());
				pmr.setImage(image);
				pmr.setMsgType();
				pmr.setToUserName(clickEvent.getFromUserName());
				responseStr = XMLUtil.jaxb2XML(pmr);
			} 
			response.respStr = responseStr;
		}
		if (Event.checkEventType(messageRec) == WeChatEventType.other) {
			TextMessageReceive textMessageReceive = (TextMessageReceive)XMLUtil.XML2Jaxb(messageRec,TextMessageReceive.class);
			byte[] pic = ClientCustomSSL.getRealTimePic();
			String imageId = ClientCustomSSL.picUploadByByte(pic);
			Image image = new Image(imageId);
			PictureMessageReply pmr = new PictureMessageReply();
			pmr.setCreateTime(123123);
			pmr.setFromUserName(textMessageReceive.getToUserName());
			pmr.setImage(image);
			pmr.setMsgType();
			pmr.setToUserName(textMessageReceive.getFromUserName());
			response.respStr = XMLUtil.jaxb2XML(pmr);
		} 
		
	}
}



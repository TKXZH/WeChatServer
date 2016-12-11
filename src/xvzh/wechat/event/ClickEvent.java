package xvzh.wechat.event;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import xvzh.wechat.util.CDataAdapter;

@XmlRootElement(name="xml")
@XmlAccessorOrder(XmlAccessOrder.UNDEFINED)
public class ClickEvent {
	String ToUserName;
	String FromUserName;
	String CreateTime;
	String MsgType;
	String Event;
	String EventKey;
	
	@XmlJavaTypeAdapter(CDataAdapter.class)
	@XmlElement(name="ToUserName")
	public String getToUserName() {
		return ToUserName;
	}
	@XmlJavaTypeAdapter(CDataAdapter.class)
	@XmlElement(name="FromUserName")
	public String getFromUserName() {
		return FromUserName;
	}
	
	@XmlElement(name="CreateTime")
	public String getCreateTime() {
		return CreateTime;
	}
	
	@XmlJavaTypeAdapter(CDataAdapter.class)
	@XmlElement(name="MsgType")
	public String getMsgType() {
		return MsgType;
	}
	
	@XmlJavaTypeAdapter(CDataAdapter.class)
	@XmlElement(name="Event")
	public String getEvent() {
		return Event;
	}
	
	@XmlJavaTypeAdapter(CDataAdapter.class)
	@XmlElement(name="EventKey")
	public String getEventKey() {
		return EventKey;
	}
	
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public void setEvent(String event) {
		Event = event;
	}
	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}	
}

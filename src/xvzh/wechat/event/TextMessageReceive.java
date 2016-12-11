package xvzh.wechat.event;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import xvzh.wechat.util.CDataAdapter;

@XmlRootElement(name="xml")
@XmlAccessorOrder(XmlAccessOrder.UNDEFINED)
public class TextMessageReceive {
	String ToUserName;
	String FromUserName;
	int CreateTime;
	String MsgType;
	String Content;
	String MsgId;
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
	public int getCreateTime() {
		return CreateTime;
	}
	
	@XmlJavaTypeAdapter(CDataAdapter.class)
	@XmlElement(name="MsgType")
	public String getMsgType() {
		return MsgType;
	}
	
	@XmlJavaTypeAdapter(CDataAdapter.class)
	@XmlElement(name="Content")
	public String getContent() {
		return Content;
	}
	
	@XmlElement(name="MsgId")
	public String getMsgId() {
		return MsgId;
	}
	
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public void setCreateTime(int createTime) {
		CreateTime = createTime;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public void setContent(String content) {
		Content = content;
	}
	public void setMsgId(String msgId) {
		MsgId = msgId;
	}
}

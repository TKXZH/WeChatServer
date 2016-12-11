package xvzh.wechat.event;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import xvzh.wechat.util.CDataAdapter;


@XmlRootElement(name="xml")
@XmlAccessorOrder(XmlAccessOrder.UNDEFINED)
public class PictureMessageReply {
	String ToUserName;
	String FromUserName;
	int CreateTime;
	String MsgType;
	Image image;
	public PictureMessageReply() {
		super();
	}
	public PictureMessageReply(String ToUserName, String FromUserName, String MediaId, Image image) {
		this.ToUserName = ToUserName;
		this.FromUserName = FromUserName;
		this.CreateTime = (int)System.currentTimeMillis()/1000;
		this.MsgType = "image";
		this.image = image;
	}
	@XmlElement(name="Image")
	public Image getImage() {
		return image;
	}
	
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
	
	
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public void setCreateTime(int createTime) {
		CreateTime = createTime;
	}
	public void setMsgType() {
		MsgType = "image";
	}
	public void setImage(Image image) {
		this.image = image;
	}
}

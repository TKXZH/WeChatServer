package xvzh.wechat.event;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import xvzh.wechat.util.CDataAdapter;


@XmlRootElement(name="Image")
@XmlAccessorOrder(XmlAccessOrder.UNDEFINED)
public class Image {
	public Image() {
		
	}
	public Image(String MediaId) {
		this.MediaId = MediaId;
	}
	String MediaId;
	
	@XmlJavaTypeAdapter(CDataAdapter.class)
	@XmlElement(name="MediaId")
	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	
}

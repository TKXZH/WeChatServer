package xvzh.wechat.event;

import javax.xml.bind.JAXBException;

import xvzh.wechat.util.XMLUtil;

public class test {
	public static void main(String args[]) throws JAXBException {
		Image image = new Image("asdasdasdasd");
		PictureMessageReply pmr = new PictureMessageReply();
		pmr.setCreateTime(123123);
		pmr.setFromUserName("xv");
		pmr.setToUserName("hui");
		pmr.setImage(image);
		
		System.out.println(XMLUtil.jaxb2XML(pmr));
	}
}

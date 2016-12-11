package xvzh.wechat.util.menu;

public class ViewSingleButton extends SingleButton {
	String url;
	
	public ViewSingleButton(String name, String url) {
		super("view", name);
		this.url = url;
	}
	
	@Override
	public String toString() {
		String string = "{"+addQuotationMarks("type")+":"+addQuotationMarks(type)
				+","+addQuotationMarks("name")+":"+addQuotationMarks(name)
				+","+addQuotationMarks("url")+":"+addQuotationMarks(url)+"}";
		return string;
	}
	
	private String addQuotationMarks(String rawString) {
		return "\"" + rawString + "\"";
	}
}

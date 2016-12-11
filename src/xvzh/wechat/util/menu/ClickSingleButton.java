package xvzh.wechat.util.menu;

public class ClickSingleButton extends SingleButton {
	String key;
	
	public ClickSingleButton(String name, String key) {
		super("click", name);
		this.key = key;
	}

	@Override
	public String toString() {
		String string = "{"+addQuotationMarks("type")+":"+addQuotationMarks(type)
				+","+addQuotationMarks("name")+":"+addQuotationMarks(name)
				+","+addQuotationMarks("key")+":"+addQuotationMarks(key)+"}";
		return string;
	}
	
	private String addQuotationMarks(String rawString) {
		return "\"" + rawString + "\"";
	}
}

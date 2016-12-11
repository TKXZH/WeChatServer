package xvzh.wechat.util.menu;

import java.util.ArrayList;
import java.util.List;

public class CompoundButton {
	String name;
	List<SingleButton> buttons = new ArrayList<SingleButton>();
	
	public int getSize() {
		return buttons.size();
	}
	public CompoundButton(SingleButton button1, String name) {
		this.name = name;
		buttons.add(button1);
	}
	
	public CompoundButton(SingleButton button1, SingleButton button2, String name) {
		this(button1, name);
		buttons.add(button2);
	}
	
	public CompoundButton(SingleButton button1, SingleButton button2, SingleButton button3, String name) {
		this(button1, button2, name);
		buttons.add(button3);
	}
	
	public CompoundButton(SingleButton button1, SingleButton button2, SingleButton button3, SingleButton button4, String name) {
		this(button1, button2, button3, name);
		buttons.add(button4);
	}
	
	public CompoundButton(SingleButton button1, SingleButton button2, SingleButton button3, SingleButton button4, SingleButton button5, String name) {
		this(button1, button2, button3, button4, name);
		buttons.add(button5);
	}
	@Override
	public String toString() {
		String string = "{"+addQuotationMarks("name")+":"+addQuotationMarks(name)
				+","+addQuotationMarks("sub_button")+":"+ "[";
		
		StringBuffer sb = new StringBuffer(string);
		for(SingleButton button : buttons) {
			sb.append(button);
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

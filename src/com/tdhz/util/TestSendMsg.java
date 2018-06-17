package com.tdhz.util;




public class TestSendMsg {

	public static void main(String[] args) throws Exception {
//		SendOrderPaySuccessMsg s = new SendOrderPaySuccessMsg();
//		s.send_template_message("wx1af65f51f6f6d8e6", "d5d6b243b4a70e7f0a33cc6ca9dfb4bf", "opPP9wdMk4Ag3Tn2iHHxTZCln8Nw",123,456);
		MessageService m = new MessageService();
		m.sendMsg();
	}
}

package com.tdhz.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tdhz.menu.MenuManager;

@Controller
@Scope("prototype")
public class MenuAction {

	//声明输入流对象
	private InputStream inputStream;
	
	public InputStream getInputStream() {
		return inputStream;
	}
	
	public String buildMenu(){
		String result = null;
		result = MenuManager.buildMenu() +"";			
		try {
			inputStream=new ByteArrayInputStream(result.getBytes("UTF-8"));
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return "ajax";
	}
}

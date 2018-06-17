package com.tdhz.menu;
/**
 * 描述: 菜单项的基类
 * @author TD-PC
 *
 */
public class Button {

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	

	private String name;//所有一级菜单、二级菜单都共有一个相同的属性，那就是name
	private String type;
    private String url;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

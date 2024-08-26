package com.kenny.section03.delete;

public class Menu {
    private int menuCode;

    public Menu(int menuCode) {
        this.menuCode = menuCode;
    }

    public int getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(int menuCode) {
        this.menuCode = menuCode;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuCode=" + menuCode +
                '}';
    }
}

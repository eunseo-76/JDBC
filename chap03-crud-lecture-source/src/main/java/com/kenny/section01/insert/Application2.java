package com.kenny.section01.insert;

import java.util.Scanner;

/* Service 계층과 Repository 계층을 구현하고 XML 파일로부터 쿼리를 불러와서 insert 해보기 */
public class Application2 {
    public static void main(String[] args) {
        /* view 이자 controller 의 역할 */
        Scanner sc = new Scanner(System.in);
        System.out.print("메뉴의 이름 입력 : ");
        String menuName = sc.nextLine();
        System.out.print("메뉴의 가격 입력 : ");
        int menuPrice = sc.nextInt();
        System.out.print("카테고리 코드 입력 : ");
        int categoryCode = sc.nextInt();
        sc.nextLine();
        System.out.print("판매 상태 입력 : ");
        String orderableStatus = sc.nextLine();

        // 메뉴 라는 클래스로 묶어서 전달한다.
        Menu menu = new Menu(menuName, menuPrice, categoryCode, orderableStatus);

        MenuService menuService = new MenuService();
        menuService.registMenu(menu);
    }
}

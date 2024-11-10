package q1;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class Main {

    public static void printMenu() {
        System.out.println("*******************************");
        System.out.println("오늘의 선택, 코난문고");
        System.out.println("영원한 스테디셀러, 명탐정 코난시리즈를 만나보세요.");
        System.out.println("*******************************");
        System.out.println("1. 고객 정보 확인하기 2. 장바구니 상품 목록 보기");
        System.out.println("3. 바구니에 항목 추가하기 4. 장바구니의 항목 삭제하기");
        System.out.println("5. 장바구니 비우기 6. 영수증 표시하기 7. 종료");
        System.out.print("메뉴 번호를 선택해주세요 ");
    }

    public static void initialize(ArrayList<Book> books) {
        Book book1 = new Book("ISBN1234", "셜롬홈즈", 20000, "코난 도일",
                "그 누구도 뛰어넘지 못했던 추리 소설의 고전", "추리소설", "2018/10/08");
        Book book2 = new Book("ISBN2345", "도리안 그레이의 초상", 16000, "오스카 와일드",
                "예술을 위한 예술!", "고전소설", "2022/01/22");
        Book book3 = new Book("ISBN3456", "쥐덫", 27000, "애거서크리스티",
                "폭설 속에 갇힌 몽스웰 여관 - 네 명의 손님과 주인 부부, 그리고 한 명의 형사", "추리소설", "2019/06/10");
        books.add(book1);
        books.add(book2);
        books.add(book3);

    }

    public static void printBookList(ArrayList<Book> books) {
        for (Book book : books) {
            System.out.println(book.getIsbn() + " | " + book.getTitle() + " | " + book.getPrice() + "원 | "
                    + book.getAuthor() + " | " + book.getDescription() + " | " + book.getBookType() + " | " + book.getPublishedDate());
        }
    }

    // ISBN으로 책 찾기
    private static Book getBookByIsbn(ArrayList<Book> books, String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }
    
    public static void menu2 (ArrayList<Book> books, ArrayList<ShoppingList> shoppingList) {
        // 장바구니 상품 목록 보기
        if(shoppingList.isEmpty()) {
            System.out.println();
            System.out.println("장바구니가 비었습니다.");
        }
        else {
            printShoppingList(shoppingList, books);
        }
    }
    
    public static void printShoppingList(ArrayList<ShoppingList> shoppingList, ArrayList<Book> books) {
        System.out.println("장바구니 상품 목록 :");
        System.out.println("=======================");
        System.out.println(" 도서 ID   |   수량   |   합계");
        for(ShoppingList s : shoppingList) {
            Book book = getBookByIsbn(books, s.getIsbn());  // ISBN으로 책 정보 찾기
            if (book != null) {
                int total = book.getPrice() * s.getQuantity();
                System.out.println(book.getIsbn() + "  |  " + s.getQuantity() + "  |  " + total + "원");
            }
        }
        System.out.println("=======================");
    }
    
    public static void menu3(ArrayList<Book> books, ArrayList<ShoppingList> shoppingList, Scanner sc) {
        // 장바구니에 항목 추가하기
        printBookList(books); // 보유도서 목록 출력
        System.out.print("장바구니에 추가할 도서의 ID를 입력하세요 : ");
        String isbnToAdd = sc.nextLine();

        if (getBookByIsbn(books, isbnToAdd) != null) {
            System.out.println("장바구니에 추가하시겠습니까? Y | N");
            char yesorno = sc.next().charAt(0);

            if(yesorno == 'Y' || yesorno == 'y') {
                // 장바구니에 추가하거나 수량 업데이트
                boolean found = false;
                for (ShoppingList s : shoppingList) {
                    if (s.getIsbn().equals(isbnToAdd)) {
                        s.setQuantity(s.getQuantity() + 1);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    shoppingList.add(new ShoppingList(isbnToAdd, 1));
                }
            }

        } else {
            System.out.println("ISBN에 해당하는 도서를 찾을 수 없습니다.");
        }
    }

    private static void menu4(ArrayList<ShoppingList> shoppingList, ArrayList<Book> books, Scanner sc) {
        // 장바구니에서 항목 삭제하기
        System.out.println("장바구니 상품 목록 : ");
        printShoppingList(shoppingList, books);
        System.out.print("삭제할 도서의 ISBN을 입력하세요 : ");
        String isbn = sc.nextLine();
        boolean removed = false;
        for (ShoppingList s : shoppingList) {
            if (s.getIsbn().equals(isbn)) {
                shoppingList.remove(s);
                removed = true;
                break;
            }
        }
        if (removed) {
            System.out.println("도서가 장바구니에서 삭제되었습니다.");
        } else {
            System.out.println("장바구니에 해당 도서가 없습니다.");
        }
    }

    private static void menu5(ArrayList<ShoppingList> shoppingList) {
        // 장바구니 비우기
        shoppingList.clear();
        System.out.println("장바구니가 비워졌습니다.");
    }

    private static void menu6(String name, String phoneNumber,
                              ArrayList<ShoppingList> shoppingList, ArrayList<Book> books, Scanner sc) {
        // 영수증 표시하기
        System.out.print("배송받을 분은 고객정보와 같습니까? ");
        char yesorno = sc.next().charAt(0);
        sc.nextLine();
        if(yesorno == 'Y' || yesorno == 'y') {
            System.out.print("배송지를 입력해주세요 ");
            String dest = sc.nextLine();
            Calendar now = Calendar.getInstance();
            int year = now.get(Calendar.YEAR);
            int month = now.get(Calendar.MONTH);
            int day = now.get(Calendar.DAY_OF_MONTH);
            System.out.println("==========배송 받을 고객 정보==========");
            System.out.println("고객명 : " + name + " 연락처 : " + phoneNumber);
            System.out.println("배송지 : " + dest + " 발송일 : " + year + "-" + (month+1) + "-" + day);
        }
        printShoppingList(shoppingList, books);
        int totalAmount = 0;
        for(ShoppingList s : shoppingList) {
            Book book = getBookByIsbn(books, s.getIsbn());
            if (book != null) {
                int total = book.getPrice() * s.getQuantity();
                totalAmount += total;
                            }
        }
        System.out.println("총 금액: " + totalAmount + "원");
    }

    public static void main(String[] args) {
        ArrayList<Book> books = new ArrayList<>();
        initialize(books); // 초기 보유 책 정보 저장

        ArrayList<ShoppingList> shoppingList = new ArrayList<>(); // 사용자 장바구니

        Scanner sc = new Scanner(System.in);
        System.out.print("당신의 이름을 입력하세요 : ");
        String name = sc.nextLine();
        System.out.print("연락처를 입력하세요 : ");
        String phoneNumber = sc.nextLine();

        while(true) {
            printMenu();
            int choice = sc.nextInt();
            sc.nextLine();  // nextInt() 후 남은 개행 문자 처리

            if(choice == 1) {
                // 고객 정보 확인
                System.out.println("현재 고객 정보 : ");
                System.out.println("이름 " + name + " 연락처 " + phoneNumber);
            }

            else if(choice == 2) {
                menu2(books, shoppingList);
            }

            else if(choice == 3) {
                menu3(books, shoppingList, sc);
            }

            else if(choice == 4) {
                menu4(shoppingList, books, sc);
            }

            else if(choice == 5) {
                menu5(shoppingList);
            }

            else if(choice == 6) {
                menu6(name, phoneNumber, shoppingList, books, sc);
            }

            else if(choice == 7) {
                System.exit(0);
            }
        }
    }



}
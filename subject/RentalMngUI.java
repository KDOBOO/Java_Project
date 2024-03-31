package subject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.List;
import subject.BookVO;
import subject.BookImpl;
import subject.MemberImpl;
import subject.MemberVO;

public class RentalMngUI {
   
   private Book bkImpl = null;
   private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   private RentalMng ss = new RentalMngImpl();
   
   public RentalMngUI(Book bkImpl) {
      this.bkImpl = bkImpl;
   }

   public void menu() {
      int ch;


      //대여관리 메뉴
      while (true) {
         try {
            do {
               System.out.println("\t\t[대여 관리]");
               System.out.println("---------------------------------------------------------");
               System.out.println("1.대여 등록 2.대여 반납 3.도서 조회 4.전체 현황 5.뒤로가기\n");
               System.out.println("선택 => ");
               
               ch = Integer.parseInt(br.readLine());
            } while (ch < 1 || ch > 5);

            if (ch == 5) {
               return;
            }

            switch (ch) {
            case 1:
               register(); break;
            case 2:
               delete(); break;
            case 3:
               select(); break;
            case 4:
               printAll();
            // case 5 : // 돌아가기
            }

         } catch (Exception e) {
            e.printStackTrace();
         }
      }
   }

   //전체 현황
   private void printAll() {
      System.out.println("\n[대여 현황]");

      List<RentalMngVO> list = ss.listRental();
      
      System.out.println("도서명 \t등록일 \t반납예정일 \t대여현황");
      for (RentalMngVO vo : list) {
         System.out.print(vo.getRentbook() + "\t");
         System.out.print(vo.getRentalday() + "\t");
         System.out.println(vo.getReturnday());
         Return(vo.getBooknumber());
         
      }
      if(list.isEmpty()) {
         System.out.println("대여정보 없음");
      }
      System.out.println();
      
      
      
   }

   //도서 조회
   private void select() {
      
      String num;
      
      try {
         System.out.println("찾으려는 도서 번호를 입력하세요");
         num = br.readLine();
         
         BookVO bvo = bkImpl.readNumber(num); 
         
           if(bvo == null ) {   // 기존도서랑 일치하지 않으면 사라짐. 
                System.out.println("존재하지 않는 도서입니다.");
                return;
               }   
            System.out.println("검색한 책 대여 현황.\n");
            
            System.out.println("도서번호 \t도서명 \t대여현황");
            System.out.print(bvo.getNumber()+"\t");
            System.out.print(bvo.getName()+"\n");
            Return(num);

         

      } catch (Exception e) {
         System.out.println("찾으려는 도서를 확인할 수 없습니다"+"\n");
         return;
         
      }
      System.out.println();
   }

   //도서 반납
   private void delete() {
      System.out.println("\n[반납]");

      String booknum;

      try {
         
         
         System.out.println("반납할 도서 번호 :");
         booknum = br.readLine();

         boolean b = ss.deleteDate(booknum);
         RentalMngVO vo = ss.findBook(booknum);

         if (b) {
            System.out.println("반납 완료\n");
         } else {
            System.out.println("등록된 자료가 아닙니다.\n");
         }
         
         
      } catch (Exception e) {
         e.printStackTrace();
      }

   }

   //대여 등록
   private void register() {
      System.out.println("\n[대여할 도서의 번호를 입력하세요]");
      
      
      RentalMngVO vo = new RentalMngVO();
      
 
      try {
         String booknumber = br.readLine();
         BookVO bvo = bkImpl.readNumber(booknumber); 
         if(bvo == null ) {   // 기존도서랑 일치하지 않으면 사라짐. 
             System.out.println("존재하지 않는 도서입니다.");
             return;
            }   

            vo.setRentbook(bvo.getName());
            vo.setBooknumber(bvo.getNumber());
            vo.setRentalday(Date());
            vo.setReturnday(ReturnDate());
            
            ss.insertList(vo);
         

      } catch (Exception e) {
         e.printStackTrace();
      }
      System.out.println("대여 완료");

   }

   
   
   
   protected String Date() {

      LocalDate today = LocalDate.now();
      String todayString = today.toString();

      return todayString;

   }

   protected String ReturnDate() {
      LocalDate today = LocalDate.now();
      LocalDate afterday = today.plusDays(7);

      String afterdayString = afterday.toString();

      return afterdayString;
   }
   
   protected void Return(String num) {
      RentalMngVO vo = ss.findBook(num);
      
      if(ss.findBook(num) == null) {
         System.out.println("대여 가능");
      }
      else {
         System.out.print("\t 대여중");
      
         
      }
   
   }
}
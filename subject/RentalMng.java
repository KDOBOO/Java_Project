<<<<<<< HEAD
package subject;
import java.util.List;

public interface RentalMng {
	    //시간 데이터 등록 
		public void insertList(RentalMngVO vo);
		
		//삭제
		public boolean deleteDate(String boknum);
		
		public List<RentalMngVO> listRental();
		
		public String compareDate(String returndate,String lastday);
	
		public RentalMngVO findBook(String num);

}
=======
package subject;
import java.util.List;

public interface RentalMng {
	    //시간 데이터 등록 
		public void insertList(RentalMngVO vo);
		
		//삭제
		public boolean deleteDate(String boknum);
		
		public List<RentalMngVO> listRental();
		
		public String compareDate(String returndate,String lastday);
	
		public RentalMngVO findBook(String num);

}
>>>>>>> c1dce160f423e1166a16664b95b883140ea323f5

package subject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// 대여 관리 클래스
public class RentalMngImpl implements RentalMng {

	private List<RentalMngVO> list = new ArrayList<RentalMngVO>();
	

	@Override
	public void insertList(RentalMngVO vo) {
		list.add(vo);

	}

	@Override
	public boolean deleteDate(String boknum) {
		RentalMngVO vo = findBook(boknum);
	
		return list.remove(vo);
	}
	
	@Override
	public RentalMngVO findBook(String num) {

		for (RentalMngVO vo : list) {
			if (vo.getBooknumber().equals(num)) {
				return vo;
			}
		}
		return null;

	}

	@Override
	public List<RentalMngVO> listRental() {

		return list;
	}

	@Override
	public String compareDate(String returndate, String currentday) {
		
		LocalDate date1 = LocalDate.parse(returndate);
        LocalDate date2 = LocalDate.parse(currentday);
        String a = null;
        if(date1.isBefore(date2)) {
        	a="기간 남음"; //연체 아직 안댐
        }
        else if(date2.isAfter(date1)) {
        	a="연체";
        }
        
		return a;
	}

}

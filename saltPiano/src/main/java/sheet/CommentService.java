package sheet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

	@Autowired
	private CommentDao cDao;
	
	// 목록을 구하는 메서드
	public List<CommentVo> getList(int board_no) {
		return cDao.selectList(board_no);
	}
	
	public boolean insert(CommentVo vo) {
		int r = cDao.insert(vo);
		if (r > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean delete(int no) {
		int r = cDao.delete(no);
		if (r > 0) {
			return true;
		} else {
			return false;
		}
	}
	
}

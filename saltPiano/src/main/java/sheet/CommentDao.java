package sheet;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommentDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	//등록
	public List<CommentVo> selectList(int board_no) {
		return sqlSession.selectList("comment.selectList", board_no);
	}

	public int insert(CommentVo vo) {
		return sqlSession.insert("comment.insert",vo);
	}
	
	public int delete(int no) {
		return sqlSession.delete("comment.delete", no);
	}	
	
}

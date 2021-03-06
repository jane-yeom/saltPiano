package board;


import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public int count(BoardVo vo) {
		return sqlSession.selectOne("board.count", vo);
	}
	
	public List<BoardVo> selectList(BoardVo vo) {
		return sqlSession.selectList("board.selectList", vo);
	}
	
	public BoardVo selectOne(BoardVo vo) {
		return sqlSession.selectOne("board.selectOne", vo);
	}
	
	public int insert(BoardVo vo) {
		return sqlSession.insert("board.insert",vo);
	}
	
	public int delete(BoardVo vo) {
		return sqlSession.delete("board.delete", vo);
	}
	
	public int update(BoardVo vo) {
		return sqlSession.update("board.update", vo);
	}
	

	public int update_hits(int no) {
		return sqlSession.update("board.update_hits", no);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}	


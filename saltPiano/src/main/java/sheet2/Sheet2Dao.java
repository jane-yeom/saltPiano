package sheet2;


import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Sheet2Dao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public int count(Sheet2Vo vo) {
		return sqlSession.selectOne("sheet2.count", vo);
	}
	
	public List<Sheet2Vo> selectList(Sheet2Vo vo) {
		return sqlSession.selectList("sheet2.selectList", vo);
	}
	
	public Sheet2Vo selectOne(Sheet2Vo vo) {
		return sqlSession.selectOne("sheet2.selectOne", vo);
	}
	
	public int insert(Sheet2Vo vo) {
		return sqlSession.insert("sheet2.insert",vo);
	}
	
	public int delete(Sheet2Vo vo) {
		return sqlSession.delete("sheet2.delete", vo);
	}
	
	public int update(Sheet2Vo vo) {
		return sqlSession.update("sheet2.update", vo);
	}
	

	public int update_hits(int no) {
		return sqlSession.update("sheet2.update_hits", no);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}	


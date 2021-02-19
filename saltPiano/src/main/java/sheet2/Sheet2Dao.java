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
		return sqlSession.selectOne("sheet.count", vo);
	}
	
	public List<Sheet2Vo> selectList(Sheet2Vo vo) {
		return sqlSession.selectList("sheet.selectList", vo);
	}
	
	public Sheet2Vo selectOne(Sheet2Vo vo) {
		return sqlSession.selectOne("sheet.selectOne", vo);
	}
	
	public int insert(Sheet2Vo vo) {
		return sqlSession.insert("sheet.insert",vo);
	}
	
	public int delete(Sheet2Vo vo) {
		return sqlSession.delete("sheet.delete", vo);
	}
	
	public int update(Sheet2Vo vo) {
		return sqlSession.update("sheet.update", vo);
	}
	

	public int update_hits(int no) {
		return sqlSession.update("sheet.update_hits", no);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}	


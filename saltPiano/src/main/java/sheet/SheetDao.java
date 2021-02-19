package sheet;


import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SheetDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public int count(SheetVo vo) {
		return sqlSession.selectOne("sheet.count", vo);
	}
	
	public List<SheetVo> selectList(SheetVo vo) {
		return sqlSession.selectList("sheet.selectList", vo);
	}
	
	public SheetVo selectOne(SheetVo vo) {
		return sqlSession.selectOne("sheet.selectOne", vo);
	}
	
	public int insert(SheetVo vo) {
		return sqlSession.insert("sheet.insert",vo);
	}
	
	public int delete(SheetVo vo) {
		return sqlSession.delete("sheet.delete", vo);
	}
	
	public int update(SheetVo vo) {
		return sqlSession.update("sheet.update", vo);
	}
	

	public int update_hits(int no) {
		return sqlSession.update("sheet.update_hits", no);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}	


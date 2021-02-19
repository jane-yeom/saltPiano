package tutorial;


import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TutorialDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public int count(TutorialVo vo) {
		return sqlSession.selectOne("tutorial.count", vo);
	}
	
	public List<TutorialVo> selectList(TutorialVo vo) {
		return sqlSession.selectList("tutorial.selectList", vo);
	}
	
	public TutorialVo selectOne(TutorialVo vo) {
		return sqlSession.selectOne("tutorial.selectOne", vo);
	}
	
	public int insert(TutorialVo vo) {
		return sqlSession.insert("tutorial.insert",vo);
	}
	
	public int delete(TutorialVo vo) {
		return sqlSession.delete("tutorial.delete", vo);
	}
	
	public int update(TutorialVo vo) {
		return sqlSession.update("tutorial.update", vo);
	}
	

	public int update_hits(int no) {
		return sqlSession.update("tutorial.update_hits", no);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}	


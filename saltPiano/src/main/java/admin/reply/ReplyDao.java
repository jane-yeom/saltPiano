package admin.reply;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReplyDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public int count(ReplyVo vo) {
		return sqlSession.selectOne("reply.count", vo);
	}
	
	public List<ReplyVo> selectList(ReplyVo vo) {
		return sqlSession.selectList("reply.selectList", vo);
	}
	
	public ReplyVo selectOne(ReplyVo vo) {
		return sqlSession.selectOne("reply.selectOne", vo);
	}
	
	public int insert(ReplyVo vo) {
		return sqlSession.insert("reply.insertBoard",vo);
	}
	
	public int insertReply(ReplyVo vo) {
		return sqlSession.insert("reply.insertReply",vo);
	}
	
	public int update(ReplyVo vo) {
		return sqlSession.update("reply.updateBoard", vo);
	}
	
	public void refUpdate(int no) {
		sqlSession.update("reply.refUpdate", no);
	}
	
	public int delete(ReplyVo vo) {
		return sqlSession.delete("reply.deleteBoard", vo);
	}
	
	public void updateReadcount(int no) {
		sqlSession.update("reply.updateReadcount",no);
	}
	
	public void updateSeq(Map map) {
		sqlSession.update("reply.updateSeq",map);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}

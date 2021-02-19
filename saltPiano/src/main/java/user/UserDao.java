package user;


import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public int count(UserVo vo) {
		return sqlSession.selectOne("user.count", vo);
	}
	
	public List<UserVo> selectList(UserVo vo) {
		return sqlSession.selectList("user.selectList", vo);
	}
	
	public UserVo selectOne(UserVo vo) {
		return sqlSession.selectOne("user.selectOne", vo);
	}
	
	public int insert(UserVo vo) {
		return sqlSession.insert("user.insertUser",vo);
	}
	
	public int delete(UserVo vo) {
		return sqlSession.delete("user.deleteUser", vo);
	}	
	
	public int update(UserVo vo) {
		return sqlSession.update("user.updateUser", vo);
	}	
	
	public int isDuplicateId(String id) {
		return sqlSession.selectOne("user.isDuplicateId", id);
	}
	
	public UserVo login(UserVo vo) {
		return sqlSession.selectOne("user.login", vo);
	}
	
}	


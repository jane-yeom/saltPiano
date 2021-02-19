package video;


import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class VideoDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public int count(VideoVo vo) {
		return sqlSession.selectOne("video.count", vo);
	}
	
	public List<VideoVo> selectList(VideoVo vo) {
		return sqlSession.selectList("video.selectList", vo);
	}
	
	public VideoVo selectOne(VideoVo vo) {
		return sqlSession.selectOne("video.selectOne", vo);
	}
	
	public int insert(VideoVo vo) {
		return sqlSession.insert("video.insert",vo);
	}
	
	public int delete(VideoVo vo) {
		return sqlSession.delete("video.delete", vo);
	}
	
	public int update(VideoVo vo) {
		return sqlSession.update("video.update", vo);
	}
	

	public int update_hits(int no) {
		return sqlSession.update("video.update_hits", no);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}	


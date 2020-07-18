package kr.hs.dgsw.bbs;

import java.util.List;

public interface Bulletin {
	// 글을 쓰기
	public void write(Writing writing);
	
	// 글 수정하기
	public void update(Writing writing);

	// 글 목록 좋아요 숫자 증가 시키기
	public void increaseListHitCount(int sequence);
	
	// 글 읽기
	public Writing read(int sequence);
	
	// 글 목록
	public List<Writing> list();

	// 글 삭제하기
	public void delete(int sequence);

	// 글 삭제하면 해당 글 idx의 좋아요까지 삭제하기
	public void deleteHit(int sequence);

	// 좋아요 누르기
	public void increaseHit(Writing writing);

	// 좋아요 목록
	public List<Writing> readHit(int sequence);
}

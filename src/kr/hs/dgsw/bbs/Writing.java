package kr.hs.dgsw.bbs;

import java.sql.Timestamp;

public class Writing {

	private String title;
	
	private String content;
	
	private String writer;
	
	private int sequence;

	private int hit;

	private Timestamp hitTime;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public int getHit() {
		return this.hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public void setHitTime(Timestamp hitTime) {
		this.hitTime = hitTime;
	}

	public Timestamp getHitTime() {
		return this.hitTime;
	}
}

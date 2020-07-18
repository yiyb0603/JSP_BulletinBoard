package kr.hs.dgsw.bbs;

import java.util.List;

public interface Bulletin {
	// ���� ����
	public void write(Writing writing);
	
	// �� �����ϱ�
	public void update(Writing writing);

	// �� ��� ���ƿ� ���� ���� ��Ű��
	public void increaseListHitCount(int sequence);
	
	// �� �б�
	public Writing read(int sequence);
	
	// �� ���
	public List<Writing> list();

	// �� �����ϱ�
	public void delete(int sequence);

	// �� �����ϸ� �ش� �� idx�� ���ƿ���� �����ϱ�
	public void deleteHit(int sequence);

	// ���ƿ� ������
	public void increaseHit(Writing writing);

	// ���ƿ� ���
	public List<Writing> readHit(int sequence);
}

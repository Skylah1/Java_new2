package database;

import java.util.Vector;
import java.util.HashMap;

public interface CalculatorInterface {
	public Vector<Vector<String>> getIncomePerDate(); 
	public Vector<Vector<String>> getOutcomePerDate();
	public HashMap<String,Double> calcIncome();
	public HashMap<String,Double> calcOutcome();
	
}


//���� �Ϸ�
//���#2: ���� ��¥�� �����ͼ� �̰� -> �ش� ������ ���� �̹� �޿� �Է��� ���� ���� --> getIncomePerDate 	// ȭ�鼳�� �����̵� 7
//���#3: ���� ��¥�� �����ͼ� �̰� -> �ش� ������ ���� �̹� �޿� �Է��� ���� ���� --> getOutcomePerDate // ȭ�鼳�� �����̵� 8
//���#4: �ֱ� 6���� ���� �Ѵ޴����� ��� -> 8�� �� 9�� ��  --> calcIncome() // ȭ�鼳�� �����̵� 9
//���#5: �ֱ� 6���� ���� �Ѵ޴����� ��� -> 8�� �� 9�� �� --> calcOutcome()   // ȭ�鼳�� �����̵� 10


//�̱���
//���#1: ������ ���� ����ݾ� --> getExpectedExpense
//���#5: ��ü ���峻�� �� ī�װ��� ��� -> ������Ʈ    --> calcPerCategory()
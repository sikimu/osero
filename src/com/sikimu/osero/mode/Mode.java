package com.sikimu.osero.mode;

/**
 * ��Ԃ��Ƃ̐���N���X�̒��ۃN���X
 * @author sikimu
 *
 */
public abstract class Mode {
	
	/**
	 * �`��
	 */
	abstract public void draw();		
	
	/**
	 * �X�V
	 * @return �X�V��̐���N���X(�ύX�Ȃ��̏ꍇ�������g)
	 */
	abstract public Mode update();
}

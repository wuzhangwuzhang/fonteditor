package com.keitaitoys.fonteditor.gui.editor;

import java.awt.event.KeyEvent;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.keitaitoys.fonteditor.core.Manager;
import com.keitaitoys.fonteditor.gui.TextComponentDecreaseIntegerValueAction;
import com.keitaitoys.fonteditor.gui.TextComponentIncreaseIntegerValueAction;

public class CharOffsetYTextField extends JTextField implements DocumentListener {

	//////////////////////////////////////////////////////////////////////
	// Description ///////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

	//////////////////////////////////////////////////////////////////////
	// Consts ////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

	//////////////////////////////////////////////////////////////////////
	// Variables /////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

	//////////////////////////////////////////////////////////////////////
	// Constructor ///////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

	public CharOffsetYTextField() {
		
		Manager manager = Manager.getInstance();
		manager.registerCharOffsetYTextField(this);

		init();
	}

	//////////////////////////////////////////////////////////////////////
	// Functions /////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

	private void init() {
		
		setEnabled(true);
		setColumns(6);

		Action textComponentIncreaseIntegerValueAction = new TextComponentIncreaseIntegerValueAction(this);
		Action textComponentDecreaseIntegerValueAction = new TextComponentDecreaseIntegerValueAction(this);
		
		getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "textComponentIncreaseValueAction");
		getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "textComponentDecreaseValueAction");

		getActionMap().put("textComponentIncreaseValueAction", textComponentIncreaseIntegerValueAction);
		getActionMap().put("textComponentDecreaseValueAction", textComponentDecreaseIntegerValueAction);

		getDocument().addDocumentListener(this);
	}

	//////////////////////////////////////////////////////////////////////
	// Functions /////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

	public void changedUpdate(DocumentEvent e) {

	}
	
	//////////////////////////////////////////////////////////////////////
	// Functions /////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

	public void insertUpdate(DocumentEvent e) {

	}

	//////////////////////////////////////////////////////////////////////
	// Functions /////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

	public void removeUpdate(DocumentEvent e) {

	}
}

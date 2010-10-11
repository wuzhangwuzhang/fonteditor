package com.keitaitoys.fonteditor.gui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.text.JTextComponent;

@SuppressWarnings("serial")
public class TextComponentDecreaseCharValueAction extends AbstractAction {

	//////////////////////////////////////////////////////////////////////
	// Description ///////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

	//////////////////////////////////////////////////////////////////////
	// Consts ////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

	//////////////////////////////////////////////////////////////////////
	// Variables /////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

	private JTextComponent textComponent;
	
	//////////////////////////////////////////////////////////////////////
	// Constructor ///////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

	public TextComponentDecreaseCharValueAction(JTextComponent textComponent) {
		
		this.textComponent = textComponent;
	}

	//////////////////////////////////////////////////////////////////////
	// Functions /////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

	public void actionPerformed(ActionEvent event) {

		if(textComponent != null) {
			
			String text = textComponent.getText();
			
			if(text != null && text.length() == 1) {
				char value = text.charAt(0);
				value--;
				textComponent.setText(String.valueOf(value));
			}
		}
	}
}

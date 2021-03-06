package com.keitaitoys.fonteditor.gui.preferences;

import java.awt.event.KeyEvent;

import javax.swing.JLabel;

import com.keitaitoys.fonteditor.core.LocaleManager;
import com.keitaitoys.fonteditor.core.Manager;
import com.keitaitoys.fonteditor.event.LocaleChangeEvent;
import com.keitaitoys.fonteditor.event.LocaleChangeListener;

public class PreferencesEditorBoundsLabel extends JLabel implements LocaleChangeListener {

	//////////////////////////////////////////////////////////////////////
	// Description ///////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

	//////////////////////////////////////////////////////////////////////
	// Consts ////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

	private static final String CAPTION_LOCALIZE_KEY = "preferences.editor.label.bounds.caption";

	//////////////////////////////////////////////////////////////////////
	// Variables /////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

	//////////////////////////////////////////////////////////////////////
	// Constructor ///////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

	public PreferencesEditorBoundsLabel() {
		
//		Manager manager = Manager.getInstance();
//		manager.registerPreferencesEditorBoundsLabel(this);
		
		LocaleManager localeManager = LocaleManager.getInstance();
		localeManager.addLocaleChangeListener(this);

		init();
	}

	//////////////////////////////////////////////////////////////////////
	// Functions /////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

	private void init() {
		
		setEnabled(true);
		setHorizontalAlignment(JLabel.RIGHT);
		setDisplayedMnemonic(KeyEvent.VK_N);
		
		localize();
	}

	//////////////////////////////////////////////////////////////////////
	// Functions /////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

	private void localize() {
		
		LocaleManager localeManager = LocaleManager.getInstance();
		setText(localeManager.getValue(CAPTION_LOCALIZE_KEY));
	}
	
	//////////////////////////////////////////////////////////////////////
	// Functions /////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

	public void localeChange(LocaleChangeEvent e) {
		
		localize();
	}
}

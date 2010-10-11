package com.keitaitoys.fonteditor.gui.status;

import java.awt.Dimension;

import javax.swing.JLabel;

import com.keitaitoys.fonteditor.core.LocaleManager;
import com.keitaitoys.fonteditor.core.Manager;
import com.keitaitoys.fonteditor.event.LocaleChangeEvent;
import com.keitaitoys.fonteditor.event.LocaleChangeListener;

public class ScaleLabel extends JLabel implements LocaleChangeListener {

	//////////////////////////////////////////////////////////////////////
	// Description ///////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

	//////////////////////////////////////////////////////////////////////
	// Consts ////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

	private static final String TEXT_LOCALIZE_KEY = "status.label.scale.caption";
	private static final int MIN_WIDTH = 80;

	//////////////////////////////////////////////////////////////////////
	// Variables /////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

	private int scale = 100; // TODO: initial scale is set explicitly, we need to get it from the original source

	//////////////////////////////////////////////////////////////////////
	// Constructor ///////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

	public ScaleLabel() {
		
		Manager manager = Manager.getInstance();
		manager.registerScaleLabel(this);
		
		LocaleManager localeManager = LocaleManager.getInstance();
		localeManager.addLocaleChangeListener(this);

		init();
	}

	//////////////////////////////////////////////////////////////////////
	// Functions /////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

	private void init() {
		
		setEnabled(true);
		setHorizontalAlignment(JLabel.CENTER);

		localize();
	}

	//////////////////////////////////////////////////////////////////////
	// Functions /////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

	public int getScale() {
		
		return scale;
	}
	
	//////////////////////////////////////////////////////////////////////
	// Functions /////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

	public void setScale(int scale) {
		
		this.scale = scale;

		localize();
	}
	
	//////////////////////////////////////////////////////////////////////
	// Functions /////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

	private void localize() {
		
		setPreferredSize(null);

		LocaleManager localeManager = LocaleManager.getInstance();
		setText(localeManager.getValue(TEXT_LOCALIZE_KEY, scale));
		
		Dimension preferredSize = getPreferredSize();
		
		if(preferredSize.width < MIN_WIDTH) {
			preferredSize.width = MIN_WIDTH;
		}
		
		setPreferredSize(preferredSize);
	}
	
	//////////////////////////////////////////////////////////////////////
	// Functions /////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

	public void localeChange(LocaleChangeEvent e) {
		
		localize();
	}
}

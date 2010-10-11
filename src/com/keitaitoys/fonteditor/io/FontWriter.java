package com.keitaitoys.fonteditor.io;

import java.io.File;

import javax.swing.filechooser.FileFilter;

import com.keitaitoys.fonteditor.font.Font;

public interface FontWriter {

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
	// Functions /////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

	String[] getFormatNames();
	FileFilter getFileFilter();
	void write(String path, Font font) throws FontWriteFailedException; 
	void write(File file, Font font) throws FontWriteFailedException; 
}

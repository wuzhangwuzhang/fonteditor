package com.keitaitoys.fonteditor.font;

import java.util.EventObject;

public class FontChangeEvent extends EventObject {

	//////////////////////////////////////////////////////////////////////
	// Description ///////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

	//////////////////////////////////////////////////////////////////////
	// Consts ////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////
	
	//////////////////////////////////////////////////////////////////////
	// Variables /////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

    private FontChangeType type;

    //////////////////////////////////////////////////////////////////////
	// Constructor ///////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

    public FontChangeEvent(Object source, FontChangeType type) {
    	
    	super(source);
    	
    	this.type = type;
    }
    
	//////////////////////////////////////////////////////////////////////
	// Functions /////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

    public FontChangeType getType() {
    	
    	return type;
    }

	//////////////////////////////////////////////////////////////////////
	// Functions /////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

    public String toString() {
    	
    	return getClass().getName() + "[source=" + getSource() + ",type=" + type + "]";
    }
    
	//////////////////////////////////////////////////////////////////////
	// Inner class ///////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

	public enum FontChangeType {
		
	    IMAGE_FILE_CHANGED, SIZE_CHANGED, SPACING_CHANGED, LEADING_CHANGED, SYMBOL_ADDED, SYMBOL_REMOVED 
	}
}

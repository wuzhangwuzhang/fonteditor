package com.keitaitoys.fonteditor.font;

import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.swing.event.EventListenerList;

import com.keitaitoys.fonteditor.font.FontChangeEvent.FontChangeType;

public class Font {

	//////////////////////////////////////////////////////////////////////
	// Description ///////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

	//////////////////////////////////////////////////////////////////////
	// Consts ////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

	public static final int MINIMAL_SIZE = 1;
	
	public static final int DEFAULT_SIZE = 9;
	public static final int DEFAULT_SPACING = 1;
	public static final int DEFAULT_LEADING = 0;

	//////////////////////////////////////////////////////////////////////
	// Variables /////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

	protected EventListenerList listenerList = new EventListenerList();

	private File imageFile;

	private int size;
	private int spacing;
	private int leading;
	
	private HashSet<Symbol> symbols;
	
	//////////////////////////////////////////////////////////////////////
	// Constructor ///////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

	public Font() {
		
		this(null, DEFAULT_SIZE, DEFAULT_SPACING, DEFAULT_LEADING,  new HashSet<Symbol>());
	}

	//////////////////////////////////////////////////////////////////////
	// Constructor ///////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

	public Font(File imageFile, HashSet<Symbol> symbols) {
		
		this(imageFile, DEFAULT_SIZE, DEFAULT_SPACING, DEFAULT_LEADING, symbols);
	}

	//////////////////////////////////////////////////////////////////////
	// Constructor ///////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

	public Font(File imageFile, int size, int spacing, int leading, HashSet<Symbol> symbols) {
		
		if(size < MINIMAL_SIZE) throw new IllegalArgumentException("size must be greater than " + (MINIMAL_SIZE - 1));

		this.imageFile = imageFile;
		this.size = size;
		this.spacing = spacing;
		this.leading = leading;
		this.symbols = symbols;
	}

	//////////////////////////////////////////////////////////////////////
	// Functions /////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////
	
	public File getImageFile() {
		
		return imageFile;
	}

	//////////////////////////////////////////////////////////////////////
	// Functions /////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////
	
	public int getSize() {
		
		return size;
	}

	//////////////////////////////////////////////////////////////////////
	// Functions /////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////
	
	public int getSpacing() {
		
		return spacing;
	}

	//////////////////////////////////////////////////////////////////////
	// Functions /////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////
	
	public int getLeading() {
		
		return leading;
	}

	//////////////////////////////////////////////////////////////////////
	// Functions /////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////
	
	public Set<Symbol> getSymbols() {
		
		return Collections.unmodifiableSet(symbols);
	}

	//////////////////////////////////////////////////////////////////////
	// Functions /////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////
	
	public Symbol getSymbol(char c) {
		
		for(Symbol symbol: symbols) {
			if(symbol.getChar() == c) {
				return symbol;
			}
		}
		
		return null;
	}

	//////////////////////////////////////////////////////////////////////
	// Functions /////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////
	
	public void setImageFile(File imageFile) {
		
		this.imageFile = imageFile;
		
		fireFontChanged(FontChangeType.IMAGE_FILE_CHANGED);
	}

	//////////////////////////////////////////////////////////////////////
	// Functions /////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////
	
	public void setSize(int size) {
		
		if(size < MINIMAL_SIZE) throw new IllegalArgumentException("size must be greater than " + (MINIMAL_SIZE - 1));
		
		this.size = size;
		
		fireFontChanged(FontChangeType.SIZE_CHANGED);
	}

	//////////////////////////////////////////////////////////////////////
	// Functions /////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////
	
	public void setSpacing(int spacing) {
		
		this.spacing = spacing;
		
		fireFontChanged(FontChangeType.SPACING_CHANGED);
	}

	//////////////////////////////////////////////////////////////////////
	// Functions /////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////
	
	public void setLeading(int leading) {
		
		this.leading = leading;
		
		fireFontChanged(FontChangeType.LEADING_CHANGED);
	}

	//////////////////////////////////////////////////////////////////////
	// Functions /////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////
	
	public boolean addSymbol(Symbol symbol) {
		
		boolean success = symbols.add(symbol);

		if(success) {
			fireFontChanged(FontChangeType.SYMBOL_ADDED);
		}

		return true; 
	}

	//////////////////////////////////////////////////////////////////////
	// Functions /////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////
	
	public boolean removeSymbol(Symbol symbol) {
		
		boolean success = symbols.remove(symbol); 
		
		if(success) {
			fireFontChanged(FontChangeType.SYMBOL_REMOVED);
		}

		return success;
	}

	//////////////////////////////////////////////////////////////////////
	// Functions /////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////
	
	public void clear() {
		
		symbols.clear();
		
		fireFontChanged(FontChangeType.SYMBOL_REMOVED);
	}

	//////////////////////////////////////////////////////////////////////
	// Functions /////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////
	
	public boolean contains(Symbol symbol) {
		
		return symbols.contains(symbol);
	}

	//////////////////////////////////////////////////////////////////////
	// Functions /////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////
	
	public boolean isEmpty() {
		
		return symbols.isEmpty();
	}

	//////////////////////////////////////////////////////////////////////
	// Functions ///////////////////////////////////////////////////////// 
	//////////////////////////////////////////////////////////////////////

    protected void fireFontChanged(FontChangeType type) {
    	
    	Object[] listeners = listenerList.getListenerList();
    	FontChangeEvent e = null;

    	for(int i = listeners.length - 2; i >= 0; i -= 2) {
    		
    	    if(listeners[i] == FontChangeListener.class) {
    	    	if(e == null) e = new FontChangeEvent(this, type);
    	    	((FontChangeListener)listeners[i + 1]).fontChange(e);
    	    }
    	}
    }

	//////////////////////////////////////////////////////////////////////
	// Functions /////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

	public void addFontChangeListener(FontChangeListener l) {
		
		listenerList.add(FontChangeListener.class, l);
    }

	//////////////////////////////////////////////////////////////////////
	// Functions /////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

	public void removeFontChangeListener(FontChangeListener l) {
		
		listenerList.remove(FontChangeListener.class, l);
	}

	//////////////////////////////////////////////////////////////////////
	// Functions /////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

	public FontChangeListener[] getFontChangeListeners() {
		
		return (FontChangeListener[])listenerList.getListeners(FontChangeListener.class);
	}

	//////////////////////////////////////////////////////////////////////
	// Functions /////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

	public String toString() {
		
    	StringBuffer stringBuffer = new StringBuffer();
    	
    	stringBuffer.append(getClass().getName());
    	stringBuffer.append("[");
    	stringBuffer.append("imagefile=" + imageFile);    	
    	stringBuffer.append(",");
    	stringBuffer.append("size=" + size);
    	stringBuffer.append(",");
    	stringBuffer.append("spacing=" + spacing);
    	stringBuffer.append(",");
    	stringBuffer.append("leading=" + leading);
    	stringBuffer.append(",");
    	stringBuffer.append("symbols=");
    	stringBuffer.append("\n");
    	
    	for(Symbol symbol : symbols) {
        	stringBuffer.append("\t");
        	stringBuffer.append(symbol.toString());    	
        	stringBuffer.append("\n");
    	}
    	
    	stringBuffer.append("]");    	

    	return stringBuffer.toString();
	}
}
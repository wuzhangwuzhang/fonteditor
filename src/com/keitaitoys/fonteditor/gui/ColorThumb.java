package com.keitaitoys.fonteditor.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class ColorThumb extends JComponent {

	//////////////////////////////////////////////////////////////////////
	// Description ///////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

	//////////////////////////////////////////////////////////////////////
	// Consts ////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

	private static final Dimension MINIMUM_SIZE = new Dimension(40, 40); 
	private static final Dimension PREFERRED_SIZE = new Dimension(40, 40); 
	private static final Dimension MAXIMUM_SIZE = new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
	
	private static final Color BORDER_COLOR = new Color(144, 144, 144);
	private static final Color FOCUS_COLOR = new Color(204, 204, 204);
	
	//////////////////////////////////////////////////////////////////////
	// Variables /////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

	private ColorThumbModel colorThumbModel;

	private Handler handler;

    protected MouseListener mouseListener;
    protected FocusListener focusListener;

	//////////////////////////////////////////////////////////////////////
	// Constructor ///////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

	public ColorThumb() {
		
		colorThumbModel = new ColorThumbModel();

		mouseListener = createMouseListener();
		focusListener = createFocusListener();
		
		addMouseListener(mouseListener);
		addFocusListener(focusListener);
		
		Action actionPerformedAction = new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		        fireActionPerformed(e);
		    }
		};

		getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "actionPerformed");
		getActionMap().put("actionPerformed", actionPerformedAction);

		setFocusable(true);
	}

	//////////////////////////////////////////////////////////////////////
	// Function //////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

	public ColorThumbModel getModel() {
		
		return colorThumbModel;
	}

	//////////////////////////////////////////////////////////////////////
	// Function //////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

	public void setModel(ColorThumbModel colorThumbModel) {
		
        if (colorThumbModel == null) {
            throw new IllegalArgumentException("model must be not null");
        }

		this.colorThumbModel = colorThumbModel;
	    repaint();
	}

	//////////////////////////////////////////////////////////////////////
	// Function //////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

	public Color getColor() {
		
		return colorThumbModel.getColor();
	}

	//////////////////////////////////////////////////////////////////////
	// Function //////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

	public void setColor(Color color) {
		
		colorThumbModel.setColor(color);
		repaint();
	}

	//////////////////////////////////////////////////////////////////////
	// Function //////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

	public Dimension getMinimumSize() {
		
        if(isMinimumSizeSet()) {
            return super.getMinimumSize();
        }
        
        return MINIMUM_SIZE;
    }

	//////////////////////////////////////////////////////////////////////
	// Function //////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

	public Dimension getPreferredSize() {
		
        if(isPreferredSizeSet()) {
            return super.getPreferredSize();
        }
        
        return PREFERRED_SIZE;
    }

	//////////////////////////////////////////////////////////////////////
	// Function //////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

	public Dimension getMaximumSize() {
		
        if(isMaximumSizeSet()) {
            return super.getMaximumSize();
        }
        
        return MAXIMUM_SIZE;
    }

	//////////////////////////////////////////////////////////////////////
	// Function //////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////
	
	protected void paintComponent(Graphics g) {
		
		Graphics2D g2 = (Graphics2D)g.create();
		
		Color color = colorThumbModel.getColor();
		
		g2.setColor(color);
		g2.fillRect(0, 0, getWidth(), getHeight());
		
		g2.setColor(BORDER_COLOR);
		g2.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
		
		if(isFocusOwner()) {
			
			// Попытаемся получить цвет фокуса из текущего L&F
			Color focusColor = UIManager.getColor("Button.focus");
			
			if(focusColor == null) {
				focusColor = FOCUS_COLOR;
			}
			
			g2.setColor(focusColor);
			g2.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
		}

		g2.dispose();
	}

    //////////////////////////////////////////////////////////////////////
	// Functions /////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

	protected MouseListener createMouseListener() {
		
		return getHandler();
	}

    //////////////////////////////////////////////////////////////////////
	// Functions /////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

	protected FocusListener createFocusListener() {
		
		return getHandler();
	}

    //////////////////////////////////////////////////////////////////////
	// Functions /////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

    private Handler getHandler() {
    	
        if(handler == null) {
            handler = new Handler();
        }

        return handler;
    }

	//////////////////////////////////////////////////////////////////////
	// Functions /////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

    public void addActionListener(ActionListener l) {

    	listenerList.add(ActionListener.class, l);
    }
    
	//////////////////////////////////////////////////////////////////////
	// Functions /////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

    public void removeActionListener(ActionListener l) {
    	
	    listenerList.remove(ActionListener.class, l);
    }
    
	//////////////////////////////////////////////////////////////////////
	// Functions /////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

    public ActionListener[] getActionListeners() {

    	return (ActionListener[])(listenerList.getListeners(ActionListener.class));
    }

	//////////////////////////////////////////////////////////////////////
	// Functions /////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

    protected void fireActionPerformed(ActionEvent event) {

        Object[] listeners = listenerList.getListenerList();
        ActionEvent e = null;

        for(int i = listeners.length - 2; i >= 0; i-=2) {

        	if(listeners[i] == ActionListener.class) {

                if(e == null) {
                	
                      e = new ActionEvent(this,
                                          ActionEvent.ACTION_PERFORMED,
                                          event.getActionCommand(),
                                          event.getWhen(),
                                          event.getModifiers());
                }
                
                ((ActionListener)listeners[i + 1]).actionPerformed(e);
            }          
        }
    }

    //////////////////////////////////////////////////////////////////////
	// Inner class ///////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

    class Handler implements MouseListener, FocusListener {
    	
    	//////////////////////////////////////////////////////////////////////
    	// Functions /////////////////////////////////////////////////////////
    	//////////////////////////////////////////////////////////////////////

    	public void mouseClicked(MouseEvent e) {
    		
    	}

    	//////////////////////////////////////////////////////////////////////
    	// Functions /////////////////////////////////////////////////////////
    	//////////////////////////////////////////////////////////////////////

    	public void mouseReleased(MouseEvent e) {
    		
    	}
    	
    	//////////////////////////////////////////////////////////////////////
    	// Functions /////////////////////////////////////////////////////////
    	//////////////////////////////////////////////////////////////////////

    	public void mouseEntered(MouseEvent e) {
    		
    	}
    	
    	//////////////////////////////////////////////////////////////////////
    	// Functions /////////////////////////////////////////////////////////
    	//////////////////////////////////////////////////////////////////////

    	public void mouseExited(MouseEvent e) {
    		
    	}
    	
    	//////////////////////////////////////////////////////////////////////
    	// Functions /////////////////////////////////////////////////////////
    	//////////////////////////////////////////////////////////////////////

    	public void mousePressed(MouseEvent e) {
    		
    		if(SwingUtilities.isLeftMouseButton(e)) {
    			
    			if(isEnabled()) {
    				
    				if(!hasFocus() && isRequestFocusEnabled()) {
        				requestFocus();
        			}            

    				fireActionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "", e.getWhen(), e.getModifiersEx()));
    			}
    		}
    	}
    	
    	//////////////////////////////////////////////////////////////////////
    	// Functions /////////////////////////////////////////////////////////
    	//////////////////////////////////////////////////////////////////////

    	public void focusGained(FocusEvent e) {

    		repaint();
	    }

    	//////////////////////////////////////////////////////////////////////
    	// Functions /////////////////////////////////////////////////////////
    	//////////////////////////////////////////////////////////////////////

	    public void focusLost(FocusEvent e) {

	    	repaint();
	    }
    }
}
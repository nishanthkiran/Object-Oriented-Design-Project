/*
 * @(#)SelectAreaTracker.java
 *
 * Project:		JHotdraw - a GUI framework for technical drawings
 *				http://www.jhotdraw.org
 *				http://jhotdraw.sourceforge.net
 * Copyright:	? by the original author(s) and all contributors
 * License:		Lesser GNU Public License (LGPL)
 *				http://www.opensource.org/licenses/lgpl-license.html
 */

package CH.ifa.draw.standard;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.*;
import CH.ifa.draw.framework.*;

/**
 * SelectAreaTracker implements a rubberband selection of an area.
 *
 * @version <$CURRENT_VERSION$>
 */
public class SelectAreaTracker extends AbstractTool {

	private Rectangle fSelectGroup;

	public SelectAreaTracker(DrawingEditor newDrawingEditor) {
		super(newDrawingEditor);
	}

	public void mouseDown(MouseEvent e, int x, int y) {
		// use event coordinates to supress any kind of
		// transformations like constraining points to a grid
		super.mouseDown(e, e.getX(), e.getY());
		rubberBand(fAnchorX, fAnchorY, fAnchorX, fAnchorY);
	}

	public void mouseDrag(MouseEvent e, int x, int y) {
		super.mouseDrag(e, x, y);
		eraseRubberBand();
		rubberBand(fAnchorX, fAnchorY, x, y);
	}

	public void mouseUp(MouseEvent e, int x, int y) {
		super.mouseUp(e, x, y);
		eraseRubberBand();
		selectGroup(e.isShiftDown());
	}

	private void rubberBand(int x1, int y1, int x2, int y2) {
		fSelectGroup = new Rectangle(new Point(x1, y1));
		fSelectGroup.add(new Point(x2, y2));
		drawXORRect(fSelectGroup);
	}

	private void eraseRubberBand() {
		drawXORRect(fSelectGroup);
	}

	private void drawXORRect(Rectangle r) {
		Graphics g = view().getGraphics();
		if ( g != null ) {
			try {
				g.setXORMode(view().getBackground());
				g.setColor(Color.black);
				g.drawRect(r.x, r.y, r.width, r.height);
			}
			finally {
				g.dispose(); // SF bugtracker id: #490663
			}
		}
	}

	private void selectGroup(boolean toggle) {
		FigureEnumeration k = drawing().figuresReverse();
		while (k.hasMoreElements()) {
			Figure figure = k.nextFigure();
			Rectangle r2 = figure.displayBox();
			if (fSelectGroup.contains(r2.x, r2.y) && fSelectGroup.contains(r2.x+r2.width, r2.y+r2.height)) {
				if (toggle) {
					view().toggleSelection(figure);
				}
				else {
					view().addToSelection(figure);
				}
			}
		}
	}
}

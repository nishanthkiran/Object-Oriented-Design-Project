/*
 * @(#)FollowURLTool.java
 *
 * Project:		JHotdraw - a GUI framework for technical drawings
 *				http://www.jhotdraw.org
 *				http://jhotdraw.sourceforge.net
 * Copyright:	? by the original author(s) and all contributors
 * License:		Lesser GNU Public License (LGPL)
 *				http://www.opensource.org/licenses/lgpl-license.html
 */

package CH.ifa.draw.samples.javadraw;

import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JApplet;

import CH.ifa.draw.framework.DrawingEditor;
import CH.ifa.draw.framework.Figure;
import CH.ifa.draw.standard.AbstractTool;

/**
 * @version <$CURRENT_VERSION$>
 */
class FollowURLTool extends AbstractTool {
	 private JApplet         fApplet;

	 FollowURLTool(DrawingEditor newDrawingEditor, JApplet applet) {
		super(newDrawingEditor);
		fApplet = applet;
	 }

	/**
	 * Handles mouse move events in the drawing view.
	 */
	public void mouseMove(MouseEvent e, int x, int y) {
		String urlstring = null;
		Figure figure = drawing().findFigureInside(x,y);
		if (figure != null) {
			urlstring = (String) figure.getAttribute("URL");
		}
		if (urlstring != null) {
			fApplet.showStatus(urlstring);
		}
		else {
			fApplet.showStatus("");
		}
	}

	/**
	 * Handles mouse up in the drawing view.
	 */
	public void mouseUp(MouseEvent e, int x, int y) {
		Figure figure = drawing().findFigureInside(x, y);
		if (figure == null) {
			return;
		}
		String urlstring = (String) figure.getAttribute("URL");
		if (urlstring == null) {
			return;
		}

		try {
			URL url = new URL(fApplet.getDocumentBase(), urlstring);
			fApplet.getAppletContext().showDocument(url);
		}
		catch (MalformedURLException exception) {
			fApplet.showStatus(exception.toString());
		}
	}
}

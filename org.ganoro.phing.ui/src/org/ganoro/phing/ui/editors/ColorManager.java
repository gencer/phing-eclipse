package org.ganoro.phing.ui.editors;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.jface.text.source.ISharedTextColors;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

/**
 * Generic color manager.
 */
public class ColorManager implements ISharedTextColors {	
	
	private static ColorManager fgColorManager;
	
	private ColorManager() {
	}
	
	public static ColorManager getDefault() {
		if (fgColorManager == null) {
			fgColorManager= new ColorManager();
		}
		return fgColorManager;
	}
	
	protected Map fColorTable= new HashMap(10);
	
	public Color getColor(RGB rgb) {
		Color color= (Color) fColorTable.get(rgb);
		if (color == null) {
			color= new Color(Display.getCurrent(), rgb);
			fColorTable.put(rgb, color);
		}
		return color;
	}
	
	public void dispose() {
		Iterator e= fColorTable.values().iterator();
		while (e.hasNext()) {
			((Color) e.next()).dispose();
		}
	}
}



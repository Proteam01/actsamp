package net.acq;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class TrayIconManager {

	private static TrayIconManager instance = new TrayIconManager();

	private TrayIconManager() {

	}

	public static TrayIconManager getInstance() {
		return TrayIconManager.instance;
	}

	private TrayIcon tIcon;

	public void createTrayIcon() {
		final URL url = getClass().getResource("/candado.png");
		final Image image = Toolkit.getDefaultToolkit().createImage(url);
		this.tIcon = new TrayIcon(image);
		final SystemTray systemTray = SystemTray.getSystemTray();
		try {
			systemTray.add(this.tIcon);
			final PopupMenu menu = makeMenu();
			this.tIcon.setPopupMenu(menu);
		} catch (final AWTException e) {
			e.printStackTrace();
		}
	}

	private PopupMenu makeMenu() {
		final PopupMenu menu = new PopupMenu();
		final MenuItem closeItem = new MenuItem("Close");
		menu.add(closeItem);
		closeItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				System.exit(0);
			}
		});
		return menu;
	}

	public void showPopupBalloon(final String message, final String title) {
		this.tIcon.displayMessage(title, message, MessageType.INFO);
	}

}

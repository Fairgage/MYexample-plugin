package com.example;

import net.runelite.client.ui.overlay.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ExampleOverlay extends Overlay
{
	private final Client client;
	private Rectangle orbBounds;

	@Inject
	private ExampleOverlay(Client client)
	{
		this.client = client;
		setLayer(OverlayLayer.ABOVE_WIDGETS);
		setPosition(OverlayPosition.DYNAMIC);
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		// Get minimap position
		Point minimapLoc = client.getMinimapLocation();
		if (minimapLoc == null) return null;

		// Draw orb (adjust position as needed)
		int orbX = minimapLoc.x + 150;
		int orbY = minimapLoc.y + 20;
		graphics.setColor(new Color(0, 150, 255, 200));
		graphics.fillOval(orbX, orbY, 30, 30); // 30x30 circle

		// Save clickable area
		orbBounds = new Rectangle(orbX, orbY, 30, 30);
		return new Dimension(30, 30);
	}

	@Override
	public MouseOverResult getMouseoverOverlay(MouseEvent event)
	{
		if (orbBounds != null && orbBounds.contains(event.getPoint()))
		{
			return new MouseOverResult(
				"Custom Orb",
				event.getPoint(),
				() -> client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Orb clicked!", null)
			);
		}
		return null;
	}
}

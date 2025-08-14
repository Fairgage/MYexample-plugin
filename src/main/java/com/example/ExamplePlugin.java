package com.example;

import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;
import javax.inject.Inject;

@PluginDescriptor(
	name = "Custom Orb",
	description = "Adds a clickable orb to your minimap"
)
public class ExamplePlugin extends Plugin
{
	@Inject
	private OverlayManager overlayManager;

	@Inject
	private ExampleOverlay overlay;

	@Override
	protected void startUp()
	{
		overlayManager.add(overlay);
	}

	@Override
	protected void shutDown()
	{
		overlayManager.remove(overlay);
	}
}

/*
 * This source file is part of the FIUS ICGE project.
 * For more information see github.com/FIUS/ICGE2
 * 
 * Copyright (c) 2019 the ICGE project authors.
 * 
 * This software is available under the MIT license.
 * SPDX-License-Identifier:    MIT
 */
package de.unistuttgart.informatik.fius.icge.ui;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import de.unistuttgart.informatik.fius.icge.ui.internal.SwingConsole;
import de.unistuttgart.informatik.fius.icge.ui.internal.SwingEntitySidebar;
import de.unistuttgart.informatik.fius.icge.ui.internal.SwingPlayfieldDrawer;
import de.unistuttgart.informatik.fius.icge.ui.internal.SwingTextureRegistry;
import de.unistuttgart.informatik.fius.icge.ui.internal.SwingToolbar;
import de.unistuttgart.informatik.fius.icge.ui.internal.SwingGameWindow;


/**
 * The factory for creating a game window
 *
 * @author Tim Neumann
 */
public class GameWindowFactory {
    
    /**
     * Creates a new game window including the initialization of all required submodules.
     *
     * @param simulationProxy
     *     The simulation proxy to create the ui around
     * @return The new game window.
     */
    public static GameWindow createGameWindow(final SimulationProxy simulationProxy) {
        // Set window look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.err.println("Can't set look and feel because of: " + e.toString());
        }
        
        final SwingTextureRegistry textureRegistry = new SwingTextureRegistry();
        final SwingPlayfieldDrawer playfieldDrawer = new SwingPlayfieldDrawer(simulationProxy, textureRegistry);
        final SwingToolbar toolbar = new SwingToolbar(simulationProxy, textureRegistry);
        final SwingEntitySidebar entitySidebar = new SwingEntitySidebar(simulationProxy, textureRegistry);
        final SwingConsole console = new SwingConsole();
        
        return new SwingGameWindow(textureRegistry, playfieldDrawer, toolbar, entitySidebar, console);
    }
}

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

import java.util.Collection;

import de.unistuttgart.informatik.fius.icge.ui.internal.entity_selector.EntitySelector;

/**
 * The toolbar manager user by a {@link UiManager} to handle the toolbar.
 *
 * @author Tim Neumann
 * @author Tobias Wältken
 * @version 1.0
 */
public interface ToolbarManager {

    /**
     * Getter function for the user selected entity
     *
     * @return The displayname of the selected entry
     * @see EntitySelector
     */
    public String getCurrentEntity();

    /**
     * Append the list of entities with the given entry
     *
     * @param displayName the name which is displayed for the user
     * @param textureID the texture which is rendert infront of the display name
     * @see EntitySelector
     */
    public void addEntity(String displayName, String textureID);

    /**
     * This function adds a {@link ToolbarListener} to the toolbar
     *
     * @param listener the listener to be added
     * @return true (as specified in {@link Collection#add(Object)})
     */
    public boolean addToolbarListener(ToolbarListener listener);

    /**
     * This function removes the given listener from the toolbar
     *
     * @param listener the listener to be removed
     * @return true if this list contained the specified element
     */
    public boolean removeToolbarListener(ToolbarListener listener);

    /**
     * This function deletes all {@link ToolbarListener}s from the toolbar.
     */
    public void clearAllToolbarListeners();
}

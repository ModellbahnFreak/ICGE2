/*
 * This source file is part of the FIUS ICGE project.
 * For more information see github.com/FIUS/ICGE2
 * 
 * Copyright (c) 2019 the ICGE project authors.
 * 
 * This software is available under the MIT license.
 * SPDX-License-Identifier:    MIT
 */
package de.unistuttgart.informatik.fius.icge.simulation;

import de.unistuttgart.informatik.fius.icge.simulation.entity.EntityTypeRegistry;
import de.unistuttgart.informatik.fius.icge.simulation.tasks.TaskRegistry;
import de.unistuttgart.informatik.fius.icge.ui.TextureRegistry;


/**
 * The host for the simulation.
 * 
 * Holds the task and texture registries for all simulations.
 */
public interface SimulationHost {
    
    /**
     * Get the task registry associated with the simulation host.
     * 
     * @return The task registry
     */
    public TaskRegistry getTaskRegistry();
    
    /**
     * Get the texture registry associated with the simulation host.
     * 
     * @return The texture registry
     */
    public TextureRegistry getTextureRegistry();
    
    /**
     * Get the entity type registry associated with the simulation host.
     * 
     * @return The entity type registry
     */
    public EntityTypeRegistry getEntityTypeRegistry();
    
    /**
     * Update the graphics settings of the playfield drawer.
     * 
     * @param useDoubleBuffering
     *     true (default) uses doubleBuffering when rendering changes on the playfield.
     * @param syncToScreen
     *     true (default) actively tries to sync the updated graphics to the screen after rendering changes on the
     *     playfield.
     */
    public void updateGraphicsSettings(boolean useDoubleBuffering, boolean syncToScreen);
    
}
